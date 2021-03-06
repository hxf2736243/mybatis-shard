package org.mybatis.db.shard.spring.mapper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.db.shard.engine.interfaces.IShardThreadContext;
import org.mybatis.db.shard.engine.mapper.IMapperManager;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * @ClassName: DefaultMapperManager 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
  * @date 2013-10-31 上午 02:53:39 
 *
 */
public class MapperManager implements IMapperManager,ApplicationContextAware{

	private ApplicationContext appContext ;
	
	private Map<String,Object> mapperContainer = new ConcurrentHashMap<String,Object>();
	
	private IShardThreadContext shardContext;
	
    private SqlSessionFactory sqlSessionFactory;
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.appContext = applicationContext;
	}

	public <E> E getMapper(String mpperId) {
		if(mapperContainer.containsKey(mpperId)){
			return (E) mapperContainer.get(mpperId);
		}
		
		MapperProxy mpperProxy = new MapperProxy(appContext.getBean(mpperId));
		mpperProxy.setShardContext(shardContext);
		mpperProxy.setSqlSessionconfig(sqlSessionFactory.getConfiguration());
		
		E mpper = mpperProxy.getProxy();
		mapperContainer.put(mpperId,mpper);
		
		return mpper;
	}

	public IShardThreadContext getShardContext() {
		return shardContext;
	}

	public void setShardContext(IShardThreadContext shardContext) {
		this.shardContext = shardContext;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
}
