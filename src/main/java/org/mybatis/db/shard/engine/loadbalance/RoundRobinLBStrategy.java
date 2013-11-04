package org.mybatis.db.shard.engine.loadbalance;

import java.util.List;

/**
 * 
 * @ClassName: RoundRobinLBPolicy 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-10-31 下午9:58:08 
 *
 */
public class RoundRobinLBStrategy<T> implements LBStrategy<T> {

	/**
	 * 
	 */
	private int writeCursor = -1;
	
	/**
	 * 
	 */
	private int readCursor  = -1;
	
	/**
	 * 
	 */
	public T nextWrite(List<T> writeableGroup) {
		
		if(writeCursor >= writeableGroup.size() || writeCursor < 0){
			writeCursor = 0;
		}
		return writeableGroup.get(writeCursor++);
	}
	

	/**
	 * 
	 */
	public T nextRead(List<T> readableGroup) {
		if(readCursor >= readableGroup.size() || readCursor < 0){
			readCursor = 0;
		}
		return readableGroup.get(readCursor++);
	}

}
