package Files;

import Model.WeiboContent;

import java.io.*;
import java.util.ArrayList;

import static DAO.WeiboDAO.SelectWeibo;

/**
 * Created by bin on 2017/5/4.
 */
public class ConvertFiles {
    public static void main(String[] args) {
        FileOutputStream  out = null;
        ArrayList<WeiboContent> arrayList = SelectWeibo();

        try{
            out=new FileOutputStream("d:/temp/weibo.txt");
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
