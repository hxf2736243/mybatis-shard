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
import org.mybatis.db.shard.common.Constant;
import org.mybatis.db.shard.dbroute.interfaces.IDBRoute;
import org.mybatis.db.shard.engine.interfaces.IShardThreadContext;

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
	
	/**
	 * 当前正在执行的IDBRoute
	 */
	public static final String CURRENT_SQL_SESSION_MAP = "$_current_sql_session_map$";
	
	IShardThreadContext shardThreadContext = new DefaultThreadContext ();
	
	
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
		IDBRoute route = shardThreadContext.get(Constant.CURRENT_DB_ROUTE);
		if(null == route){
			throw new RuntimeException("route can not been null");
		}
        return sqlSessionRouter.get(route.getDBGroupName());
    }
	
	/**
	 * 得到一个SqlSession
	 * @Title: getSession 
	 * @Description: TODO
	 * @return 
	 * SqlSession
	 * @throws
	 */
	public  SqlSession getSession(){
		IDBRoute route = shardThreadContext.get(Constant.CURRENT_DB_ROUTE);
		if(null == route){
			throw new RuntimeException("route can not been null");
		}
		Map<String,SqlSession> sqlSessionMap = shardThreadContext.get(CURRENT_SQL_SESSION_MAP);
		if(sqlSessionMap == null  ){
			sqlSessionMap = new HashMap<String,SqlSession>();
			shardThreadContext.put(CURRENT_SQL_SESSION_MAP, sqlSessionMap);
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
	
	/**
	 * 关闭当前sqlsession
	 * @Title: closeSession 
	 * @Description: TODO 
	 * void
	 * @throws
	 */
	public  void closeSession(){
		IDBRoute route = shardThreadContext.get(Constant.CURRENT_DB_ROUTE);
		if(null == route){
			return ;
		}
		Map<String,SqlSession> sqlSessionMap = shardThreadContext.get(CURRENT_SQL_SESSION_MAP);
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
	/**
	 * 清理当前线程绑定的变量
	 * @Title: clear 
	 * @Description: TODO 
	 * void
	 * @throws
	 */
	public  void clear(){
		shardThreadContext.destory();
	}
	
	
    private static SqlSessionFactory getSqlSessionFactory(Reader reader, String environmentId) {
        return new SqlSessionFactoryBuilder().build(reader, environmentId);
    }

    
    public  void setCurrent(IDBRoute route){
    	shardThreadContext.put(Constant.CURRENT_DB_ROUTE, route);
    }
	
}
