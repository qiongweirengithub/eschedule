package com.qw.study.scheduleclient.utils;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * @author qunar-qw
 * @date 18-7-13
 */
public class EscheduleHttpUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(EscheduleHttpUtils.class);

    public static boolean get(String url, Map<String, String> paramMap) throws IOException {

        LOGGER.info("invoke job for->  url: {}, param:{}" , url, paramMap.toString());


        FormEncodingBuilder builder = new FormEncodingBuilder();
        for (String key:paramMap.keySet()) {
            builder.add(key, paramMap.get(key));
        }

        RequestBody requestBody = builder.build();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url).post(requestBody).build();

        Response response = client.newCall(request).execute();

        return true;
    }


}