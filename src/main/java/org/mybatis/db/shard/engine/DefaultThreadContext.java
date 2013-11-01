package org.mybatis.db.shard.engine;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.mybatis.db.shard.engine.interfaces.IShardThreadContext;

/**
 * 
 * @ClassName: DefaultThreadContext 
 * @Description: 默认的线程上下文实现
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-10-31 下午8:39:28 
 *
 */
public class DefaultThreadContext implements IShardThreadContext {
	
	private ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

	public boolean contains(String key) {
		return this.getContainer().containsKey(key);
	}

	public <E> E remove(String key) {
		return (E) this.getContainer().remove(key);
	}

	public <E> E get(String key) {
		return (E) this.getContainer().get(key);
	}

	public <E> E put(String key, E value) {
		return (E) this.getContainer().put(key, value);
	}

	
	public void destory() {
		this.getContainer().clear();
		this.getThreadLocal().set(null);
	}
	

	private ThreadLocal<Map<String, Object>> getThreadLocal() {
		if (threadLocal.get() == null) {
			Map<String, Object> map = new ConcurrentHashMap<String, Object>();
			threadLocal.set(map);
		}
		return threadLocal;
	}
	
    private  Map<String, Object> getContainer() {
        return (Map<String, Object>) getThreadLocal().get();
    }
}
