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

	public static void main(String[] args) {
		
		
		
		MapRoute m=new MapRoute();
		try {
			m.parseRouteConfig("user_info.unitPosId={beijing:user_nanchang;nanchang:user1;101:user2;XXXXX:userxxx}#_%s");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Map.Entry<String, String> e: m.getRouteRuleMap().entrySet()) {
				m.setRouteId(e.getKey());
				System.out.println( m.getTableName() +"--->"+ m.getDBGroupName() );
		}
		
	}
}
