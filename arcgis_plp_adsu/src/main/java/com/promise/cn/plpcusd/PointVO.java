/**@文件名: PointVO.java @作者： promisePB xingjian@yeah.net @日期 2011-2-23 下午03:09:22 */

package com.promise.cn.plpcusd;

/**   
 * @类名: PointVO.java 
 * @包名: com.xingjian.plpcusd 
 * @描述: 点对象 
 * @作者： promisePB xingjian@yeah.net   
 * @日期： 2011-2-23 下午03:09:22 
 * @版本： V1.0   
 */

public class PointVO {
	
	private String mis_id;
	
	private double x;
	
	private double y;
	
	private String name;
	
	private int layerID;
	
	private String layerName;

	public PointVO(String mis_id,String name,double x,double y,int layerID,String layerName){
		this.mis_id = mis_id;
		this.name = name;
		this.x = x;
		this.y = y;
		this.layerID = layerID;
		this.layerName = layerName;
	}
	
	public String getMis_id() {
		return mis_id;
	}

	public void setMis_id(String mis_id) {
		this.mis_id = mis_id;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
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
	
}
