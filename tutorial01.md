# 教程01-ModRoute设计与使用 #
本教程介绍此框架的第一个教程，也是入门教程。主要介绍如何使用一个现实了IRoute接口的ModRoute类。<br /> 此类设计和实现依赖没任何Mybatis和Spring的接口。从这个简单实现你可以看出此框架基石的端倪。先看看如下程序：
```
public static void main(String[] args) throws Exception {
	ModRoute modRoute = new ModRoute();
	modRoute.parseRouteConfig("user_info.id%16={0~7:user1;8~15:user2}#_%04d");
	
	modRoute.setRouteId(246L);
	System.out.println(String.format("246L -> TableName   is : %s ",modRoute.getTableName()));
	System.out.println(String.format("246L -> DBGroupName is : %s ",modRoute.getDBGroupName()));
	
	modRoute.setRouteId(249L);
	System.out.println(String.format("249L -> TableName   is : %s ",modRoute.getTableName()));
	System.out.println(String.format("249L -> DBGroupName is : %s ",modRoute.getDBGroupName()));
}
```
上面程序得到的结果是：
```
246L -> TableName   is : user_info_0006 
246L -> DBGroupName is : user1 
249L -> TableName   is : user_info_0009 
249L -> DBGroupName is : user2 
```
TableName 表示是表名，DBGroupName可以暂时理解为库名（后面教程会详细说明）。<br />
如果你对上述程序很面熟或者很好懂，那么下面的介绍可以跳过。

