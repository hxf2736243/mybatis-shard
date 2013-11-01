package org.mybatis.db.shard.engine.loadbalance;

import java.util.List;

/**
 * 
 * @ClassName: LBPolicy 
 * @Description: 负载均衡接口
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-10-31 下午9:51:12 
 *
 */
public interface LBStrategy<T> {
	
	/**
	 * 
	 * @Title: nextWrite 
	 * @Description: 获取下一个写库
	 * @param writeableGroup
	 * @return 
	 * T
	 * @throws
	 */
	T nextWrite(List<T> writeableGroup);
	
	/**
	 * 
	 * @Title: nextRead 
	 * @Description: 获取下一个读库
	 * @param readableGroup
	 * @return 
	 * T
	 * @throws
	 */
	T nextRead(List<T> readableGroup);
	
}
