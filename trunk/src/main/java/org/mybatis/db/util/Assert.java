package org.mybatis.db.util;

import java.util.Collection;

/**
 * 
 * @ClassName: Assert 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-11-1 下午3:44:59 
 *
 */
public abstract class Assert {

	public static void notEmpty(Collection collec){
			notEmpty(collec,null);
	}
	
	public static void notEmpty(Collection collec,String errorMsg){
		if(null ==  collec || collec.isEmpty()){
			if(null == errorMsg) errorMsg ="Collection is Empty";
			throw new RuntimeException(errorMsg);
		}
	}
}
