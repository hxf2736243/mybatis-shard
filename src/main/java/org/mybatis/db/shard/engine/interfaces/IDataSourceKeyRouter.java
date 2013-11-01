package org.mybatis.db.shard.engine.interfaces;

import java.util.List;

/**
 * 
 * @ClassName: ILookupKey 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-11-1 下午2:05:13 
 * 
 * @param <T>
 */
public interface IDataSourceKeyRouter<T> {
	
	/**
	 * 
	 * @Title: getKey 
	 * @Description: TODO
	 * @param readWriteKeys
	 * @return 
	 * T
	 * @throws
	 */
	public T getKey();
	
	/**
	 * 
	 * @Title: setReadKeys 
	 * @Description: TODO
	 * @param readKeys 
	 * void
	 * @throws
	 */
	public void setReadKeys(List<T> readKeys);
	
	/**
	 * 
	 * @Title: setWriteKeys 
	 * @Description: TODO
	 * @param writeKeys 
	 * void
	 * @throws
	 */
	public void setWriteKeys(List<T> writeKeys);
	
	
}
