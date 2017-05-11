package Utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by bin on 2017/4/15.
 */
public class DBConn {
        //private static final Logger Log = Logger.getLogger(DBConn.class.getName());
        public static final String CONN_URL = "jdbc:mysql://127.0.0.1:3306/crawler?characterEncoding=utf8&useSSL=true";
        public static final String USERNAME = "root";
        public static final String PASSWORD = "root";

        public static Connection GetConnection(){
            Connection conn = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(CONN_URL, USERNAME, PASSWORD);
            }
            catch (Exception e) {
                //Log.error(e);
                System.out.println(e);
            }
            return conn;
        }
}
