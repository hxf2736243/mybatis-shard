package org.mybatis.db.shard.datasourse.interfaces;


/**
 * 
 * @ClassName: AbstractRWDataSource 
 * @Description: 可读写接口
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-10-31 下午4:54:13 
 *
 */
public interface ReadWriteAble {

	/**
	 * 
	 * @Title: isCanReadable 
	 * @Description: 是否可读
	 * @return boolean
	 * @throws
	 */
	public boolean isCanReadable();
	
	/**
	 * 
	 * @Title: isCanWriteable 
	 * @Description:是否可写
	 * @return boolean
	 * @throws
	 */
	public boolean isCanWriteable();

}
