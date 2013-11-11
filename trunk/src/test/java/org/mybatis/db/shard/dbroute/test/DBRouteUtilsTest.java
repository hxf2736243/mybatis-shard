package org.mybatis.db.shard.dbroute.test;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.db.shard.dbroute.impl.DBRouteUtils;

public class DBRouteUtilsTest {

	/** 
	 * @Title: main 
	 * @Description: TODO
	 * @param args 
	 * void
	 * @throws 
	 */
	public static void main(String[] args) {

		String key = "dbRouteUtilsTest.user";
		String conf = "org.mybatis.db.shard.dbroute.impl.ModRoute[id%4={0~1:shard_01;2~3:shard_02}#_%04d]";
		Map<String,String> por = new HashMap<String,String>();
		por.put(key,conf);
		
		DBRouteUtils routeUtils = new DBRouteUtils();
		routeUtils.setDbRoutePropertiesMap(por);
		routeUtils.init();
		
		System.out.println(routeUtils.getRoute(key));
	}

}
