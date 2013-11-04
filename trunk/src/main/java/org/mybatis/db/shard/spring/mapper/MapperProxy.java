package org.mybatis.db.shard.spring.mapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.Configuration;
import org.mybatis.db.shard.common.Constant;
import org.mybatis.db.shard.common.SymbolConstant;
import org.mybatis.db.shard.engine.interfaces.IShardThreadContext;

/**
 * 
 * @ClassName: MapperInvocationHandler 
 * @Description: Mapper的动态代理类，此类的作用是
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-10-30 下午10:05:44 
 *
 */
public class MapperProxy  implements InvocationHandler {
	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
    // 线程上下文   
    private IShardThreadContext shardContext;
    
    // 目标对象   
    private Object invokedMapper;
    
    private Configuration sqlSessionconfig;

    public MapperProxy(Object invokedMapper) {  
        super();  
        this.invokedMapper = invokedMapper;  
    }  

    
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		try{
			this.initCurrentMapperReadWiite(proxy, method);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
        return method.invoke(invokedMapper, args);  
	} 
	
	private void initCurrentMapperReadWiite(Object proxy, Method method){
		String currentMapperId = getMapperId(proxy,method);
		shardContext.put(Constant.CURRENT_MAPPER_ID, currentMapperId);
		
		MappedStatement currentMappedStatement= sqlSessionconfig.getMappedStatement(currentMapperId);
		SqlCommandType sqlType = currentMappedStatement.getSqlCommandType();
		shardContext.put(Constant.CURRENT_MAPPER_IS_READ, SqlCommandType.SELECT.equals(sqlType));
		if (logger.isInfoEnabled()) {
			logger.info("MapperProxy current mapper id :["+ currentMapperId+"]");
			logger.info("MapperProxy SQL is read :["+ SqlCommandType.SELECT.equals(sqlType)+"]");
		}

	}
	
	
	public String getMapperId(Object proxy, Method method){
		return invokedMapper.getClass().getInterfaces()[0].getName()+SymbolConstant.SYMBOL_PERIOD+method.getName();
	}
	
    /** 
     * 获取目标对象的代理对象 
     * @return 代理对象 
     */  
    public <E> E getProxy() {  
        return (E) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),   
        		invokedMapper.getClass().getInterfaces(), this);  
    }


	public IShardThreadContext getShardContext() {
		return shardContext;
	}

	public void setShardContext(IShardThreadContext shardContext) {
		this.shardContext = shardContext;
	}


	public Configuration getSqlSessionconfig() {
		return sqlSessionconfig;
	}


	public void setSqlSessionconfig(Configuration sqlSessionconfig) {
		this.sqlSessionconfig = sqlSessionconfig;
	}  
    
}
