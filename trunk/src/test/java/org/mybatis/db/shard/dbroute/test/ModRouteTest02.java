package org.mybatis.db.shard.dbroute.test;

import org.mybatis.db.shard.dbroute.impl.ModRoute;

public class ModRouteTest02 {

	/**
	 * @throws Exception  
	 * @Title: main 
	 * @Description: TODO
	 * @param args 
	 * void
	 * @throws 
	 */
public static void main(String[] args) throws Exception {
	ModRoute modRoute = new ModRoute();
	modRoute.parseRouteConfig("user_info.id%16={0~7:user1;8~15:user2}#_%04d");
	
	modRoute.setRouteId(246L);
	System.out.println(String.format("246L -> TableName   is : %s ",modRoute.getTableName()));
	System.out.println(String.format("246L -> DBGroupName is : %s ",modRoute.getDBGroupName()));
	
	modRoute.setRouteId(249L);
	System.out.println(String.format("249L -> TableName   is : %s ",modRoute.getTableName()));
	System.out.println(String.format("249L -> DBGroupName is : %s ",modRoute.getDBGroupName()));
}

}
