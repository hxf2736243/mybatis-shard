package org.mybatis.db.shard.dbroute.interfaces;

/**
 * 
 * @ClassName: IDBRoute
 * @Description: 分库分表路由接口，此接口负责对外暴露 DBGroupName和tableName<br>
 * @author huhailiang(javadeeper@gmail.com)
 * @date 2013-10-27 8:03:04
 * 
 */
public interface IDBRoute {

	/**
	 * 
	 * @Title: getDBName
	 * @Description: 获取数据库集群名称，如果不是集群此处是库名
	 * @return String
	 * @throws
	 */
	String getDBGroupName();

	/**
	 * 
	 * @Title: getTableName
	 * @Description: 获取表名称
	 * @return String
	 * @throws
	 */
	String getTableName();

}
