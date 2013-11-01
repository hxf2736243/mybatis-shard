package org.mybatis.db.shard.spring.datasource;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.mybatis.db.shard.datasourse.interfaces.ReadWriteAble;
import org.mybatis.db.shard.engine.interfaces.IDataSourceKeyRouter;
import org.mybatis.db.util.ForceReflectionUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 
 * @ClassName: ReadWriteGroupDataSource 
 * @Description: 读写分离集群数据源
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-10-31 下午4:18:28 
 *
 */
public class ReadWriteGroupDataSource extends AbstractRoutingDataSource {

	private IDataSourceKeyRouter<String> dataSourceKeyRouter;
	
	@Override
	protected Object determineCurrentLookupKey() {
		String dbKey =dataSourceKeyRouter.getKey();
		System.out.println("--info---> dataSourceKeyRouter ReadWriteGroupDataSource["+dbKey+"] routed tagert is ");
		return dbKey;
	}
	
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		
		Map<Object, Object> targetDataSources = (Map<Object, Object>)ForceReflectionUtils.getFieldValue(this, "targetDataSources");
		
		this.classifyReadWriteDataSourceKeys(targetDataSources);
	}
	
	/**
	 * 
	 * @Title: classifyReadWriteDataSource 
	 * @Description: 将配置的数据库集群配置按照读写性质分离<br>
	 * 如果配置的数据源没有读写属性（没实现ReadWriteAble接口）那么读写列表中都登记下。<br>
	 * 这样设置主要是当前配置人没有读写分离的想法,那么读写其实都是一致的
	 * @param @param targetDataSources 
	 * @return void
	 * @throws
	 */
	private void classifyReadWriteDataSourceKeys(Map<Object, Object> targetDataSources){
		if(null == targetDataSources || targetDataSources.isEmpty()){
			return;
		}
		List<String> writeKeys = new LinkedList<String>();	
		List<String> readKeys = new LinkedList<String>();
		for(Map.Entry<Object, Object> entry : targetDataSources.entrySet()){
			Object key = entry.getKey();
			Object value = entry.getValue();
			
			if(value instanceof ReadWriteAble){
				ReadWriteAble readWriteAbleValue = (ReadWriteAble)value;
				if(readWriteAbleValue.isCanReadable()){
					readKeys.add(key.toString());
				}
				if(readWriteAbleValue.isCanWriteable()){
					writeKeys.add(key.toString());
				}
			}else{
				readKeys.add(key.toString());
				writeKeys.add(key.toString());
			}
		}
		dataSourceKeyRouter.setReadKeys(readKeys);
		dataSourceKeyRouter.setWriteKeys(writeKeys);
	}

	public void setDataSourceKeyRouter(
			IDataSourceKeyRouter<String> dataSourceKeyRouter) {
		this.dataSourceKeyRouter = dataSourceKeyRouter;
	}
}
