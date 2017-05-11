package DAO;

import Model.WeiboContent;
import Utils.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by bin on 2017/5/3.
 */
public class WeiboDAO {

    public static Connection conn = DBConn.GetConnection();
    private static SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static boolean InsertWeibo(ArrayList<WeiboContent> arrayList) {
        PreparedStatement ps = null;
        String insertSql = "INSERT INTO weibo(content,link,datetime) VALUES(?,?,?)";
        String date = simpleDateTimeFormat.format(new Date());

        try {
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            ps = conn.prepareStatement(insertSql);
            //rs = st.executeQuery(insertSql);
            for (WeiboContent s : arrayList) {
                ps.setString(1, s.getContent());
                ps.setString(2, s.getLink());
                ps.setString(3,date);
                ps.addBatch();
            }
            ps.executeBatch();
            conn.commit();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<WeiboContent> SelectWeibo(){
        ArrayList<WeiboContent> arrayList = new ArrayList<WeiboContent>();
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM weibo";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                WeiboContent weiboContent = new WeiboContent();

                weiboContent.setContent(resultSet.getString("content"));
                weiboContent.setLink(resultSet.getString("link"));

                arrayList.add(weiboContent);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return  arrayList;
    }
}
