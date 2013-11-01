/**
 * 
 */
package org.mybatis.db.shard.engine;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.mybatis.db.util.SqlUtils;


/**
 * 
 * @ClassName: SQLReplacePlugin 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-10-28 4:00:10 
 *
 */
@Intercepts( { @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class })})
public class SQLPrintPlugin implements Interceptor {
	
	private static final Log log = LogFactory.getLog(SQLPrintPlugin.class);

	public Object intercept(Invocation invocation) throws Throwable {
		try{
			before(invocation);
			
			Object invocationResult = invocation.proceed();
			
			after(invocation);
			
			return invocationResult;
		}catch (Throwable e) {
			throw e;
		} finally{
			finallyer(invocation);
		}
		
	}
	
	private void before(Invocation invocation){
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		System.out.println("SQL:"+SqlUtils.format(statementHandler.getBoundSql().getSql()));
	}
	
	private void after(Invocation invocation){
		
	}
	
	private void finallyer(Invocation invocation){
		
	}

	
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		if (properties == null) {
			return;
		}
	}

}
