package org.mybatis.db.shard.engine.mapper;


/**
 * 
 * @ClassName: MapperManager 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-10-31 上午 02:47:39 
 *
 */
public interface IMapperManager {
	
	/**
	 * 
	 * @Title: getMapper 
	 * @Description: 获取mpper
	 * @param  mpperId
	 * @return E
	 * @throws
	 */
	public <E> E getMapper(String mpperId);
}
