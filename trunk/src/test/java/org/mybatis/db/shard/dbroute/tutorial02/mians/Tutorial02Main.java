package org.mybatis.db.shard.dbroute.tutorial02.mians;

import org.mybatis.db.shard.dbroute.tutorial02.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @ClassName: Tutorial02Main 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-11-1 下午11:12:43 
 *
 */
public class Tutorial02Main {

	/** 
	 * @Title: main 
	 * @Description: TODO
	 * @param @param args 
	 * @return void
	 * @throws 
	 */
	public static void main(String[] args) {
        ApplicationContext ctx=null;  
        
        ctx=new ClassPathXmlApplicationContext("org/mybatis/db/shard/dbroute/tutorial02/config/applicationContext.xml");  
        
        UserDao userDao= (UserDao) ctx.getBean("userDao");
        for(long i=0 ; i<6 ; i++){
        	userDao.selectById(i);
        	userDao.deleteById(i);
        	System.out.println("\n");
        }
        
	}

}
