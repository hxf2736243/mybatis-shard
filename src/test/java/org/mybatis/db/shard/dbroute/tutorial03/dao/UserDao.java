package org.mybatis.db.shard.dbroute.tutorial03.dao;

import org.mybatis.db.shard.dbroute.tutorial02.po.User;

/**
 * 
 * @ClassName: UserDao 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-11-4 上午10:45:38 
 *
 */
public interface UserDao {

	public User selectById(Long id);
	
	public void deleteById(Long id);
	
}
