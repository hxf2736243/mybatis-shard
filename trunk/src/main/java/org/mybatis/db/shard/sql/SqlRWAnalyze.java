package org.mybatis.db.shard.sql;

/**
 * 
 * @ClassName: SqlReadWriteAnalyze 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-10-29 下午3:31:21 
 *
 */
public interface SqlRWAnalyze {

	/**
	 * 
	 * @Title: isReadSQL <br> 
	 * @Description: 判断SQL是否是读SQL<br> 
	 * @param  sql <br> 
	 * @return boolean
	 * 是读SQL  	：true <br> 
	 * 不是读SQL	：false <br> 
	 * @throws 
	 */
	public boolean isReadSQL(String sql);
	
	/**
	 * 
	 * @Title: isWriteSQL 
	 * @Description: 判断SQL是否是写SQL
	 * @param  sql
	 * @return boolean
	 * 是写SQL  	：true <br> 
	 * 不是写SQL	：false <br> 
	 * @throws
	 */
	public boolean isWriteSQL(String sql);
}
