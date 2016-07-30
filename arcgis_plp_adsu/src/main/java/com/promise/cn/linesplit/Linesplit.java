/** @文件名: Linesplit.java @创建人：邢健  @创建日期： 2015年5月28日 上午11:10:31 */
package com.promise.cn.linesplit;

import java.io.IOException;
import java.net.UnknownHostException;

import com.esri.arcgis.datasourcesfile.ShapefileWorkspaceFactory;
import com.esri.arcgis.geodatabase.IFeatureWorkspace;
import com.esri.arcgis.geodatabase.IFeatureWorkspaceProxy;
import com.esri.arcgis.geodatabase.IWorkspaceFactory;

     /**  
 * @类名: Linesplit.java
 * @包名: com.promise.cn.linesplit
 * @描述: TODO
 * @作者: xingjian xingjian@yeah.net  
 * @日期:2015年5月28日 上午11:10:31
 * @版本: V1.0  
 */
public class Linesplit {
	public IWorkspaceFactory shapeWkspFactory;
	public void openWorkSpace(){
		try {
			IWorkspaceFactory shapeWkspFactory = new ShapefileWorkspaceFactory();
			IFeatureWorkspace featureWksp = new IFeatureWorkspaceProxy(shapeWkspFactory.openFromFile("F:\\gitworkspace\\arcgisstudy\\arcgis_plp_adsu\\src\\main\\resources", 0));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Linesplit linesplit = new Linesplit();
		linesplit.openWorkSpace();
	}

}
