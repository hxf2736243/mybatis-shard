package org.mybatis.db.shard.dbroute.impl;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.db.shard.common.SymbolConstant;
import org.mybatis.db.shard.dbroute.interfaces.IDBRoute;

/**
 * 
 * @ClassName: DBRouteUtils 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-11-1 下午7:07:30 
 *
 */
public class DBRouteUtils {

	//只读
	private Map<String,String> routeRuleConfigMap = new HashMap<String, String>();
	
	//只读
	private Map<String,Class> routeClassMap = new HashMap<String, Class>();

	private Map<String, String> dbRoutePropertiesMap = new HashMap<String, String>();
	
	public void init() {
		if(null == dbRoutePropertiesMap || dbRoutePropertiesMap.isEmpty()){
			return;
		}
		for(Map.Entry<String, String> entry : dbRoutePropertiesMap.entrySet()){
			initRouteItem(entry.getKey(),entry.getValue());
		}
		
	}
	
	private void initRouteItem(String key,String ruleValue){
		
		String[] ruleValueArr = ruleValue.split("\\"+SymbolConstant.SYMBOL_OPEN_BRACKET);
		
		String routeClass = ruleValueArr[0];
		
		String routeConfig = ruleValueArr[1].substring(0, ruleValueArr[1].length()-1);
		
		Class routeClazzBean = null;
		Object routeBean = null;
		try {
			routeClazzBean = Class.forName(routeClass);
			routeBean = routeClazzBean.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		if(routeBean instanceof IDBRoute){
			routeClassMap.put(key, routeClazzBean);
			routeRuleConfigMap.put(key, routeConfig);
		}else{
			throw new RuntimeException("routeClazz["+routeClass+"]  muste be implements interface of IDBRoute ");
		}
	}
	
	public <E extends IDBRoute> E getRoute(String key){
		Class routeClazzBean = routeClassMap.get(key);
		if(null == routeClazzBean){
			return null;
		}
		IDBRoute route = null;
		try {
			route = (IDBRoute) routeClazzBean.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("getRoute("+key+") has error ",e);
		} 
		
		String routeConfig = routeRuleConfigMap.get(key);
		
		if(route instanceof AbstractRoute ){
			try {
				((AbstractRoute)route).parseRouteConfig(routeConfig);
			} catch (Exception e) {
				throw new RuntimeException("routeConfig["+routeConfig+"] has error ",e);
			}
		}
		
		return (E) route;
	}
	
	public String getRouteRuleConfig(String key){
		return routeRuleConfigMap.get(key);
	}

	public void setDbRoutePropertiesMap(Map<String, String> dbRoutePropertiesMap) {
		this.dbRoutePropertiesMap = dbRoutePropertiesMap;
	}
	
}
