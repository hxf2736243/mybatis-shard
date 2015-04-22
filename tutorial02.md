# 简介 #
在很多应用中，取模分表分库不能满足需求。例如在仓库管理的应用中，<br />北京的货物需存在在北京的仓库，上海的货物需存在在上海的仓库。<br />再如在CRM系统中，用户信息需分公司编号分库，北京分公司存入北分库，上海分公司存入上海分库等等。那么之前讲诉的分库是不能满足，于是本人编写了MapRoute来应对。

# 映射路由MapRoute详细介绍 #

**配置一：
```
user_info.unitPosId={beijing:user_bj;nanchang:user_nc}


表示user_info对分库字段unitPosId做映射，
映射关系是beijing在库user_bj上，nanchang在库user_nc上
调用MapRoute.getTableName()会带上user_info
```
测试程序：
```
public static void main(String[] args) throws Exception {
	final String ruleConfig2 = "user_info.unitPosId={beijing:user_bj;nanchang:user_nc}";
	MapRoute mapRoute2 = new MapRoute();
	mapRoute2.parseRouteConfig(ruleConfig2);
	
	System.out.println("分表分库配置"+ruleConfig2);
	for (Map.Entry<String, String> e: mapRoute2.getRouteRuleMap().entrySet()) {
		mapRoute2.setRouteId(e.getKey());
		
		System.out.print(String.format("routeId = %s -->",e.getKey()));
		System.out.print(String.format("TableName   is %s ", mapRoute2.getTableName()));
		System.out.print(String.format("DBGroupName is %s",  mapRoute2.getDBGroupName()));
		System.out.println();
	}
}
```
结果：
```
分表分库配置user_info.unitPosId={beijing:user_bj;nanchang:user_nc}
routeId = nanchang -->TableName   is user_info DBGroupName is user_nc
routeId = beijing -->TableName   is user_info DBGroupName is user_bj
```**


**配置二：
```
unitPosId={beijing:user_bj;nanchang:user_nc}


表示对字段unitPosId做映射，
映射关系是beijing在库user_bj上，nanchang在库user_nc上
调用MapRoute.getTableName()不会带上user_info
```
测试程序：
```
public static void main(String[] args) throws Exception {
	final String ruleConfig3 = "	unitPosId={beijing:user_bj;nanchang:user_nc}";
	MapRoute mapRoute3 = new MapRoute();
	mapRoute3.parseRouteConfig(ruleConfig3);
	
	System.out.println("分表分库配置"+ruleConfig3);
	for (Map.Entry<String, String> e: mapRoute3.getRouteRuleMap().entrySet()) {
		mapRoute3.setRouteId(e.getKey());
		
		System.out.print(String.format("routeId = %s -->",e.getKey()));
		System.out.print(String.format("TableName   is \"%s\" ", mapRoute3.getTableName()));
		System.out.print(String.format("DBGroupName is \"%s\"",  mapRoute3.getDBGroupName()));
		System.out.println();
	}
}
```
结果：
```
分表分库配置	unitPosId={beijing:user_bj;nanchang:user_nc}
routeId = nanchang -->TableName   is "" DBGroupName is "user_nc"
routeId = beijing -->TableName   is "" DBGroupName is "user_bj"
```**

**配置三：
```
user_info.unitPosId={beijing:user_bj;nanchang:user_nc}#_%s


表示user_info对分库字段unitPosId做映射，
映射关系是beijing在库user_bj，nanchang在库user_nc，并且分表名是key值结尾
调用MapRoute.getTableName()会带上user_info并以下划线和unitPosId结尾，如：user_info_99
```
测试程序：
```
public static void main(String[] args) throws Exception {
	final String ruleConfig = "user_info.unitPosId={beijing:user_bj;nanchang:user_nc}#_%s";
	MapRoute mapRoute = new MapRoute();
	mapRoute.parseRouteConfig(ruleConfig);
	
	System.out.println("分表分库配置"+ruleConfig);
	for (Map.Entry<String, String> e: mapRoute.getRouteRuleMap().entrySet()) {
		mapRoute.setRouteId(e.getKey());
		
		System.out.print(String.format("routeId = %s -->",e.getKey()));
		System.out.print(String.format("TableName   is %s ", mapRoute.getTableName()));
		System.out.print(String.format("DBGroupName is %s", mapRoute.getDBGroupName()));
		System.out.println();
	}
}
```**

结果：
```
分表分库配置user_info.unitPosId={beijing:user_bj;nanchang:user_nc}#_%s
routeId = nanchang -->TableName   is user_info_nanchang DBGroupName is user_nc
routeId = beijing -->TableName   is user_info_beijing DBGroupName is user_bj
```


