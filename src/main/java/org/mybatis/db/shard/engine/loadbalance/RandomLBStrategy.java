package org.mybatis.db.shard.engine.loadbalance;

import java.util.List;
import java.util.Random;

public class RandomLBStrategy<T> implements LBStrategy<T> {

	
	private  Random rm = new Random();
	
	
	public T nextWrite(List<T> writeableGroup) {
		return writeableGroup.get(rm.nextInt(writeableGroup.size()+1));
	}

	
	public T nextRead(List<T> readableGroup) {
		return readableGroup.get(rm.nextInt(readableGroup.size()+1));
	}

}
