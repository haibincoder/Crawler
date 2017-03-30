package Utils;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

public class DBConn {	
	private static final Logger Log = Logger.getLogger(DBConn.class.getName());
	public static String CONN_URL = "jdbc:mysql://127.0.0.1:3306/crawler?characterEncoding=utf8&useSSL=true";
	public static String USERNAME = "root";
	public static String PASSWORD = "root";

	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(CONN_URL, USERNAME, PASSWORD);
		} 
		catch (Exception e) {
			Log.error(e);
		}
		return conn;
	}
}
