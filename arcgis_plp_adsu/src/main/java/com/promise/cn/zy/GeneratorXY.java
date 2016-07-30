/**
 * 
 */
package com.promise.cn.zy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.promise.cn.plpcusd.PointVO;

/**
 * @author Administrator
 *
 */
public class GeneratorXY {

	public int xMax = 201;
	public int yMax = 201;
	public int centerX = 101;
	public int centerY = 101;
	public int unit = 1;
	public double centerlgtd = 116.50191;
	public double centerlttd = 40.28937;
	public double lgtdUnit = 111.000;
	public double lttdUnit = 111.400;
	
	
	public double length(PointVO v1, PointVO v2){
		double d1 = (Math.abs(v1.getX() - v2.getX()))*lgtdUnit;
		double d2 = (Math.abs(v1.getY() - v2.getY()))*lttdUnit;
		double result = Math.sqrt((Math.pow(d1, 2)+Math.pow(d2, 2)))*1000;
		return result; 
	}
	
	public double getDistance(double lng1,double lat1,double lng2,double lat2){
        double radLat1 = Math.toRadians(lat1);
        double radLat2 = Math.toRadians(lat2);
        double a = radLat1 - radLat2;
        double b = Math.toRadians(lng1) - Math.toRadians(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)
                * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * 6378137.0;// 取WGS84标准参考椭球中的地球长半径(单位:m)
        s = Math.round(s * 10000) / 10000;
        return s;
    }
	
	public List<PointVO> generatorPointVOs(){
		List<PointVO> list = new ArrayList<PointVO>();
		for(int i=1;i<=xMax;i++){
			double lgtdTemp = 0.0;
			int iTemp = i-centerX;
			if(iTemp<0){//在左侧，应该小于centerlgtd
				lgtdTemp = centerlgtd+iTemp/lgtdUnit;
			}else if(iTemp>0){//在右侧，应该大于centerlgtd
				lgtdTemp = centerlgtd+iTemp/lgtdUnit;
			}else{//相等
				lgtdTemp = centerlgtd;
			}
			for(int j=1;j<=yMax;j++){
				double lttdTemp = 0.0;
				int jTemp = j-centerY;
				if(jTemp<0){//在上面，应该大于centerlttd
					lttdTemp = centerlttd-jTemp/lttdUnit;
				}else if(jTemp>0){//在下面，应该小于centerlttd
					lttdTemp = centerlttd-jTemp/lttdUnit;
				}else{//相等
					lttdTemp = centerlttd;
				}
				PointVO pointVOTemp = new PointVO();
				pointVOTemp.setMis_id(i+"-"+j);
				pointVOTemp.setLayerID(0);
				pointVOTemp.setLayerName("weather");
				pointVOTemp.setName(i+"-"+j);
				pointVOTemp.setX(lgtdTemp);
				pointVOTemp.setY(lttdTemp);
				list.add(pointVOTemp);
			}
			
			PointVO pointVOTempCenter = new PointVO();
			pointVOTempCenter.setMis_id(101+"-"+101);
			pointVOTempCenter.setLayerID(0);
			pointVOTempCenter.setLayerName("weather");
			pointVOTempCenter.setName("中间点");
			pointVOTempCenter.setX(centerlgtd);
			pointVOTempCenter.setY(centerlttd);
			list.add(pointVOTempCenter);
		}
		return list;
	}
	
	public PointVO getPointVOByXY(int i,int j,String name){
		double lgtdTemp = 0.0;
		int iTemp = i-centerX;
		if(iTemp<0){//在左侧，应该小于centerlgtd
			lgtdTemp = centerlgtd+iTemp/lgtdUnit;
		}else if(iTemp>0){//在右侧，应该大于centerlgtd
			lgtdTemp = centerlgtd+iTemp/lgtdUnit;
		}else{//相等
			lgtdTemp = centerlgtd;
		}
		double lttdTemp = 0.0;
		int jTemp = j-centerY;
		if(jTemp<0){//在上面，应该大于centerlttd
			lttdTemp = centerlttd+jTemp/lttdUnit;
		}else if(jTemp>0){//在下面，应该小于centerlttd
			lttdTemp = centerlttd+jTemp/lttdUnit;
		}else{//相等
			lttdTemp = centerlttd;
		}
		PointVO pointVOTemp = new PointVO();
		pointVOTemp.setMis_id(i+"-"+j);
		pointVOTemp.setLayerID(0);
		pointVOTemp.setLayerName("weather1");
		pointVOTemp.setName(name);
		pointVOTemp.setX(lgtdTemp);
		pointVOTemp.setY(lttdTemp);
		return pointVOTemp;
	}
	
