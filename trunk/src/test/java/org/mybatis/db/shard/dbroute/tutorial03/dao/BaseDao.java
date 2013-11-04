package org.mybatis.db.shard.dbroute.tutorial03.dao;

import javax.annotation.Resource;

import org.mybatis.db.shard.dbroute.impl.DBRouteUtils;
import org.mybatis.db.shard.engine.interfaces.IShardThreadContext;
import org.mybatis.db.shard.engine.mapper.IMapperManager;

/**
 * 
 * @ClassName: BaseDao 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-11-4 上午11:51:46 
 *
 */
public abstract class BaseDao {

	@Resource(name = "mapperManager")
	protected IMapperManager mapperManager;
	
	@Resource(name = "dbRouteUtil")
	protected DBRouteUtils dbRouteUtil;
	
	@Resource(name = "shardContext")
	protected IShardThreadContext shardContext;
	
	
}
