/**@文件名: LineVO.java @作者： promisePB xingjian@yeah.net @日期 2011-2-23 下午05:02:07 */

package com.xingjian.plpcusd;

import java.util.List;

/**   
 * @类名: LineVO.java 
 * @包名: com.xingjian.plpcusd 
 * @描述: 保存线的对象 
 * @作者： promisePB xingjian@yeah.net   
 * @日期： 2011-2-23 下午05:02:07 
 * @版本： V1.0   
 */

public class PolygonVO {

	private String mis_id;
	
	private String name;
	
	private int layerID;
	
	private String layerName;
	
	private List<PointVO> points;

	/**
	 * 构造函数
	 */
	public PolygonVO(String mis_id,String name,int layerID,String layerName,List<PointVO> points){
		this.mis_id = mis_id;
		this.layerName = layerName;
		this.points = points;
		this.layerID = layerID;
		this.name = name;
	}
	
	public String getMis_id() {
		return mis_id;
	}

	public void setMis_id(String mis_id) {
		this.mis_id = mis_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLayerID() {
		return layerID;
	}

	public void setLayerID(int layerID) {
		this.layerID = layerID;
	}

	public String getLayerName() {
		return layerName;
	}

	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}

	public List<PointVO> getPoints() {
		return points;
	}

	public void setPoints(List<PointVO> points) {
		this.points = points;
	}
	
	
}
