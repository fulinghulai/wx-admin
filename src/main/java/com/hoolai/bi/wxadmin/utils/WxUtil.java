package com.hoolai.bi.wxadmin.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;

public class WxUtil {
    public static final String SECRET = "a3a168859eac7785d33d47aabd599d58";
    public static final String APPID = "wxb3a2364aa99e9554";
    public  static final String APPURL = "https://api.weixin.qq.com/sns/jscode2session";

    public WxUtil(){}

    public static JSONObject produceWxInfo(String code){
        String url = APPURL+"?appid="+ APPID +"&secret="+ SECRET +"&js_code="+code+"&grant_type=authorization_code";
        String data = HttpClientUtil.client(url, HttpMethod.GET,new LinkedMultiValueMap<>());
        return JSON.parseObject(data);
    }
}

