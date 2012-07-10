/**@文件名: ArcgisServer.java @作者： promisePB xingjian@yeah.net @日期 2011-2-23 下午01:09:59 */

package com.promise.cn.util;

import com.esri.arcgis.carto.FeatureLayer;
import com.esri.arcgis.carto.ILayer;
import com.esri.arcgis.carto.MapServer;
import com.esri.arcgis.datasourcesGDB.SdeWorkspaceFactory;
import com.esri.arcgis.geodatabase.Workspace;
import com.esri.arcgis.server.IServerContext;
import com.esri.arcgis.server.IServerObjectManager;
import com.esri.arcgis.server.ServerConnection;
import com.esri.arcgis.system.AoInitialize;
import com.esri.arcgis.system.EngineInitializer;
import com.esri.arcgis.system.IPropertySet;
import com.esri.arcgis.system.PropertySet;
import com.esri.arcgis.system.ServerInitializer;
import com.esri.arcgis.system.esriLicenseProductCode;

/**   
 * @类名: ArcgisServer.java 
 * @包名： com.xingjian.util 
 * @描述: arcgis 服务 
 * @作者： promisePB xingjian@yeah.net   
 * @日期： 2011-2-23 下午01:09:59 
 * @版本： V1.0   
 */

public class ArcgisServer {

	/** 服务连接对象 **/
	public ServerConnection serverConnection;
	/** 初始化参数 **/
	public String hostName = "127.0.0.1";
	public String mapServerName = "testplp";
	public String mapServerType = "MapServer";
	public String userName = "administrator";
	public String pwd = "dhcc123321";
	/** 定义上下文 **/
	public IServerContext cxt = null;
	/** 初始化server管理 **/
	public IServerObjectManager som;
	/** 地图服务对象 **/
	public MapServer mapserver;
	/** workspace **/
	public Workspace workspace;
	
	/**
	 * 构造函数
	 */
	public ArcgisServer()throws Exception{
		initServer();
		openWorkspace();
	}
	
	public void openWorkspace() throws Exception{
		SdeWorkspaceFactory workspaceFactory = new SdeWorkspaceFactory();
		IPropertySet connection = new PropertySet();
		connection.setProperty("SERVER", hostName);
		connection.setProperty("INSTANCE", "esri_sde");
		connection.setProperty("DATABASE", "emdb");
		connection.setProperty("USER", "sde");
		connection.setProperty("PASSWORD", "sde");
		connection.setProperty("VERSION", "SDE.DEFAULT");
		workspace = new Workspace(workspaceFactory.open(connection, 0));
	}
	
	/**
	 * 功能：初始化arcgis服务
	 * @throws Exception
	 */
	public void initServer()throws Exception{
		EngineInitializer.initializeEngine();
		AoInitialize aoInitializer = new AoInitialize();
		ServerInitializer serverInitializer = new ServerInitializer();
		int lic = aoInitializer.initialize(esriLicenseProductCode.esriLicenseProductCodeArcServer);
		serverInitializer.initializeServer(hostName,userName,pwd);
		serverConnection = new ServerConnection();
		serverConnection.connect(hostName);
		som = serverConnection.getServerObjectManager();
		//注意带包的情况写成:Emergency/Taian_landbase
		cxt=som.createServerContext(mapServerName,mapServerType);
		mapserver=(MapServer)cxt.getServerObject();
	}
	
	/**
	 * 功能：获取layer,通过index
	 * 描述：
	 * @param name
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public ILayer getLayer(int index) throws Exception{
		FeatureLayer layer = (FeatureLayer)mapserver.getLayer("", index);
		return layer;
	}
	
	/**
	 * 功能：测试入口函数
	 * @throws Exception
	 */
	public static void main(String[] args)throws Exception{
		ArcgisServer arcgisServer = new ArcgisServer();
		ILayer layer = arcgisServer.getLayer(0);
	}
}
