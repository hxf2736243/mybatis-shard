package org.mybatis.db.shard.dbroute.tutorial03.dao;

import org.mybatis.db.shard.common.RouteParam;
import org.mybatis.db.shard.dbroute.tutorial02.po.User;

/**
 * 
 * @ClassName: UserMapper 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-11-4 下午5:43:19 
 *
 */
public interface UserMapper {
	
	public User selectById(RouteParam param);
	
	public void deleteById(RouteParam param);
	
}
