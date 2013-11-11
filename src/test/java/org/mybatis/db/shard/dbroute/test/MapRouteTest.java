package org.mybatis.db.shard.dbroute.test;

import java.util.Map;

import org.mybatis.db.shard.dbroute.impl.MapRoute;

/**
 * 
 * @ClassName: MapRouteTest 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-10-30 下午1:57:09 
 *
 */
public class MapRouteTest {

public static void main(String[] args) throws Exception {
//	final String ruleConfig = "user_info.unitPosId={beijing:user_bj;nanchang:user_nc}#_%s";
//	MapRoute mapRoute = new MapRoute();
//	mapRoute.parseRouteConfig(ruleConfig);
//	
//	System.out.println("分表分库配置"+ruleConfig);
//	for (Map.Entry<String, String> e: mapRoute.getRouteRuleMap().entrySet()) {
//		mapRoute.setRouteId(e.getKey());
//		
//		System.out.print(String.format("routeId = %s -->",e.getKey()));
//		System.out.print(String.format("TableName   is %s ", mapRoute.getTableName()));
//		System.out.print(String.format("DBGroupName is %s", mapRoute.getDBGroupName()));
//		System.out.println();
//	}
//	
//	
//	final String ruleConfig2 = "user_info.unitPosId={beijing:user_bj;nanchang:user_nc}";
//	MapRoute mapRoute2 = new MapRoute();
//	mapRoute2.parseRouteConfig(ruleConfig2);
//	
//	System.out.println("分表分库配置"+ruleConfig2);
//	for (Map.Entry<String, String> e: mapRoute2.getRouteRuleMap().entrySet()) {
//		mapRoute2.setRouteId(e.getKey());
//		
//		System.out.print(String.format("routeId = %s -->",e.getKey()));
//		System.out.print(String.format("TableName   is %s ", mapRoute2.getTableName()));
//		System.out.print(String.format("DBGroupName is %s",  mapRoute2.getDBGroupName()));
//		System.out.println();
//	}
//	
//	
//	final String ruleConfig3 = "unitPosId={beijing:user_bj;nanchang:user_nc}";
//	MapRoute mapRoute3 = new MapRoute();
//	mapRoute3.parseRouteConfig(ruleConfig3);
//	
//	System.out.println("分表分库配置"+ruleConfig3);
//	for (Map.Entry<String, String> e: mapRoute3.getRouteRuleMap().entrySet()) {
//		mapRoute3.setRouteId(e.getKey());
//		
//		System.out.print(String.format("routeId = %s -->",e.getKey()));
//		System.out.print(String.format("TableName   is \"%s\" ", mapRoute3.getTableName()));
//		System.out.print(String.format("DBGroupName is \"%s\"",  mapRoute3.getDBGroupName()));
//		System.out.println();
//	}
	
	
	
	final String ruleConfig4 = "unitPosId={beijing:user_bj;nanchang:user_nc}#_%s";
	MapRoute mapRoute4 = new MapRoute();
	mapRoute4.parseRouteConfig(ruleConfig4);
	
	System.out.println("分表分库配置"+ruleConfig4);
	for (Map.Entry<String, String> e: mapRoute4.getRouteRuleMap().entrySet()) {
		mapRoute4.setRouteId(e.getKey());
		
		System.out.print(String.format("routeId = %s -->",e.getKey()));
		System.out.print(String.format("TableName   is \"%s\" ", mapRoute4.getTableName()));
		System.out.print(String.format("DBGroupName is \"%s\"",  mapRoute4.getDBGroupName()));
		System.out.println();
	}
}
}
