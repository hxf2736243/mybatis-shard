package org.mybatis.db.shard.dbroute.tutorial02.dao.impl;

import javax.annotation.Resource;

import org.mybatis.db.shard.common.Constant;
import org.mybatis.db.shard.common.RouteParam;
import org.mybatis.db.shard.dbroute.impl.ModRoute;
import org.mybatis.db.shard.dbroute.tutorial02.dao.BaseDao;
import org.mybatis.db.shard.dbroute.tutorial02.dao.UserDao;
import org.mybatis.db.shard.dbroute.tutorial02.dao.UserMapper;
import org.mybatis.db.shard.dbroute.tutorial02.po.User;
import org.mybatis.db.shard.engine.mapper.IMapperManager;
import org.springframework.stereotype.Service;

@Service("userDao")
public class UserDaoImpl extends BaseDao implements UserDao {

	@Resource(name = "mapperManager")
	protected IMapperManager mapperManager;
	
	private UserMapper userMapper = null;
	
	public User selectById(Long id) {
		initMapper();
		ModRoute dbRoute = dbRouteUtil.getRoute("tutorial02.user");		
		dbRoute.setRouteId(id);
		
        RouteParam param = new RouteParam(dbRoute);
        param.put("id", id);
        shardContext.put(Constant.CURRENT_DB_ROUTE, dbRoute);
        
		return userMapper.selectById(param);
	}

	public void deleteById(Long id) {
		initMapper();
		ModRoute dbRoute = dbRouteUtil.getRoute("tutorial02.user");		
		dbRoute.setRouteId(id);
		
        RouteParam param = new RouteParam(dbRoute);
        param.put("id", id);
        shardContext.put(Constant.CURRENT_DB_ROUTE, dbRoute);
        
        userMapper.deleteById(param);
        
	}
	
	private void initMapper(){
		if(null == userMapper){
			userMapper = mapperManager.getMapper("userMapper");
		}
		
	}

}
