package com.qbq.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 * 
* @ClassName: DBUtity 
* @Description: TODO(封装JDBC连接数据库的链接和关闭连接) 
* @author QianBingqiao 
* @date 2017年6月7日 下午4:46:26 
*
 */
public class DBUtity {
	
	private static Properties properties = new Properties();
	private static String driver  = null;
	private static String url = null;
	private static String user = null;
	private static String password = null;
	
	static{
		try {
			properties.load(DBUtity.class.getClassLoader().getResourceAsStream("resource/jdbc.properties"));
			driver = properties.getProperty("jdbc.driverClassName");
			url = properties.getProperty("jdbc.url");
			user = properties.getProperty("jdbc.username");
			password = properties.getProperty("jdbc.password");
			Class.forName(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//加载驱动，连接；
	public static Connection openConnection() throws SQLException{
		return DriverManager.getConnection(url, user, password);
	}
   //关闭连接
	public static void closeConnection(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
	}

}
