package org.mybatis.db.shard.datasourse;

import java.util.List;

import org.mybatis.db.shard.common.Constant;
import org.mybatis.db.shard.engine.interfaces.IDataSourceKeyRouter;
import org.mybatis.db.shard.engine.interfaces.IShardThreadContext;
import org.mybatis.db.shard.engine.loadbalance.LBStrategy;
import org.mybatis.db.util.Assert;
/**
 * 
 * @ClassName: DataSourceKeyRouter 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-11-1 下午2:23:17 
 *
 */
public class DataSourceKeyRouter implements IDataSourceKeyRouter<String> {

	IShardThreadContext shardContext;
	
	LBStrategy lBStrategy;
	
	/**
	 * 写库的数据库名称列表<br>
	 * 只有在ReadWriteGroupDataSource初始化的时候初始化。<br>
	 * 其余都是读操作，故不存在线程安全问题。<br>
	 */
	private List<String> writeDataSourceKeys ;
	
	/**
	 * 读库的数据库名称列表<br>
	 * 只有在ReadWriteGroupDataSource初始化的时候初始化。<br>
	 * 其余都是读操作，故不存在线程安全问题。<br>
	 */
	private List<String> readDataSourceKeys  ;
	
	public String getKey() {
		if(shardContext.get(Constant.CURRENT_MAPPER_IS_READ)){
			return lBStrategy.nextRead(readDataSourceKeys).toString();
		}
		return lBStrategy.nextWrite(writeDataSourceKeys).toString();
	}
	

	public void setReadKeys(List<String> readKeys) {
		Assert.notEmpty(readKeys);
		readDataSourceKeys = readKeys;
	}

	public void setWriteKeys(List<String> writeKeys) {
		Assert.notEmpty(writeKeys);
		writeDataSourceKeys = writeKeys;
	}

	
	public IShardThreadContext getShardContext() {
		return shardContext;
	}

	public void setShardContext(IShardThreadContext shardContext) {
		this.shardContext = shardContext;
	}

	public LBStrategy getlBStrategy() {
		return lBStrategy;
	}

	public void setlBStrategy(LBStrategy lBStrategy) {
		this.lBStrategy = lBStrategy;
	}

}
