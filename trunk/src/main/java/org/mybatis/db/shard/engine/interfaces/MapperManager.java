package org.mybatis.db.shard.engine.interfaces;

import org.springframework.context.ApplicationContextAware;

/**
 * 
 * @ClassName: MapperManager 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-10-31 上午 02:47:39 
 *
 */
public interface MapperManager extends ApplicationContextAware{
	
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
