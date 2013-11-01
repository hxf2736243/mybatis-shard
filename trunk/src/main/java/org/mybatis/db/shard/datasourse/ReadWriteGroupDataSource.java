package org.mybatis.db.shard.datasourse;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.db.shard.datasourse.interfaces.ReadWriteAble;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.ReflectionUtils;

/**
 * 
 * @ClassName: ReadWriteGroupDataSource 
 * @Description: 读写分离集群数据源
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-10-31 下午4:18:28 
 *
 */
public class ReadWriteGroupDataSource extends AbstractRoutingDataSource {

	/**
	 * 写库的数据库名称列表<br>
	 * 只有在ReadWriteGroupDataSource初始化的时候初始化。<br>
	 * 其余都是读操作，故不存在线程安全问题。<br>
	 * 用ArrayList即可，ArrayList通过index可以加速查找Key<br>
	 */
	private List<String> writeAbleDataSourceKeys = new ArrayList<String>();
	
	/**
	 * 读库的数据库名称列表<br>
	 * 只有在ReadWriteGroupDataSource初始化的时候初始化。<br>
	 * 其余都是读操作，故不存在线程安全问题。<br>
	 * 用ArrayList即可，ArrayList通过index可以加速查找Key<br>
	 */
	private List<String> readAbleDataSourceKeys = new ArrayList<String>();

	
	@Override
	protected Object determineCurrentLookupKey() {
		return null;
	}
	
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		
		Field targetDataSourcesField = ReflectionUtils.findField(this.getClass(), "targetDataSources");
		
		Map<Object, Object> targetDataSources = (Map<Object, Object>)ReflectionUtils.getField(targetDataSourcesField, this);
		
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
		if(null == targetDataSources){
			return;
		}
		
		for(Map.Entry<Object, Object> entry : targetDataSources.entrySet()){
			Object key = entry.getKey();
			Object value = entry.getValue();
			
			if(value instanceof ReadWriteAble){
				ReadWriteAble readWriteAbleValue = (ReadWriteAble)value;
				if(readWriteAbleValue.isCanReadable()){
					readAbleDataSourceKeys.add(key.toString());
				}
				if(readWriteAbleValue.isCanWriteable()){
					writeAbleDataSourceKeys.add(key.toString());
				}
			}else{
				readAbleDataSourceKeys.add(key.toString());
				writeAbleDataSourceKeys.add(key.toString());
			}
		}
	}
}
