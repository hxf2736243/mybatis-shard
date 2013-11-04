package org.mybatis.db.shard.dbroute.impl;

import org.apache.commons.lang.StringUtils;
import org.mybatis.db.shard.common.SymbolConstant;


/**
 * 
 * @ClassName: MapRoute 
 * @Description: 映射路由分库分表<br>
 * 配置DEMO:<br>
 * 配置：user_info.unitPosId={99:user0;100:user1;101:user2;XXXXX:userxxx}<br>
 * 		表示user_info对分库字段unitPosId做映射，<br>
 * 		映射关系是99在库user0上，100在库user1上，101在库user2上<br>
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-10-29 下午4:40:28 
 *
 */
public class MapRoute extends AbstractRoute {
	
	
	private String routeId;
	
	public String getDBGroupName() {
		return routeRuleMap.get(routeId);
	}
	

	public String getTableName() {
		
		if(null == tableNamePostfixFormat) return this.getTableNamePrefix();
		
		return this.getTableNamePrefix()+String.format(this.tableNamePostfixFormat, routeId);
	}


	/**
	 * user_info.unitPosId={99:user0;100:user1;101:user2}
	 */
	@Override
	public void parseRouteConfig(String routeConfig) throws Exception {
		if(StringUtils.isNotBlank(routeConfig)){
			String[] routeConfigArr = routeConfig.split(SymbolConstant.SYMBOL_EQUAL);
			if(routeConfigArr.length >= 2){
				
				String[] tableInfoArr = routeConfigArr[0].split("\\"+SymbolConstant.SYMBOL_PERIOD);
				if(tableInfoArr.length ==2){
					this.tableNamePrefix = tableInfoArr[0] ;
					this.tableRouteFieldName = tableInfoArr[1] ;
				}else if(tableInfoArr.length == 1){
					this.tableRouteFieldName = tableInfoArr[0] ;
				}else{
					throw new Exception("invalid routeConfig:"+routeConfig);
				}
				
				String[] routeRes = routeConfigArr[1].split(SymbolConstant.SYMBOL_POUND);
				
				String routeRuleStr = routeRes[0].replace(SymbolConstant.SYMBOL_OPEN_BRACE, "")
					      						 .replace(SymbolConstant.SYMBOL_CLOSE_BRAE, "") ;
				
				String[] routeItems = routeRuleStr.split(SymbolConstant.SYMBOL_SEM);
				for (int i = 0; i < routeItems.length; i++) {
					String[] routeb=routeItems[i].split(SymbolConstant.SYMBOL_COLON);
					if(routeb.length==2){
						this.routeRuleMap.put(routeb[0], routeb[1]);
					}else{
						throw new Exception("invalid routeConfig"+routeConfig);
					}
				}
				if(routeRes.length>=2){
					tableNamePostfixFormat =  routeRes[1];
				}
				
			}else {
				throw new Exception("invalid routeConfig:" + routeConfig);
			}
			
		}
		
		
	}


	public String getRouteId() {
		return routeId;
	}


	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String toString() {		
		return "db:"+getDBGroupName()+" table:"+getTableName();
	}
}
