package org.mybatis.db.shard.datasourse;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.mybatis.db.shard.common.Constant;
import org.mybatis.db.shard.dbroute.interfaces.IDBRoute;
import org.mybatis.db.shard.engine.interfaces.IShardThreadContext;

/**
 * 
 * @ClassName: MultipleGroupDataSourse 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-11-1 下午4:56:47 
 *
 */
public class MultipleGroupDataSourse implements DataSource {

	private Map<String,DataSource> dataSourceMap = new ConcurrentHashMap<String, DataSource>();;
	
	private IShardThreadContext shardContext;
	
	public PrintWriter getLogWriter() throws SQLException {
		return this.routeDataSource().getLogWriter();
	}
	
	public void setLogWriter(PrintWriter out) throws SQLException {
		 this.routeDataSource().setLogWriter(out);
	}

	public void setLoginTimeout(int seconds) throws SQLException {
		this.routeDataSource().setLoginTimeout(seconds);
	}

	public int getLoginTimeout() throws SQLException {
		return this.routeDataSource().getLoginTimeout();
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		return this.routeDataSource().unwrap(iface);
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return this.routeDataSource().isWrapperFor(iface);
	}

	public Connection getConnection() throws SQLException {
		return this.routeDataSource().getConnection();
	}

	public Connection getConnection(String username, String password)
			throws SQLException {
		return this.getConnection(username, password);
	}

	private DataSource routeDataSource(){
		IDBRoute route = shardContext.get(Constant.CURRENT_DB_ROUTE);
		if(null == route){
			throw new RuntimeException("current IDBRoute is null,pelese put current Route into shardContext");
		}
		DataSource dataSource = dataSourceMap.get(route.getDBGroupName());
		if(dataSource ==  null){
			throw new RuntimeException(String.format("dataSource: dbGroupName [%s] is null", route.getDBGroupName()));
		}
		System.out.println("--info---> DBGroupName routed tagert is : :"+ route.getDBGroupName());
		return dataSource;
	}

	public void setDataSourceMap(Map<String, DataSource> dataSourceMap) {
		this.dataSourceMap.putAll(dataSourceMap);
	}

	public void setShardContext(IShardThreadContext shardContext) {
		this.shardContext = shardContext;
	}

}