# 本教程章节： #
![http://img02.taobaocdn.com/bao/uploaded/i2/14839030322003106/T1Ze7AFmNaXXXXXXXX_!!723064839-2-pix.png_300x300.jpg](http://img02.taobaocdn.com/bao/uploaded/i2/14839030322003106/T1Ze7AFmNaXXXXXXXX_!!723064839-2-pix.png_300x300.jpg)
# 详细介绍如下： #
## 1、为何要分库分表 ##
当今互联网处理大流量的三板斧：
  * 缓存系统
  * 数据库集群系统
  * 负载均衡系统
当然还有其他小板斧，但这三个是主要的（如果不对请各位见谅）。<br />BAT（baidu/Alibaba/Tencent）三个寡头都有自己的一套利器和标准，但原理都是一样的。概括一句话：划分而治。<br />貌似扯远了，回归正题三板斧中有一板斧叫“**数据库集群系统**”，<br />其基本思想是基就要把一个数据库切分成多个部分放到不同的数据库(server)上，从而缓解单一数据库的性能问题。<br />其中有数据库横切（水平切分）和纵切（垂直切分），关于切分在CSDN有相关同志已经写了相当详细的介绍，如：[数据库分库分表系列](http://blog.csdn.net/column/details/sharding.html)。<br />每个业务都有不同的设计来应对各种复杂的环境和流量，本处举个简单的实例：
| **图一** | **图二** |
|:-----------|:-----------|
| ![http://img01.taobaocdn.com/bao/uploaded/i1/14839030178111439/T1MxIXFkddXXXXXXXX_!!723064839-2-pix.png_300x300.jpg](http://img01.taobaocdn.com/bao/uploaded/i1/14839030178111439/T1MxIXFkddXXXXXXXX_!!723064839-2-pix.png_300x300.jpg) | ![http://img02.taobaocdn.com/bao/uploaded/i2/14839030173082978/T1.EgwFc8aXXXXXXXX_!!723064839-2-pix.png_300x300.jpg](http://img02.taobaocdn.com/bao/uploaded/i2/14839030173082978/T1.EgwFc8aXXXXXXXX_!!723064839-2-pix.png_300x300.jpg) |
<br />
图一图二所表示的就是最常用的分库分表方式，<br />图例用id对128取模，模在0到63在user\_0上,落在63到127区间就在user\_1库上，<br />而分表就是落在名为{user\_模}表上。<br />取模能在一定程度上保质数据均匀散列在各个分表上。从而将数据库的压力分散到各个库表中。<br />

## 2、设计思想、继承关系 ##
关于路由信息的接口有一下几个：IRWRoute、IDBRoute、IRoute、AbstractRoute<br />
默认实现有：ModRoute、MapRoute。具体如图：<br />


![http://img04.taobaocdn.com/bao/uploaded/i4/14839042866824365/T1UxEQFjhdXXXXXXXX_!!723064839-2-pix.png_570x10000.jpg](http://img04.taobaocdn.com/bao/uploaded/i4/14839042866824365/T1UxEQFjhdXXXXXXXX_!!723064839-2-pix.png_570x10000.jpg)<br />

## 3、配置说明、举例 ##
此处的配置说明是解释ModRoute默认实现的说明，如果不满足应该需求你可以自行修改<br />
“user\_info.id%16={0~7:user1;8~15:user2}#_%04d”此路由配置是作为我解释的一个实例说明。_<br /> 上述配置表示user\_info表字段id对16取模，得到结果如果是0到7在库user1上，8到15在库user2上,分表名结尾以模数4位补齐，<br />
（1）上述规则表名非必配，可如下：<br />
`id%16={0~7:user1;8~15:user2}#_%04d`<br />
modRoute.getTableName()得到的表名是“_0000”格式类型的分表结尾符。_

（2）分表名结尾格式符非必配，可如下：<br />
`user_info.id%16={0~7:user1;8~15:user2`}<br />
modRoute.getTableName()得到的表名永远是“user\_info”。<br />
或<br />
`id%16={0~7:user1;8~15:user2`}<br />
modRoute.getTableName()得到的表名永远是“”。

## 4、使用说明、举例 ##
在[代码](http://code.google.com/p/mybatis-shard/source/browse/trunk/src/test/java/org/mybatis/db/shard/dbroute/test/ModRouteTest.java)
详细如下：
```
public static void main(String[] args) throws Exception {
	//分表分库
	ModRoute modRoute = new ModRoute();
	System.out.println("分表分库配置user_info.id%4={0~1:user1;2~3:user2}#_%04d:");
	modRoute.parseRouteConfig("user_info.id%4={0~1:user1;2~3:user2}#_%04d");
	for (int i = 0; i < 8; i++) {
		modRoute.setRouteId(i);
		System.out.print(String.format("id = %02d -->",i));
		System.out.print(String.format("TableName   is %s ", modRoute.getTableName()));
		System.out.print(String.format("DBGroupName is %s", modRoute.getDBGroupName()));
		System.out.println();
	}
	
	System.out.println("只分库不分表配置user_info.id%4={0~1:user1;2~3:user2}:");
	//只分库不分表
	ModRoute modRoute2=new ModRoute();
	modRoute2.parseRouteConfig("user_info.id%4={0~1:user1;2~3:user2}");
	for (int i = 0; i < 8; i++) {
		modRoute2.setRouteId(i);
		System.out.print(String.format("id = %02d -->",i));
		System.out.print(String.format("TableName   is %s ", modRoute2.getTableName()));
		System.out.print(String.format("DBGroupName is %s", modRoute2.getDBGroupName()));
		System.out.println();
	}
	
}
```

得到的结果：
```
分表分库配置user_info.id%4={0~1:user1;2~3:user2}#_%04d:
id = 00 -->TableName   is user_info_0000 DBGroupName is user1
id = 01 -->TableName   is user_info_0001 DBGroupName is user1
id = 02 -->TableName   is user_info_0002 DBGroupName is user2
id = 03 -->TableName   is user_info_0003 DBGroupName is user2
id = 04 -->TableName   is user_info_0000 DBGroupName is user1
id = 05 -->TableName   is user_info_0001 DBGroupName is user1
id = 06 -->TableName   is user_info_0002 DBGroupName is user2
id = 07 -->TableName   is user_info_0003 DBGroupName is user2

只分库不分表配置user_info.id%4={0~1:user1;2~3:user2}:
id = 00 -->TableName   is user_info DBGroupName is user1
id = 01 -->TableName   is user_info DBGroupName is user1
id = 02 -->TableName   is user_info DBGroupName is user2
id = 03 -->TableName   is user_info DBGroupName is user2
id = 04 -->TableName   is user_info DBGroupName is user1
id = 05 -->TableName   is user_info DBGroupName is user1
id = 06 -->TableName   is user_info DBGroupName is user2
id = 07 -->TableName   is user_info DBGroupName is user2
```