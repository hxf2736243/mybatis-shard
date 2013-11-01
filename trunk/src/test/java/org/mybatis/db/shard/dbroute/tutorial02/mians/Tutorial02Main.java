package org.mybatis.db.shard.dbroute.tutorial02.mians;

import org.mybatis.db.shard.common.Constant;
import org.mybatis.db.shard.common.RouteParam;
import org.mybatis.db.shard.dbroute.impl.DBRouteUtils;
import org.mybatis.db.shard.dbroute.impl.ModRoute;
import org.mybatis.db.shard.dbroute.tutorial02.dao.UserMapper;
import org.mybatis.db.shard.engine.interfaces.IShardThreadContext;
import org.mybatis.db.shard.engine.mapper.IMapperManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Tutorial02Main {

	/** 
	 * @Title: main 
	 * @Description: TODO
	 * @param @param args 
	 * @return void
	 * @throws 
	 */
	public static void main(String[] args) {
        ApplicationContext ctx=null;  
        
        ctx=new ClassPathXmlApplicationContext("org/mybatis/db/shard/dbroute/tutorial02/config/applicationContext.xml");  
        
        IMapperManager mapperFactoryBean =  (IMapperManager)ctx.getBean("mapperManager");
        DBRouteUtils dbRouteUtil = (DBRouteUtils)ctx.getBean("dbRouteUtil");
        IShardThreadContext shardContext  = (IShardThreadContext)ctx.getBean("shardContext");
       
        UserMapper userMapper= mapperFactoryBean.getMapper("userMapper"); 
        for(int i=0 ; i<100 ; i++){
        	ModRoute dbRoute = dbRouteUtil.getRoute("tutorial02.user");
            dbRoute.setRouteId(i);
            RouteParam param = new RouteParam(dbRoute);
            param.put("id", i);
            shardContext.put(Constant.CURRENT_DB_ROUTE, dbRoute);
        	userMapper.selectById(param); 
        	userMapper.deleteById(param);
        	System.out.println("\n");
        }
        
	}

}
