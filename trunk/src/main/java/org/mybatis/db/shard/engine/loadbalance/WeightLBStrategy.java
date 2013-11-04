package org.mybatis.db.shard.engine.loadbalance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.db.util.Assert;

/**
 * 
 * @ClassName: WeightLBStrategy 
 * @Description: TODO
 * 
 * 比如设置如下：
 * 服务器列表：ServerGroup  = { Serve1, Serve2, Serve3 }
 * 服务器权重：ServerWeight = {   2,       6,      4   }
 * 
 * (1)各负载比例是 各个权重除权重之和 小数精确到小数点三位 ：
 * 各负载比例：Weight       = { 0.167,   0.500,  0.333 }
 * 
 * (2)乘1000，使分布散列开来
 * 各负载比例：Weight*1000  = {  167,      500,    333 }
 * 
 * ——————————————————————————————————————————————————————————————————————————
 *| 0---(server1)--167|168-----(serve3)---500|5001---------(server3)----1000|
 * ——————————————————————————————————————————————————————————————————————————
 * 
 * （3）随机参数一个1000以内的数字，散落在哪段上就是是路由到哪段
 * 完毕，算法完毕介绍完毕
 * 
 * @author huhailiang(javadeeper@gmail.com)  
 * @date 2013-10-31 下午10:34:21 
 * 
 * @param <T>
 */
public class WeightLBStrategy implements LBStrategy<String> {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
	private Long zoomFactor = 1000L;
	
	/**
	 * 写权重配置比例
	 */
	private Map<String,Integer> writeWeightMap;
	
	
	/**
	 * 写权重配置比例
	 */
	private Map<String,Integer> readWeightMap;
	
	private List<WeightBean> readWeightBeans  = null ;
	
	private List<WeightBean> writeWeightBeans = null ;
	
	private Random random = new Random();
	
	public String nextWrite(List<String> writeableGroup) {
		Assert.notEmpty(writeableGroup, "writeableGroup  is Empty");
		Integer selectLocation = random.nextInt(zoomFactor.intValue()+1);
		WeightBean weightBean = findWeightBean(selectLocation.longValue(),writeWeightBeans);
		if(null == weightBean){
			return writeableGroup.get(0);
		}
		if (logger.isInfoEnabled()) {
			logger.info("WeightLBStrategy get nextWrite  tagert is : :"+ weightBean.getKey());
		}
		return weightBean.getKey();
	}

	public String nextRead(List<String> readableGroup) {
		Assert.notEmpty(readableGroup, "writeableGroup  is Empty");
		Integer selectLocation = random.nextInt(zoomFactor.intValue()+1);
		WeightBean weightBean = findWeightBean(selectLocation.longValue(),readWeightBeans);
		if(null == weightBean){
			return readableGroup.get(0);
		}
		if (logger.isInfoEnabled()) {
			logger.info("WeightLBStrategy get nextRead  tagert is : :"+ weightBean.getKey());
		}
		return weightBean.getKey();
	}
	
	public WeightBean findWeightBean(long selectLocation,List<WeightBean> weightBeans){
		for(WeightBean weightBean : weightBeans){
			if( selectLocation >= weightBean.getStartLocation()
				&& selectLocation <= weightBean.getEndLocation()){
				return weightBean;
			}
		}
		return null;
	}

	/**
	 * 
	 * @Title: initWeightList 
	 * @Description: TODO
	 * @param weightMap
	 * @return 
	 * List<WeightBean>
	 * @throws
	 */
	public List<WeightBean> initWeightList(Map<String,Integer> weightMap){
		if(null == weightMap || weightMap.size() <= 0){
			return new ArrayList<WeightBean>(0);
		}
		List<WeightBean> weightBeanList =  new ArrayList<WeightBean>(weightMap.size());
		
		int sum_all_weight = 0;
		for(Map.Entry<String,Integer> entry : weightMap.entrySet()){
			WeightBean wbean = new WeightBean();
			wbean.setKey(entry.getKey());
			wbean.setWeight(entry.getValue());
			weightBeanList.add(wbean);
			sum_all_weight = sum_all_weight + entry.getValue();
		}
		
		//（1） 按Weight大小从小到大排序
		Collections.sort(weightBeanList);
		for(int index =0 ; index < weightBeanList.size() ; index ++ ){
			WeightBean weightBeanTemp = weightBeanList.get(index);
			
			long startLocation = 0L;
			if(index != 0){
				startLocation = weightBeanList.get(index-1).getEndLocation() + 1;
			}
			
			long stepLenth = Math.round(weightBeanTemp.getWeight()*(zoomFactor*1.0)/sum_all_weight);
			
			long endLocation = startLocation + stepLenth;
			if(index == weightBeanList.size() - 1){
				endLocation = this.zoomFactor;
			}
			
			weightBeanTemp.setStartLocation(startLocation);
			weightBeanTemp.setEndLocation(endLocation);
		}
		return weightBeanList;
	}
	
	
	public static void main(String[] args) {
		WeightLBStrategy weightLBStrategy = new WeightLBStrategy();
		Map<String,Integer> weightMap = new HashMap<String,Integer>();
		weightMap.put("s1", 2);
		weightMap.put("s2", 6);
		weightMap.put("s3", 4);
		List<WeightBean>  sssList = weightLBStrategy.initWeightList(weightMap);
		
		System.out.println(sssList);
		
		
	}
	
	/**
	 * 
	 *
	 */
	public class WeightBean implements Comparable<WeightBean> {
		
		private String 	key;
		private int    	weight = 0;
		private long 	startLocation = 0L;
		private long 	endLocation = 0L;
		
		public int compareTo(WeightBean weightBean) {
			if(null != weightBean){
				int weghtSub = this.getWeight() - weightBean.getWeight();
				if ( weghtSub < 0) return -1;
				if ( weghtSub > 0) return 1;
			}
			return 0;
		}
		
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public int getWeight() {
			return weight;
		}
		public void setWeight(int weight) {
			this.weight = weight;
		}
		public long getStartLocation() {
			return startLocation;
		}
		public void setStartLocation(long startLocation) {
			this.startLocation = startLocation;
		}
		public long getEndLocation() {
			return endLocation;
		}
		public void setEndLocation(long endLocation) {
			this.endLocation = endLocation;
		}

		public String toString(){
			return String.format("WeightBean[key:%s,weight:%d,startLocation:%d,endLocation:%d]", key,weight,startLocation,endLocation);
		}
		

	}
	
	//------------getter/setter--------------
	
	public Map<String, Integer> getWriteWeightMap() {
		return writeWeightMap;
	}

	public void setWriteWeightMap(Map<String, Integer> writeWeightMap) {
		this.writeWeightBeans = initWeightList(writeWeightMap);
		this.writeWeightMap = writeWeightMap;
	}

	public Map<String, Integer> getReadWeightMap() {
		return readWeightMap;
	}

	public void setReadWeightMap(Map<String, Integer> readWeightMap) {
		this.readWeightBeans = initWeightList(readWeightMap);
		this.readWeightMap = readWeightMap;
	}
	

	

}
