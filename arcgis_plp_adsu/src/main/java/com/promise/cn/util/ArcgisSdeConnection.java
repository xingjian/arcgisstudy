package com.promise.cn.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.esri.sde.sdk.client.SDEPoint;
import com.esri.sde.sdk.client.SeConnection;
import com.esri.sde.sdk.client.SeCoordinateReference;
import com.esri.sde.sdk.client.SeException;
import com.esri.sde.sdk.client.SeInsert;
import com.esri.sde.sdk.client.SeLayer;
import com.esri.sde.sdk.client.SeRow;
import com.esri.sde.sdk.client.SeShape;
import com.ibm.icu.text.SimpleDateFormat;
import com.promise.cn.plpcusd.PointVO;
import com.promise.cn.zy.GeneratorXY;

/**   
 * @类名: ArcgisSdeConnection.java 
 * @包名：  com.promise.cn.util 
 * @描述: arcgis数据sde数据源 
 * @作者： promisePB xingjian@yeah.net   
 * @日期： 2011-2-23 下午01:06:00 
 * @版本： V1.0   
 */
public class ArcgisSdeConnection {

	public SeConnection sdeconn;
	public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 打开sde数据库连接 需要自己输入参数
	 * 
	 * @return
	 * @throws SeException
	 */
	public SeConnection getSdeConnection(String server, String instance,String database, String user, String password) throws SeException {
		try {
			sdeconn = new SeConnection(server, instance, database, user,
					password);
		} catch (SeException e) {
			e.printStackTrace();
		}
		return sdeconn;
	}
	
	public boolean addMapPoint(PointVO mpv) {
		boolean status = true;
		try {
			sdeconn.startTransaction();
			SeLayer insertLayer = getLayer(sdeconn, mpv.getLayerName());
			String[] cols = new String[3];
			cols[0] = new String("MIS_ID");
			cols[1] = new String("NAME");
			cols[2] = insertLayer.getSpatialColumn();
			SeInsert insert = new SeInsert(sdeconn);
			insert.intoTable(insertLayer.getName(), cols);
			insert.setWriteMode(true);
			SeCoordinateReference coordref = (SeCoordinateReference) insertLayer.getCoordRef();
			SeShape shape = new SeShape(coordref);
			int numPts = 1;
			SDEPoint[] ptArray = new SDEPoint[numPts];
			ptArray[0] = new SDEPoint(mpv.getX(), mpv.getY());
			shape.generatePoint(numPts, ptArray);
			SeRow row = insert.getRowToSet();
			row.setNString(0, mpv.getMis_id());
			row.setNString(1, mpv.getName());
			row.setShape(2, shape);
			insert.execute();
			insert.close();
			sdeconn.commitTransaction();
		} catch (Exception ex) {
			ex.printStackTrace();
			status = false;
		}
		return status;
	}
	
	/**
	 * 关闭sde连接
	 */
	public void closeSdeConnection(SeConnection sdeconn) {
		if (sdeconn != null && !(sdeconn.isClosed())) {
			try {
				sdeconn.close();
			} catch (SeException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 通过传入的layer名字，返回layer
	 * 
	 * @param conn
	 * @param layerName
	 * @return
	 * @throws SeException
	 */
	@SuppressWarnings("unchecked")
	public SeLayer getLayer(SeConnection sdeconn, String layerName)throws SeException {
		SeLayer layer = null;
		SeLayer insertLayer = null;
		try {
			Vector layerList = sdeconn.getLayers();
			for (int index = 0; index < layerList.size(); index++) {
				layer = (SeLayer) layerList.elementAt(index);
				if (layer.getName().equalsIgnoreCase(layerName)) {
					insertLayer = layer;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return insertLayer;
	}
	
	public void testMain()throws SeException{
		PointVO p = new PointVO("201-201","中心点",116.50191,40.28937,0,"weather");
		SeConnection sdeconn = getSdeConnection("localhost", "5151", "xingjian", "sde", "sde");
		SeLayer insertLayer = getLayer(sdeconn, "weather");
		boolean boo = addMapPoint(p);
		System.out.println(insertLayer.getTableName()+"----"+boo);
		closeSdeConnection(sdeconn);
	}
	
	public void readExcel() throws IOException {
		  List<PointVO> list = new ArrayList<PointVO>();
		  GeneratorXY  g = new GeneratorXY();
		  FileInputStream fis = new FileInputStream("C:\\beijing.xls"); // 根据excel文件路径创建文件流
		  POIFSFileSystem fs = new POIFSFileSystem(fis); // 利用poi读取excel文件流
		  HSSFWorkbook wb = new HSSFWorkbook(fs); // 读取excel工作簿
		  HSSFSheet sheet = wb.getSheetAt(0); // 读取excel的sheet，0表示读取第一个、1表示第二个.....
		  // 获取sheet中总共有多少行数据sheet.getPhysicalNumberOfRows()
		  for (int i = 1; i <= 234; i++) {
		   HSSFRow row = sheet.getRow(i); // 取出sheet中的某一行数据
		   if (row != null) {
		    // 获取该行中总共有多少列数据row.getLastCellNum()
		     HSSFCell cell1 = row.getCell((short) 0); // 获取该行中的一个单元格对象
		     HSSFCell cell2 = row.getCell((short) 1);
		     HSSFCell cell3 = row.getCell((short) 2);
		     // 判断单元格数据类型，这个地方值得注意：当取某一行中的数据的时候，需要判断数据类型，否则会报错
		     // java.lang.NumberFormatException: You cannot get a string
		     // value from a numeric cell等等错误
		     int x = (int)cell1.getNumericCellValue();
		     int y = (int)cell2.getNumericCellValue();
		     String name = cell3.getStringCellValue();
		     PointVO p =g.getPointVOByXY(x,y,name);
		     addMapPoint(p);
		   }
		  }
		  System.out.println(list.size());
		 }
	
	public void generatorXYInsertDB()throws SeException{
		GeneratorXY  g = new GeneratorXY();
		List<PointVO> list = g.generatorPointVOs();
		getSdeConnection("localhost", "5151", "xingjian", "sde", "sde");
		System.out.println("开始插入:"+sdf.format(new Date()));
		for(PointVO p:list){
			addMapPoint(p);
		}
		System.out.println("停止插入:"+sdf.format(new Date()));
		closeSdeConnection(sdeconn);
	}
	
	/**
	 * @param args
	 * @throws SeException 
	 */
	public static void main(String[] args) throws Exception {
		ArcgisSdeConnection asc = new ArcgisSdeConnection();
		asc.getSdeConnection("localhost", "5151", "xingjian", "sde", "sde");
		//asc.generatorXYInsertDB();
		asc.readExcel();
	}

}
