package com.ffait.examine;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.net.URIBuilder;

import com.ffait.util.ParameterOperate;
import com.google.gson.Gson;



public class Examination {

//	public String url = "http://183.6.56.22:9999/common/outlineservice/examStart";
//	public String nextUrl="http://183.6.56.22:9999/common/outlineservice/answerQuestion";
//	public String submitUrl="http://183.6.56.22:9999/common/outlineservice/submitExam";
	
	public String url = "";
	public String nextUrl="";
	public String submitUrl="";
	
	
    public Examination() {
		this.url = ParameterOperate.extract("ExamStartURL");
		this.nextUrl = ParameterOperate.extract("NextAuestionURL");
		this.submitUrl = ParameterOperate.extract("SubmitExamURL");
	}


	/**
     * 发送get请求
     * @param url 开始调用http服务
     * @param param 请求参数 key:value url携带参数 或者无参可不填
     * @return
     */
    public String doGet(Map<String, String> param) {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(this.url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    
    /**
     * 发送get请求
     * @param url 调用下一个题目服务
     * @param param 请求参数 key:value url携带参数 或者无参可不填
     * @return
     */
    public  String nextGet(Map<String, String> param) {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(this.nextUrl);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 发送get请求
     * @param url 提交数据，获得分数。
     * @param param 请求参数 key:value url携带参数 或者无参可不填
     * @return
     */
    public String submitGet(Map<String, String> param) {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(this.submitUrl);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }
    
    /**
     * 解析json数据
     */
    public static <T> T JsonToObject(String jsonData,Class<T> type) {
    	Gson gson=new Gson();
    	T result=gson.fromJson(jsonData, type);
    	return result;
    }
}
