package org.mybatis.db.shard.dbroute.tutorial01.mians;

import org.mybatis.db.shard.dbroute.tutorial01.dao.UserDao;

public class Tutorial01Main {

	/** 
	 * @Title: main 
	 * @Description: TODO
	 * @param @param args 
	 * @return void
	 * @throws 
	 */
	public static void main(String[] args) {
		UserDao userDao = new UserDao();
		for(long id =0L;id<10L;id++){
			System.out.print("id为"+id+"执行的");
			userDao.selectById_withRouteParam(id);
		}
	}

}
