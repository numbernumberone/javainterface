package com.java;


import com.java.util.MD5Util;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

public class javainterface {
    public static String doPostJson(String url, String json) {
        System.out.println("");
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_OCTET_STREAM);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-type", "text/plain;charset=utf-8");
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static void main(String[] args) {
        String md5StrOut = "";
        long time =System.currentTimeMillis()/1000;
        StringBuilder md5Str = new StringBuilder();
        md5Str.append("zhongkong").append("yixiangbus_work_jiande").append("TcDmo9YJRPcKoJ4yhjkDC5bGdQS6TJad7qF4onXtLKisPTCh9YsY3jmYnih6zJ4P")
                .append(time);
        try{
            md5StrOut = MD5Util.getMD5Str(md5Str.toString());
        }catch (Exception e){
            System.out.println(e);
        }
        String url = "http://120.27.218.187:38080/cdl_sink/uploaddata/partner/zhongkong/data/yixiangbus_work_jiande?ts="+time+"&sn="+md5StrOut+"&city=330100";
        String content = "bus_id,bus_plate,current_guest,daily_guest,latitude,longitude,region,route_name,update_time\n" +
                "119,\"浙A1U930\",12,32,29.331886,119.328013,\"建德\",\"建德高铁站->新叶古村\",1563020960";
        String s = doPostJson(url, content);
        System.out.println(url+"\n"+content);
        System.out.println(s);
    }
}
