package org.mybatis.db.shard.dbroute.test;

import org.mybatis.db.shard.dbroute.impl.ModRoute;

public class ModRouteTest {

	/**
	 * @throws Exception  
	 * @Title: main 
	 * @Description: TODO
	 * @param @param args 
	 * @return void
	 * @throws 
	 */
public static void main(String[] args) throws Exception {
	//分表分库
	ModRoute modRoute = new ModRoute();
	System.out.println("分表分库配置user_info.id%4={0~1:user1;2~3:user2}#_%04d:");
	modRoute.parseRouteConfig("user_info.id%4={0~1:user1;2~3:user2}#_%04d");
	for (int i = 0; i < 8; i++) {
		modRoute.setRouteId(i);
		System.out.print(String.format("id = %02d -->",i));
		System.out.print(String.format("TableName   is %s ", modRoute.getTableName()));
		System.out.print(String.format("DBGroupName is %s", modRoute.getDBGroupName()));
		System.out.println();
	}
	
	System.out.println("只分库不分表配置user_info.id%4={0~1:user1;2~3:user2}:");
	//只分库不分表
	ModRoute modRoute2=new ModRoute();
	modRoute2.parseRouteConfig("user_info.id%4={0~1:user1;2~3:user2}");
	for (int i = 0; i < 8; i++) {
		modRoute2.setRouteId(i);
		System.out.print(String.format("id = %02d -->",i));
		System.out.print(String.format("TableName   is %s ", modRoute2.getTableName()));
		System.out.print(String.format("DBGroupName is %s", modRoute2.getDBGroupName()));
		System.out.println();
	}
	
}
	
	

}
