package Crawler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;


public class WeiboCrawlerByAPI {
    public static void main(String[] args) {
        try {
            //Document document = Jsoup.connect("https://m.weibo.cn/p/index?containerid=2304135514859428_-_WEIBO_SECOND_PROFILE_WEIBO&luicode=10000011&lfid=1005055514859428").get();

            String url= "https://m.weibo.cn/api/container/getIndex";
            String param = "containerid=2304135514859428_-_WEIBO_SECOND_PROFILE_WEIBO&luicode=10000011&lfid=1005055514859428";

            String data = sendGet(url,param);
            //System.out.println(data);

            JSONObject jsonObject = new JSONObject(data);
            System.out.println(jsonObject.get("cards"));
            JSONArray jsonArray = jsonObject.getJSONArray("cards");

            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonItem = jsonArray.getJSONObject(i);
                System.out.println(jsonItem);
                if(i > 1) {
                    JSONObject mblog = jsonItem.getJSONObject("mblog");
                    System.out.println(mblog.getString("text"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 发起http请求并获取结果
     *
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
//    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
//        JSONObject jsonObject = null;
//        StringBuffer buffer = new StringBuffer();
//        InputStream inputStream=null;
//        try {
//            URL url = new URL(requestUrl);
//            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
//            httpUrlConn.setDoOutput(true);
//            httpUrlConn.setDoInput(true);
//            httpUrlConn.setUseCaches(false);
//            // 设置请求方式（GET/POST）
//            httpUrlConn.setRequestMethod(requestMethod);
//            if ("GET".equalsIgnoreCase(requestMethod))
//                httpUrlConn.connect();
//
//            // 当有数据需要提交时
//            if (null != outputStr) {
//                OutputStream outputStream = httpUrlConn.getOutputStream();
//                // 注意编码格式，防止中文乱码
//                outputStream.write(outputStr.getBytes("UTF-8"));
//                outputStream.close();
//            }
//            //将返回的输入流转换成字符串
//            inputStream = httpUrlConn.getInputStream();
//            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
//            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//
//            String str = null;
//            while ((str = bufferedReader.readLine()) != null) {
//                buffer.append(str);
//            }
//            bufferedReader.close();
//            inputStreamReader.close();
//            // 释放资源
//            inputStream.close();
//            inputStream = null;
//            httpUrlConn.disconnect();
//            jsonObject = JSONObject.fromObject(buffer.toString());
//        } catch (ConnectException ce) {
//            ce.printStackTrace();
//            System.out.println("Weixin server connection timed out");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("http request error:{}");
//        }finally{
//            try {
//                if(inputStream!=null){
//                    inputStream.close();
//                }
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//        return jsonObject;
//    }
}
