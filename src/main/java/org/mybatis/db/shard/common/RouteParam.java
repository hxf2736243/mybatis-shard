package org.mybatis.db.shard.common;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.db.shard.dbroute.interfaces.IDBRoute;

/**
 * 
 * @ClassName: ShardParam 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-10-28 下午6:30:27 
 *
 */
public class RouteParam extends HashMap<String, Object>{
	
	/**
	 * 
	 */
	public static final String TABLE_NAME_KEY = "tableName";
	
	/**
	 * 
	 */
	public static final String DB_NAME_KEY = "dbName";
	
	public RouteParam(){
		this(null);
	}
	
	public RouteParam(IDBRoute dBRoute){
		this.put(TABLE_NAME_KEY, null == dBRoute?"":dBRoute.getTableName());
		this.put(DB_NAME_KEY, null == dBRoute?"":dBRoute.getDBGroupName());
	}
	
	/**
	 * 
	 * @Title: setDbName 
	 * @Description: 设置DBgroup
	 * @param @param dbName 
	 * @return void
	 * @throws
	 */
	public RouteParam setDBGroupName(String dbName){
		this.put(DB_NAME_KEY, dbName);
		return this;
	}
	
	/**
	 * 
	 * @Title: setTableName 
	 * @Description: 设置表名
	 * @param  dbName 
	 * @return void
	 * @throws
	 */
	public RouteParam setTableName(String dbName){
		this.put(TABLE_NAME_KEY, dbName);
		return this;
	}
	
	
	public RouteParam setParam(Map<String, Object> params){
		if(null == params){
			return this;
		}
		this.putAll(params);
		return this;
	}
	
	
	public RouteParam setParam(String key,Object value){
		this.put(key, value);
		return this;
	}

}
