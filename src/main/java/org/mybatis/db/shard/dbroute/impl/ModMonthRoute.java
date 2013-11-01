package org.mybatis.db.shard.dbroute.impl;

import java.util.Calendar;

/**
 * 
 * @ClassName: ModMonthRoute 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-10-29 下午4:07:09 
 *
 */
public class ModMonthRoute extends ModRoute {

	private int monthNum;
	
	public ModMonthRoute(){
		
	}

	public String getTableName() {
		if(this.monthNum < 1 || this.monthNum > 12){
			monthNum = Calendar.getInstance().get(Calendar.MONTH)+1;
		}
		return super.getTableName()+ String.format("_%02d", monthNum);
	}
	
	
	public int getMonthNum() {
		return monthNum;
	}

	public void setMonthNum(int monthNum) {
		this.monthNum = monthNum;
	}

}
