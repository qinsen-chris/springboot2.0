package com.example.demo.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * Create by qs on 2019/1/21
 * email:qinsen@chinaredsun.com
 */
@Slf4j
public class PostService {

    /**
     * 筋斗云短信发送、
     * @param url
     * @param jsonObject
     * @return
     */
    public static String postForJson(String url, JSONObject jsonObject) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        RestTemplate restTemplate = new RestTemplateBuilder().additionalMessageConverters(converter).build();

        //JSONObject jsonObject1 = JSONObject.parseObject(jsonString);
        jsonObject.put("apiKey", "");
        jsonObject.put("tpl_Id", "");
        jsonObject.put("content", "");
        jsonObject.put("mobile","");

        HttpEntity<String> httpEntity = new HttpEntity<String>(jsonObject.toJSONString(), headers);
        log.info("请求url:{}", url);
        log.info("请求param:{}", jsonObject.toJSONString());
        String result = restTemplate.postForObject(url, httpEntity, String.class);
        log.info("返回数据result:{}", result);
        return result;
    }
}
