/** @文件名: CreateGridDataMuilt.java @创建人：邢健  @创建日期： 2015年6月25日 下午1:49:01 */
package com.promise.taxidata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**  
 * @类名: CreateGridDataMuilt.java
 * @包名: com.promise.taxidata
 * @描述: 生成网格数据
 * @作者: xingjian xingjian@yeah.net  
 * @日期:2015年6月25日 下午1:49:01
 * @版本: V1.0  
 */
public class CreateGridDataMuilt {
	//x轴从左到右递增    y轴从上倒下递减
	private double xmin = 114.725016082024d;//经度最小(左)
	private double ymin = 39.3499115443656d;//纬度最小(下)
	private double xmax = 118.162266882976d;//经度最大(右)
	private double ymax = 41.1361591766345d;//纬度最大(上)
	
	private int xNumMax = 1800;
	private int yNumMax = 1800;
	public String filePath = "d://CreateGridData.txt";
	public int threadCount = 10;
	public String success = "success";
	public String fault = "fault";
	public File file = null;
	public FileWriter fileWriter;
	public BufferedWriter bufferedWriter;
	
	public String createSQL() throws Exception {
		openSQLFile();
		double xStep = (xmax-xmin)/xNumMax;
		double yStep = (ymax-ymin)/yNumMax;
		for(int i=0;i<xNumMax;i++){
			for(int j=0;j<yNumMax;j++){
				double point1X = xmin+i*xStep;
				double point2X = xmin+(i+1)*xStep;
				double point1Y = ymax-j*yStep;
				double point2Y = ymax-(j+1)*yStep;
				String valueStr = "(("+point1X+" "+point1Y+","+point1X+" "+point2Y+","+point2X+" "+point2Y+","+point2X+" "+point1Y+","+point1X+" "+point1Y+"))'";
				String sql = "insert into griddata1800(objectid,shape,remark,x,y) values(sde.gdb_util.next_rowid('sde','griddatapolygon'),sde.st_polygon('polygon "+valueStr+",4326),'remark','"+i+"',+'"+j+"');"+"\n";
				savaSQLToFile(sql);
			}
			savaSQLToFile("commit; \n");
			System.out.println("i======"+i);
		}
		closeSQLFile();
		return success;
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
	
	public static void main(String[] args) throws Exception {
		CreateGridDataMuilt cg = new CreateGridDataMuilt();
		cg.createSQL();
	}
}
