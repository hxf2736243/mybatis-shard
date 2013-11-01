package org.mybatis.db.shard.dbroute.test;

import org.mybatis.db.shard.dbroute.impl.ModMonthRoute;


public class ModMonthRouteTest {
	/** 
	 * @Title: main 
	 * @Description: TODO
	 * @param @param args 
	 * @return void
	 * @throws 
	 */
	public static void main(String[] args) {
		ModMonthRoute route= new ModMonthRoute();
		try {
			route.parseRouteConfig("user_info.id%16={0~7:user1;8~15:user2}#_%04d");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 16; i++) {
			route.setRouteId(i);
			route.setMonthNum(i);
			System.out.println( route.getTableName() +"--->"+ route.getDBGroupName() );
		}
	}

}