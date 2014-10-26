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
		PointVO p = g.getPointVOByXY(100,100,"中心点");
		System.out.println(p.getX()+"***"+p.getY());
	}
}
