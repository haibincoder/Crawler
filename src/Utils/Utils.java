package Utils;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Utils {
    private static SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
    //private static final Logger Log = Logger.getLogger(Utils.class.getName());
    public static Connection conn = DBConn.getConnection();

    /**
     * Created with haibin
     * 插入数据库
    **/
    public  static boolean InsertHotNews(ArrayList<String> arrayList){
        PreparedStatement ps = null;
        String insertSql = "INSERT INTO weixin_hotnews(news,datetime) VALUES(?,?)";
        String date = simpleDateTimeFormat.format(new Date());

        try{
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            ps = conn.prepareStatement(insertSql);
            //rs = st.executeQuery(insertSql);
            for(String s : arrayList){
                ps.setString(1,s);
                ps.setString(2,date);
                ps.addBatch();
            }
            ps.executeBatch();
            conn.commit();

            return true;

        } catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
        }
    }

    public  static boolean InsertRecommendNews(ArrayList<String> arrayList){
        PreparedStatement ps = null;
        String insertSql = "INSERT INTO RecommendNews(news,datetime) VALUES(?,?)";
        String date = simpleDateTimeFormat.format(new Date());

        try{
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            ps = conn.prepareStatement(insertSql);
            //rs = st.executeQuery(insertSql);
            for(String s : arrayList){
                ps.setString(1,s);
                ps.setString(2,date);
                ps.addBatch();
            }
            ps.executeBatch();
            conn.commit();

            return true;

        } catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
        }
    }

}
