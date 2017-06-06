package Files;

import Model.WeiboContent;
import Model.WeixinContent;

import java.io.*;
import java.util.ArrayList;

import static DAO.WeiboDAO.SelectWeibo;
import static DAO.WeixinDAO.SelectWeixin;
import static Utils.Utils.GetDate;

/**
 * Created by bin on 2017/5/4.
 */
public class ConvertFiles {
    public static void main(String[] args) {
        FileOutputStream  out = null;
        //获取微博数据
        ArrayList<WeiboContent> arrayList = SelectWeibo();
        ArrayList<WeixinContent> weixinList = SelectWeixin();

        try{
            out=new FileOutputStream("d:/temp/" + GetDate()+ ".txt");
            for(WeiboContent weiboContent : arrayList){
                //objectWriter.writeObject(weiboContent);
                //out.write(weiboContent.getComment());

                String temp = weiboContent.getContent();
                out.write(temp.getBytes());

                System.out.println(weiboContent.getContent());
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
