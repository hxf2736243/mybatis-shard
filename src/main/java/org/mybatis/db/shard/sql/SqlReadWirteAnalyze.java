package org.mybatis.db.shard.sql;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @ClassName: SqlReadWirteAnalyze 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-11-1 下午4:45:57 
 *
 */
public class SqlReadWirteAnalyze implements ReadWriteAnalyze<String> {

	public boolean isRead(String sql) {
		if(StringUtils.containsIgnoreCase(sql, "insert") 
				&& StringUtils.containsIgnoreCase(sql, "update") 
				&& StringUtils.containsIgnoreCase(sql, "delete")){
			return false;
		}else{
			return true;
		}
	}

	public boolean isWrite(String sql) {
		if(StringUtils.containsIgnoreCase(sql, "insert")
				||StringUtils.containsIgnoreCase(sql, "update")
				||StringUtils.containsIgnoreCase(sql, "delete")){
			return true;
		}else{
			return false;
		}
	}

}
