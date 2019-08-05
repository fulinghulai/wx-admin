package com.hoolai.bi.wxadmin.utils;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class HttpClientUtil {
    public static String client(String url, HttpMethod method, MultiValueMap<String,String> params){
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.getForEntity(url,String.class);
        return response.getBody();
    }
}
