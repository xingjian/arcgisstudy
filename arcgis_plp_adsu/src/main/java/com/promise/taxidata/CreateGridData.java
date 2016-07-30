/** @文件名: CreateGridData.java @创建人：邢健  @创建日期： 2015年6月25日 下午1:49:01 */
package com.promise.taxidata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
public class CreateGridData {
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
	
	public String createSQL() throws Exception {
		double xStep = (xmax-xmin)/xNumMax;
		double yStep = (ymax-ymin)/yNumMax;
		SaveSQLFileUtil ssfu = null;
		Statement stat = DbConnection.GetConnection().createStatement();
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
				String valueStr = "(("+point1X+" "+point1Y+","+point1X+" "+point2Y+","+point2X+" "+point2Y+","+point2X+" "+point1Y+","+point1X+" "+point1Y+"))'";
				String sql = "insert into griddata1800(objectid,shape,remark,x,y) values(sde.gdb_util.next_rowid('sde','griddata1800'),sde.st_polygon('polygon "+valueStr+",4326),'remark','"+i+"','"+j+"')";
				boolean result = stat.execute(sql);
				System.out.println("result===="+result+"----"+sql);
				ssfu.savaSQLToFile(sql+"; \n");
			}
			stat.close();
			DbConnection.GetConnection().close();
		}
		if(null!=ssfu){
			ssfu.closeSQLFile();
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
	
	public static void main(String[] args) throws Exception {
		CreateGridData cg = new CreateGridData();
		cg.createSQL();
	}
}
