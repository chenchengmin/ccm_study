package com.baidu.translate.demo;
import com.baidu.translate.demo.TransApi;

public class Main {

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20180518000161404";
    private static final String SECURITY_KEY = "b_GEyQs0yoPeEXyy8LpL";

    public static void main(String[] args) {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = "高度600�?";
        System.out.println(api.getTransResult(query, "auto", "en"));
    }

}
