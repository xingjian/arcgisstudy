/**@文件名: PointCUSD.java @作者： promisePB xingjian@yeah.net @日期 2011-2-23 下午01:06:00 */

package com.xingjian.plpcusd;

import com.esri.arcgis.geodatabase.FeatureClass;
import com.esri.arcgis.geodatabase.IFeature;
import com.esri.arcgis.geodatabase.IFields;
import com.esri.arcgis.geometry.Point;
import com.xingjian.util.ArcgisServer;

/**   
 * @类名: PointCUSD.java 
 * @包名：  com.xingjian.plpcusd 
 * @描述: 点的增删查改 
 * @作者： promisePB xingjian@yeah.net   
 * @日期： 2011-2-23 下午01:06:00 
 * @版本： V1.0   
 */

public class PointCUSD {

	private ArcgisServer arcgisServer;
	
	public PointCUSD(){
		try {
			arcgisServer = new ArcgisServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存一个点的对象
	 */
	public void save(PointVO pointVO){
		try {
			FeatureClass featureClass = new FeatureClass(arcgisServer.workspace.openFeatureClass(pointVO.getLayerName()));
			IFields fields = featureClass.getFields();
			int fcount = fields.getFieldCount();
			IFeature feature = featureClass.createFeature();;
			Point point = new Point();
			point.setX(pointVO.getX());
			point.setY(pointVO.getY());
			for (int i = 0; i < fcount; i++) {
				String key = fields.getField(i).getName();
				if(key.equals("MIS_ID")){
					feature.setValue(i, pointVO.getMis_id());
				}else if(key.equals("NAME")){
					feature.setValue(i, pointVO.getName());
				}
			}
			feature.setShapeByRef(point);
			feature.store();
		} catch (Exception e) {
			System.out.println("获取layer出错");
			e.printStackTrace();
		}
	}
	
	/**
	 * 功能：
	 * 描述：
	 * @param args
	 */
	public static void main(String[] args) {
		PointCUSD pcusd = new PointCUSD();
		PointVO pointvo = new PointVO("000001", "测试数据", 117.36, 36.0000, 0,"PointVO");
		pcusd.save(pointvo);
	}

}
