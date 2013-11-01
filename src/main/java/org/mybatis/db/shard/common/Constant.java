package org.mybatis.db.shard.common;

/**
 * 
 * @ClassName: Constant 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-10-31 下午9:01:21 
 *
 */
public abstract class Constant {

	/**
	 * 当前正在执行的mapperId
	 */
	public static final String CURRENT_MAPPER_ID = "$_current_mapper_id_$";
	
	/**
	 * 当前正在执行的mapper是否读key
	 */
	public static final String CURRENT_MAPPER_IS_READ = "$_current_mapper_read_$";
	
	/**
	 * 当前正在执行的IDBRoute
	 */
	public static final String CURRENT_DB_ROUTE = "$_current_db_route_$";
	
}
