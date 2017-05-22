package DAO;

import Model.HotNews;
import Model.RecommendNews;
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

    public static Connection conn = DBConn.GetConnection();
    private static SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Created with haibin
     * 保存热点新闻
     **/
    public  static boolean InsertHotNews(ArrayList<HotNews> arrayList){
        PreparedStatement ps = null;
        String insertSql = "INSERT INTO weixin_hotnews(news,link,datetime,width) VALUES(?,?,?,?)";
        String date = simpleDateTimeFormat.format(new Date());

        try{
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            ps = conn.prepareStatement(insertSql);
            //rs = st.executeQuery(insertSql);
            for(HotNews s : arrayList){
                ps.setString(1,s.getNews());
                ps.setString(2,s.getLink());
                ps.setString(3,date);
                ps.setString(4,s.getWidth());
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
    public  static boolean InsertRecommendNews(ArrayList<RecommendNews> arrayList){
        PreparedStatement ps = null;
        String insertSql = "INSERT INTO weixin_recommendnews(news,datetime,link,content,source) VALUES(?,?,?,?,?)";
        String date = simpleDateTimeFormat.format(new Date());

        try{
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            ps = conn.prepareStatement(insertSql);
            //rs = st.executeQuery(insertSql);
            for(RecommendNews s : arrayList){
                ps.setString(1,s.getNews());
                ps.setString(2,date);
                ps.setString(3,s.getLink());
                ps.setString(4,s.getContents());
                ps.setString(5,s.getSource());
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
