package com.java;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

public class javainterface {
    public static String doPostJson(String url, String json) {
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
    public static String getMD5Str(String str) throws Exception {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            throw new Exception("MD5加密出现错误，"+e.toString());
        }
    }
    public static void main(String[] args) {
        String md5StrOut = "";
        long time =System.currentTimeMillis()/1000;
        String md5Str = "zhongkong"+"yixiangbus_work_jiande"+"TcDmo9YJRPcKoJ4yhjkDC5bGdQS6TJad7qF4onXtLKisPTCh9YsY3jmYnih6zJ4P"+time;
        try{
            md5StrOut = getMD5Str(md5Str);
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
