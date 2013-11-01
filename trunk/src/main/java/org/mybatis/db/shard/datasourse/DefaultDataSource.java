package org.mybatis.db.shard.datasourse;

import org.mybatis.db.shard.datasourse.interfaces.ReadWriteAble;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * 
 * @ClassName: DefaultDataSource 
 * @Description: 实现了 ReadWriteAble 默认数据源
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-10-31 下午5:11:18 
 *
 */
public class DefaultDataSource extends DriverManagerDataSource implements ReadWriteAble {

	/**
	 * 标示数据源是否可读；默认可读，值为：“true”
	 */
	protected boolean canReadable = true;

	/**
	 * 标示数据源是否可写；默认可写，值为：“true”
	 */
	protected boolean canWriteable = true;
	
	public boolean isCanReadable() {
		return canReadable;
	}
	
	public boolean isCanWriteable() {
		return canWriteable;
	}

	public void setCanReadable(boolean canReadable) {
		this.canReadable = canReadable;
	}

	public void setCanWriteable(boolean canWriteable) {
		this.canWriteable = canWriteable;
	}

	
}
