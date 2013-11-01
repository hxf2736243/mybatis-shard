package org.mybatis.db.shard.dbroute.test;

import java.text.DecimalFormat;

import org.mybatis.db.shard.dbroute.impl.ModRoute;

public class ModRouteTest {

	/** 
	 * @Title: main 
	 * @Description: TODO
	 * @param @param args 
	 * @return void
	 * @throws 
	 */
	public static void main(String[] args) {
		//分表分库
		ModRoute m=new ModRoute();
		try {
			m.parseRouteConfig("user_info.id%16={0~7:user1;8~15:user2}#_%04d");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < 16; i++) {
			m.setRouteId(i);
			System.out.println( m.getTableName() +"--->"+ m.getDBGroupName() );
		}
		
		//只分库不分表
		ModRoute m2=new ModRoute();
		try {
			m2.parseRouteConfig("user_info.id%16={0~7:user1;8~15:user2}");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < 16; i++) {
			m2.setRouteId(i);
			System.out.println( m2.getTableName() +"--->"+ m2.getDBGroupName() );
		}
		
	}

}
