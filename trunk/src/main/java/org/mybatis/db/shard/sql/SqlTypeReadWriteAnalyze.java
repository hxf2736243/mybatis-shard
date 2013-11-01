package org.mybatis.db.shard.sql;

import org.apache.ibatis.mapping.SqlCommandType;

/**
 * 
 * @ClassName: SqlRWAnalyzeImpl 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-10-29 下午3:42:13 
 *
 */
public class SqlTypeReadWriteAnalyze implements ReadWriteAnalyze<SqlCommandType> {


	public boolean isRead(SqlCommandType sql) {
		return SqlCommandType.SELECT.equals(sql);
	}

	public boolean isWrite(SqlCommandType sql) {
		return !SqlCommandType.SELECT.equals(sql);
	}

}
