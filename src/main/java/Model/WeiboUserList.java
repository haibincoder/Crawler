package Model;

import java.util.ArrayList;

public class WeiboUserList{
    private ArrayList<String> arrayList = new ArrayList<String>();

    public void setArrayList(){
        arrayList.add("2665749913");
        arrayList.add("perry28pp");
        arrayList.add("1618051664");
        arrayList.add("1314608344");
        arrayList.add("sinapapers");
        arrayList.add("breakingnews");
        arrayList.add("rmrb");            //人民日报
        arrayList.add("cctvxinwen");      //央视新闻
        arrayList.add("modernexpress");   //现代快报
        arrayList.add("gzdaily");          //广州日报
        arrayList.add("cdsb");             //成都商报
        arrayList.add("sinatech");         //新浪科技
        arrayList.add("316593888");        //电商报
        arrayList.add("213423463");        //微博数码
        arrayList.add("digital");          //新浪手机
        arrayList.add("235567955");        //娱乐圈揭秘
        arrayList.add("5669038139");       //娱乐星八爷
    }

    public ArrayList<String> getArrayList() {
        return arrayList;
    }
}
