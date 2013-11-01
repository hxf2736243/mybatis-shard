package org.mybatis.db.shard.dbroute.tutorial02.mians;

import org.mybatis.db.shard.dbroute.tutorial02.dao.UserMapper;
import org.mybatis.db.shard.engine.interfaces.MapperManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        
        MapperManager mapperManager =  (MapperManager)ctx.getBean("mapperManager");
        
        UserMapper userMapper= mapperManager.getMapper("userMapper"); 
        
        for(int i=0 ; i<10 ; i++){
        	 System.out.println(userMapper.selectById(1L)); 
        }
        
	}

}
