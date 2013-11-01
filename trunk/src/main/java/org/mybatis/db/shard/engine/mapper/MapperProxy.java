package org.mybatis.db.shard.engine.mapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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

    // 目标对象   
    private Object invokedMapper;
    
    // 线程上下文   
    private IShardThreadContext context;
    
    
    public MapperProxy(Object invokedMapper,IShardThreadContext context) {  
        super();  
        this.invokedMapper = invokedMapper;  
        this.context = context;
    }  

    
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		context.put(Constant.CURRENT_MAPPER_ID, getMapperId(proxy,method));
        return method.invoke(invokedMapper, args);  
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
    
}
