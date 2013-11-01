package org.mybatis.db.shard.dbroute.impl;

import java.text.Format;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.db.shard.dbroute.interfaces.IDBRoute;

/**
 * 
 * @ClassName: AbstractRoute 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-10-30 下午12:22:59 
 *
 */
public abstract class AbstractRoute  implements IDBRoute  {


	/**
	 * 原始的RouteConfig
	 */
	protected String routeConfig;
	
	/**
	 * 库路由规则映射表
	 */
	protected Map<String,String> routeRuleMap = new HashMap<String,String>();
	
	/**
	 * 表路由字段名
	 */
	protected String tableRouteFieldName ="";
	
	/**
	 * 表名前缀
	 */
	protected String tableNamePrefix ="";
	
	/**
	 * 表名后缀生产规则
	 */
	protected String tableNamePostfixFormat ;
	
	/**
	 * 其他未知属性
	 */
	protected Map<String,String> attribute = new HashMap<String,String>();

	/**
	 * 解析分库配置字符串
	 * @Title: parseRouteConfig 
	 * @Description: TODO
	 * @param @param routeConfig
	 * @param @throws Exception 
	 * @return void
	 * @throws
	 */
	public abstract void parseRouteConfig(String routeConfig) throws Exception ;

	
	public String getRouteConfig() {
		return routeConfig;
	}


	public void setRouteConfig(String routeConfig) {
		this.routeConfig = routeConfig;
	}


	public Map<String, String> getRouteRuleMap() {
		//这个路由映射关系 只有在解析配置的时候可以被修改，其他都只是只读
		return new HashMap<String, String>(routeRuleMap);
	}


	public void setRouteRuleMap(Map<String, String> routeRuleMap) {
		this.routeRuleMap = routeRuleMap;
	}


	public String getTableNamePrefix() {
		return tableNamePrefix;
	}


	public void setTableNamePrefix(String tableNamePrefix) {
		this.tableNamePrefix = tableNamePrefix;
	}


	public String getAttribute(String key) {
		return attribute.get(key);
	}


	public void setAttribute(String key,String value) {
		this.attribute.put(key, value);
	}
	


	public String getTableRouteFieldName() {
		return tableRouteFieldName;
	}


	public void setTableRouteFieldName(String tableRouteFieldName) {
		this.tableRouteFieldName = tableRouteFieldName;
	}


	public String getTableNamePostfixFormat() {
		return tableNamePostfixFormat;
	}


	public void setTableNamePostfixFormat(String tableNamePostfixFormat) {
		this.tableNamePostfixFormat = tableNamePostfixFormat;
	}
}
