package com.integrivideo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.Base64;

/**
 * Created by asus on 5/2/2017.
 */
public class BitBucketIssueHelper {

    public static void main(String[] args) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        String encoding = Base64.getEncoder().encode("user:pass".getBytes()).toString();
        HttpGet httpget = new HttpGet("https://api.bitbucket.org/1.0/repositories/integrivideo/integrivideo-service/65");
        httpget.setHeader("Authorization", "Basic " + encoding);

        System.out.println("executing request " + httpget.getRequestLine());
        HttpResponse response = client.execute(httpget);
        HttpEntity entity = response.getEntity();
    }
}
