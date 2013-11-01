package org.mybatis.db.shard.engine;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.db.shard.dbroute.interfaces.IDBRoute;

/**
 * 
 * @ClassName: SessionFactoryUtil 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-10-28 上午 3:08:45 
 *
 */
public class SessionFactoryRouter {

	
	private  Map<String,SqlSessionFactory> sqlSessionRouter = new ConcurrentHashMap<String,SqlSessionFactory>();
	
	private  ThreadLocal<Map<String,SqlSession>> sqlSessionContext = new ThreadLocal<Map<String,SqlSession>>();
	
	private  ThreadLocal<IDBRoute> dbRouteContext = new ThreadLocal<IDBRoute>();
	
	private static SessionFactoryRouter instance  =  null;
	
	private SessionFactoryRouter(){
		
	}
	
	private SessionFactoryRouter(String resoucePath,String... dbShardNames){
		
		for(String dbName : dbShardNames){
			try {
				Reader reader = Resources.getResourceAsReader(resoucePath);
				sqlSessionRouter.put(dbName, getSqlSessionFactory(reader,dbName));
			} catch (IOException e) {
				e.printStackTrace();
				throw new  RuntimeException(e);
			}
		}
		
	}
	
	
	public static SessionFactoryRouter getInstance(String resoucePath,String... dbShardNames){
		if(null != instance){
			return instance;
		}
		instance = new SessionFactoryRouter(resoucePath,dbShardNames);
		return instance;
	}
	
	public  SqlSessionFactory getSqlSessionFactory(){ 
		IDBRoute route = dbRouteContext.get();
		if(null == route){
			throw new RuntimeException("route can not been null");
		}
        return sqlSessionRouter.get(route.getDBGroupName());
    }
	
	public  SqlSession getSession(){
		IDBRoute route = dbRouteContext.get();
		if(null == route){
			throw new RuntimeException("route can not been null");
		}
		Map<String,SqlSession> sqlSessionMap = sqlSessionContext.get();
		if(sqlSessionMap == null  ){
			sqlSessionMap = new HashMap<String,SqlSession>();
			sqlSessionContext.set(sqlSessionMap);
		}
		
		SqlSession session = sqlSessionMap.get(route.getDBGroupName());
		if(null != session){
			return session;
		}
		
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		
		if(null == sqlSessionFactory){
			throw new RuntimeException(route.toString()+"config has not ");
		}
		
		session = sqlSessionFactory.openSession();
		sqlSessionMap.put(route.getDBGroupName(), session);
		return session;
	}
	
	public  void closeSession(){
		IDBRoute route = dbRouteContext.get();
		if(null == route){
			return ;
		}
		Map<String,SqlSession> sqlSessionMap = sqlSessionContext.get();
		if(null == sqlSessionMap ){
			return ;
		}
		
		SqlSession session = sqlSessionMap.get(route.getDBGroupName());
		if(null == session){
			return ;
		}
		
		sqlSessionMap.remove(route.getDBGroupName());
		
		session.close();
	}
	
	public  void clear(){
		Map<String,SqlSession> sqlSessionMap = sqlSessionContext.get();
		if(null == sqlSessionMap ){
			return ;
		}
		sqlSessionContext.set(null);
		
		for(Map.Entry<String,SqlSession> entry : sqlSessionMap.entrySet()){
			try{
				entry.getValue().close();
			}catch(Exception e){
			}
		}
	}
	
	
    private static SqlSessionFactory getSqlSessionFactory(Reader reader, String environmentId) {
        return new SqlSessionFactoryBuilder().build(reader, environmentId);
    }

    
    public  void setCurrent(IDBRoute route){
    	dbRouteContext.set(route);
    }
	
}
