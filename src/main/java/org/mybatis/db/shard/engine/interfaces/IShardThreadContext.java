package org.mybatis.db.shard.engine.interfaces;

/**
 * 
 * @ClassName: IShardContext 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-10-31 下午3:04:19 
 *
 */
public interface IShardThreadContext {

	/**
	 * 当前线程是否设置了这个key
	 * @Title: contains 
	 * @Description: TODO
	 * @param key
	 * @return 
	 * boolean
	 * @throws
	 */
	public boolean contains(String key);
	
	/**
	 * 移除当前线程以key为变量名的变量
	 * @Title: remove 
	 * @Description: TODO
	 * @param key
	 * @return 
	 * E
	 * @throws
	 */
	public <E> E remove(String key);
	
	/**
	 * 获取当前线程以key为变量名的变量
	 * @Title: get 
	 * @Description: TODO
	 * @param key
	 * @return 
	 * E
	 * @throws
	 */
	public <E> E get(String key);
	
	/**
	 * 设置当前线程以key为变量名的变量
	 * @Title: put 
	 * @Description: TODO
	 * @param  key
	 * @param  value
	 * @return E
	 * @throws
	 */
	public <E> E put(String key, E value);
	
	/**
	 * 
	 * @Title: destory 
	 * @Description: 销毁当前线程绑定的所有变量
	 * @param  
	 * @throws
	 */
	public void destory() ;
}
