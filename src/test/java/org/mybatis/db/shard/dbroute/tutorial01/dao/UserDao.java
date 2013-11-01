package org.mybatis.db.shard.dbroute.tutorial01.dao;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.db.shard.common.RouteParam;
import org.mybatis.db.shard.dbroute.impl.ModRoute;
import org.mybatis.db.shard.dbroute.tutorial01.po.User;
import org.mybatis.db.shard.engine.SessionFactoryRouter;

/**
 * 
 * @ClassName: UserDao 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-10-28 上午 3:00:59 
 *
 */
public class UserDao {

	static String   resourcePath = "org/mybatis/db/shard/dbroute/tutorial01/config/mybatis-config.xml";
	static String[] shardDbNames = new String[]{"shard_01","shard_02"};
	
	public User selectById_withRouteParam(Long id){

		SessionFactoryRouter sessionRouter = SessionFactoryRouter.getInstance(resourcePath, shardDbNames);
		ModRoute route=new ModRoute(); 
		try {
			route.parseRouteConfig("id%4={0~1:shard_01;2~3:shard_02}#_%04d");
		} catch (Exception e) {
			e.printStackTrace();
		}
		route.setRouteId(id);
		
		RouteParam param = new RouteParam(route);
		param.setDBGroupName(route.getDBGroupName()+".");
		param.put("id", id);
		sessionRouter.setCurrent(route);
		
		SqlSession  sqlSession = sessionRouter.getSession();
		MappedStatement ms = sessionRouter.getSqlSessionFactory().getConfiguration().getMappedStatement("org.mybatis.db.shard.dbroute.tutorial01.po.User.selectById_withRouteParam");
		System.out.println(ms.getSqlCommandType().name());
		User user = (User)sqlSession.selectOne("org.mybatis.db.shard.dbroute.tutorial01.po.User.selectById_withRouteParam", param);
		sessionRouter.closeSession();
		return user;
	}
	
	
}
