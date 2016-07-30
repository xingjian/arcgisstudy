/** @文件名: CreateGridData.java @创建人：邢健  @创建日期： 2015年6月25日 下午1:49:01 */
package com.promise.taxidata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DecimalFormat;

/**  
 * @类名: CreateGridData.java
 * @包名: com.promise.taxidata
 * @描述: 生成网格数据
 * @作者: xingjian xingjian@yeah.net  
 * @日期:2015年6月25日 下午1:49:01
 * @版本: V1.0  
 */
public class CreateGridDataNew {
	//x轴从左到右递增    y轴从上倒下递减
	private double xmin = 114.725016082024d;//经度最小(左)
	private double ymin = 39.3499115443656d;//纬度最小(下)
	private double xmax = 118.162266882976d;//经度最大(右)
	private double ymax = 41.1361591766345d;//纬度最大(上)
	
	private int xNumMax = 20;
	private int yNumMax = 20;
	public String filePath = "d://CreateGridData//";
	public int threadCount = 10;
	public String success = "success";
	public String fault = "fault";
	public int fileCount = 20;
	public int fileNum  = 0;
	DecimalFormat df = new DecimalFormat("######0.0000000");   
	
	public String createSQL(){
		try{
		double xStep = (xmax-xmin)/xNumMax;
		double yStep = (ymax-ymin)/yNumMax;
		SaveSQLFileUtil ssfu = null;
//		String sql = "insert into sde.griddata1800 (objectid,shape,remark,x,y) values(sde.gdb_util.next_rowid('sde','griddata1800'),sde.st_polygon(?,4326),?,?,?)";
		String sql = "INSERT INTO polygon_test VALUES (sde.st_polygon ('polygon ((10.01 20.03, 20.94 21.34, 35.93 10.04, 10.01 20.03))', 4326))";
		Statement ps = DbConnection.GetConnection().createStatement();
		DbConnection.GetConnection().setAutoCommit(false); 
		for(int i=0;i<xNumMax;i++){
			if(i%(int)(xNumMax/fileCount)==0){
				if(null!=ssfu){
					ssfu.closeSQLFile();
				}
				ssfu = new SaveSQLFileUtil(filePath+fileNum+".sql");
				fileNum++;
			}
			for(int j=0;j<yNumMax;j++){
				double point1XD = xmin+i*xStep;
				double point2XD = xmin+(i+1)*xStep;
				double point1YD = ymax-j*yStep;
				double point2YD = ymax-(j+1)*yStep;
				String point1X = df.format(point1XD);
				String point2X = df.format(point2XD);
				String point1Y = df.format(point1YD);
				String point2Y = df.format(point2YD);
				String valueStr = "'polygon(("+point1X+" "+point1Y+","+point1X+" "+point2Y+","+point2X+" "+point2Y+","+point2X+" "+point1Y+","+point1X+" "+point1Y+"))'";
//				ps.setString(1, valueStr);
//				ps.setString(2, point1X+" "+point2Y);
//				ps.setString(3, point2X+" "+point2Y);
//				ps.setString(4, point2X+" "+point1Y);
//				ps.setString(5, point1X+" "+point1Y);
//				ps.setString(2, "remark");
//				ps.setString(3, i+"");
//				ps.setString(4, j+"");
				ps.execute(sql);
				ssfu.savaSQLToFile(sql+"\n");
				System.out.println("j==="+j);
			}
			ps.close();
//			ps.executeBatch();
			System.out.println("i===="+i);
		}
		ps.close();
		DbConnection.GetConnection().close();
		if(null!=ssfu){
			ssfu.closeSQLFile();
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return success;
	} 
	
	public String createStudentSQL(){
		try{
		SaveSQLFileUtil ssfu = null;
		String sql = "insert into student (stid,name) values(?,?)";
		PreparedStatement ps = DbConnection.GetConnection().prepareStatement(sql);
		DbConnection.GetConnection().setAutoCommit(false); 
		for(int i=0;i<xNumMax;i++){
			if(i%(int)(xNumMax/fileCount)==0){
				if(null!=ssfu){
					ssfu.closeSQLFile();
				}
				ssfu = new SaveSQLFileUtil(filePath+fileNum+".sql");
				fileNum++;
			}
			for(int j=0;j<yNumMax;j++){
				ps.setString(1, i+"-"+j);
//				ps.setString(2, point1X+" "+point2Y);
//				ps.setString(3, point2X+" "+point2Y);
//				ps.setString(4, point2X+" "+point1Y);
//				ps.setString(5, point1X+" "+point1Y);
				ps.setString(2, "j="+yNumMax);
				ps.addBatch();
				ssfu.savaSQLToFile(sql+"\n");
				System.out.println("j==="+j);
			}
			ps.executeBatch();
			System.out.println("i===="+i);
		}
		ps.close();
		DbConnection.GetConnection().close();
		if(null!=ssfu){
			ssfu.closeSQLFile();
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return success;
	} 
	
	class SaveSQLFileUtil{
		public File file = null;
		public String filePath;
		public FileWriter fileWriter;
		public BufferedWriter bufferedWriter;
		
		SaveSQLFileUtil(String filePath){
			this.filePath = filePath;
			openSQLFile();
		}
		
		public void savaSQLToFile(String sql){
			try {
				bufferedWriter.write(sql, 0, sql.length());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void closeSQLFile(){
			try {
				bufferedWriter.flush();
				bufferedWriter.close();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void openSQLFile(){
			file = new File(filePath);
			try {
				if(!file.exists()){
					file.createNewFile();
				}
				fileWriter = new FileWriter(file);
				bufferedWriter = new BufferedWriter(fileWriter);
			} catch (IOException e) {
			e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args){
		CreateGridDataNew cg = new CreateGridDataNew();
		cg.createSQL();
	}
}
