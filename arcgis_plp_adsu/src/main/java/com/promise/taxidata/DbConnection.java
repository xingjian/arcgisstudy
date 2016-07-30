/** @文件名: DbConnection.java @创建人：邢健  @创建日期： 2015年6月25日 下午1:34:49 */
package com.promise.taxidata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**  
 * @类名: DbConnection.java
 * @包名: com.promise.taxidata
 * @描述: TODO
 * @作者: xingjian xingjian@yeah.net  
 * @日期:2015年6月25日 下午1:34:49
 * @版本: V1.0  
 */
public class DbConnection {
	private static Connection conn=null;
	
	private DbConnection(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "sde", "sde");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	public static Connection GetConnection(){
		if(null==conn){
			new DbConnection();
		}
		return conn;
	}
	
	public static void main(String[] args) {
		DbConnection dbConnection = new DbConnection();
		try {
			System.out.println(conn.isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
