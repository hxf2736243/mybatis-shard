package org.mybatis.db.shard.sql;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @ClassName: SqlRWAnalyzeImpl 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-10-29 下午3:42:13 
 *
 */
public class SqlRWAnalyzeImpl implements SqlRWAnalyze {

	
	public boolean isReadSQL(String sql) {
		if(StringUtils.containsIgnoreCase(sql, "insert") 
				&& StringUtils.containsIgnoreCase(sql, "update") 
				&& StringUtils.containsIgnoreCase(sql, "delete")){
			return false;
		}else{
			return true;
		}
	}

	public boolean isWriteSQL(String sql) {
		if(StringUtils.containsIgnoreCase(sql, "insert")
				||StringUtils.containsIgnoreCase(sql, "update")
				||StringUtils.containsIgnoreCase(sql, "delete")){
			return true;
		}else{
			return false;
		}
	}

}
