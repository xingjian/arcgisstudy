/**@文件名: PointCUSD.java @作者： promisePB xingjian@yeah.net @日期 2011-2-23 下午01:06:00 */

package com.xingjian.plpcusd;

import java.util.ArrayList;
import java.util.List;
import com.esri.arcgis.geodatabase.FeatureClass;
import com.esri.arcgis.geodatabase.IFeature;
import com.esri.arcgis.geodatabase.IFields;
import com.esri.arcgis.geometry.Point;
import com.esri.arcgis.geometry.Polyline;
import com.xingjian.util.ArcgisServer;

/**   
 * @类名: PointCUSD.java 
 * @包名：  com.xingjian.plpcusd 
 * @描述: 线的增删查改 
 * @作者： promisePB xingjian@yeah.net   
 * @日期： 2011-2-23 下午01:06:00 
 * @版本： V1.0   
 */

public class LineCUSD {

	private ArcgisServer arcgisServer;
	
	public LineCUSD(){
		try {
			arcgisServer = new ArcgisServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存一个点的对象
	 */
	public void save(LineVO lineVO){
		try {
			FeatureClass featureClass = new FeatureClass(arcgisServer.workspace.openFeatureClass(lineVO.getLayerName()));
			IFields fields = featureClass.getFields();
			int fcount = fields.getFieldCount();
			IFeature feature = featureClass.createFeature();
			Polyline line  = new Polyline();
			List<PointVO> points = lineVO.getPoints();
			for (int i = 0; i < fcount; i++) {
				String key = fields.getField(i).getName();
				if(key.equals("MIS_ID")){
					feature.setValue(i, lineVO.getMis_id());
				}else if(key.equals("NAME")){
					feature.setValue(i, lineVO.getName());
				}
			}
			//设置线
			for(int j=0;j<points.size();j++){
				Point point = new Point();
				PointVO pointVO = points.get(j);
				point.setX(pointVO.getX());
				point.setY(pointVO.getY());
				line.addPoint(point, null, null);
			}
			feature.setShapeByRef(line);
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
		LineCUSD pcusd = new LineCUSD();
		PointVO pointvo1 = new PointVO("000001", "测试数据", 117.36, 36.0000, 0,"LineVO");
		PointVO pointvo2 = new PointVO("000001", "测试数据", 117.37, 36.1100, 0,"LineVO");
		List<PointVO> points = new ArrayList<PointVO>();
		points.add(pointvo1);
		points.add(pointvo2);
		LineVO lineVO = new LineVO("000001", "测试数据", 1, "LineVO", points);
		pcusd.save(lineVO);
	}

}
