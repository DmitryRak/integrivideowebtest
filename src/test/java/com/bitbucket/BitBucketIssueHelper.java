package com.bitbucket;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.Base64;
import java.util.Scanner;

/**
 * Created by drak on 5/2/2017.
 */
public abstract class BitBucketIssueHelper {

    public static void getIssueDetails(String user, String password, String issueNumber) {
        HttpClient client = HttpClientBuilder.create().build();
        String credentials = user + ":" + password;
        String encoding = new String(Base64.getEncoder().encode(credentials.getBytes()));
        HttpGet httpget = new HttpGet("http://api.bitbucket.org/1.0/repositories/integrivideo/integrivideo-service/issues/" + issueNumber);
        httpget.setHeader("Authorization", "Basic " + encoding);

        System.out.println("executing request " + httpget.getRequestLine());
        HttpResponse response = null;
        try {
            response = client.execute(httpget);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        Scanner scan = null;
        try {
            scan = new Scanner(entity.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }
    }
}
