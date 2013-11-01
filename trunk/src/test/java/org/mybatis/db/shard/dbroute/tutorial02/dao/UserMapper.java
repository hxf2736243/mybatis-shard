package org.mybatis.db.shard.dbroute.tutorial02.dao;

import org.mybatis.db.shard.dbroute.tutorial02.po.User;

public interface UserMapper {
	
	public User selectById(Long id);
	
}
