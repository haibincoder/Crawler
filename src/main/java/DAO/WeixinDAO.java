package DAO;

import Utils.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by bin on 2017/5/3.
 */
public class WeixinDAO {

    public static Connection conn = DBConn.getConnection();
    private static SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Created with haibin
     * 保存热点新闻
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

    /**
     * Created with haibin
     * 保存推荐新闻
     **/
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
