package org.mybatis.db.shard.dbroute.interfaces;

/**
 * 
 * @ClassName: IRWRoute 
 * @Description: TODO
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-11-4 下午12:02:36 
 *
 */
public interface IRWRoute {
	
	/**
	 * @Title: isGoReadGroup 
	 * @Description: TODO
	 * @return 
	 * boolean
	 * @throws
	 */
	public boolean isGoReadGroup();
	
	/**
	 * 
	 * @Title: setGoReadGroup 
	 * @Description: TODO
	 * @param isGoReadGroup 
	 * void
	 * @throws
	 */
	public void setGoReadGroup(boolean isGoReadGroup);
}