	public PointVO getPointVOByXYExtend(int i,int j,String name){
		double lgtdTemp = 0.0;
		int iTemp = i-centerX;
		if(iTemp<0){//在左侧，应该小于centerlgtd
			lgtdTemp = centerlgtd+iTemp/lgtdUnit;
		}else if(iTemp>0){//在右侧，应该大于centerlgtd
			lgtdTemp = centerlgtd+iTemp/lgtdUnit;
		}else{//相等
			lgtdTemp = centerlgtd;
		}
		double lttdTemp = 0.0;
		int jTemp = j-centerY;
		if(jTemp<0){//在上面，应该大于centerlttd
			lttdTemp = centerlttd+jTemp/lttdUnit;
		}else if(jTemp>0){//在下面，应该小于centerlttd
			lttdTemp = centerlttd+jTemp/lttdUnit;
		}else{//相等
			lttdTemp = centerlttd;
		}
		PointVO pointVOTemp = new PointVO();
		pointVOTemp.setMis_id(i+"-"+j);
		pointVOTemp.setLayerID(0);
		pointVOTemp.setLayerName("weather1");
		pointVOTemp.setName(name);
		pointVOTemp.setX(lgtdTemp);
		pointVOTemp.setY(lttdTemp);
		return pointVOTemp;
	}
	
	
	public PointVO getPointVOByXYExtendBeijing(int i,int j,String name,String code){
		double lgtdTemp = 0.0;
		int iTemp = i-centerX;
		if(iTemp<0){//在左侧，应该小于centerlgtd
			lgtdTemp = centerlgtd+iTemp*0.0116;
		}else if(iTemp>0){//在右侧，应该大于centerlgtd
			lgtdTemp = centerlgtd+iTemp*0.0116;
		}else{//相等
			lgtdTemp = centerlgtd;
		}
		double lttdTemp = 0.0;
		int jTemp = j-centerY;
		if(jTemp<0){//在上面，应该大于centerlttd
			lttdTemp = centerlttd+jTemp*0.00925;
		}else if(jTemp>0){//在下面，应该小于centerlttd
			lttdTemp = centerlttd+jTemp*0.00925;
		}else{//相等
			lttdTemp = centerlttd;
		}
		PointVO pointVOTemp = new PointVO();
		pointVOTemp.setMis_id(i+"-"+j);
		pointVOTemp.setLayerID(0);
		pointVOTemp.setLayerName("weatherstation");
		pointVOTemp.setName(name);
		pointVOTemp.setX(lgtdTemp);
		pointVOTemp.setY(lttdTemp);
		pointVOTemp.setCode(code);
		return pointVOTemp;
	}
	
	
	 /**  
     * 对double数据进行取精度.  
     * @param value  double数据.  
     * @param scale  精度位数(保留的小数位数).  
     * @param roundingMode  精度取值方式.  
     * @return 精度计算后的数据.  
     */  
	public double round(double value, int scale, int roundingMode) {   
	        BigDecimal bd = new BigDecimal(value);   
	        bd = bd.setScale(scale, roundingMode);   
	        double d = bd.doubleValue();   
	        return d;   
	}   

	/**
	 * 入口函数
	 */
	public static void main(String[] args) {
		GeneratorXY g = new GeneratorXY();
//		PointVO p = g.getPointVOByXY(100,100,"中心点");
//		System.out.println(p.getX()+"***"+p.getY());
//		116.354708  40.711341   116.888307  40.704587
		PointVO p1 = new PointVO();
		p1.setX(116.354708);
		p1.setY(40.711341);
		
		PointVO p2 = new PointVO();
		p2.setX(116.888307);
		p2.setY(40.704587);
		double result = g.length(p1, p2);
		System.out.println(result);
		//116.353915 , 40.711770 ,  116.355031 , 40.710677
		double r = g.getDistance(116.354708, 40.711341,116.888307,40.704587);
		double r1 = g.getDistance(116.353915 , 40.711770 ,  116.355031 , 40.710677);//153
		System.out.println(r1);
	}
}
