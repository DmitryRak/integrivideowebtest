package com.bitbucket;

import java.util.Base64;

import static io.restassured.RestAssured.given;

/**
 * Created by drak on 5/2/2017.
 */
public abstract class BitBucketIssueHelper {

    public static String getIssueDetails(String user, String password, String issueNumber) {
        String credentials = user + ":" + password;
        String encoding = new String(Base64.getEncoder().encode(credentials.getBytes()));
        return given().
                log().all().
                header("Authorization", "Basic " + encoding).
        when().
                get("http://api.bitbucket.org/1.0/repositories/integrivideo/integrivideo-service/issues/" + issueNumber).
        then().
                statusCode(200).
                log().all().
        extract().
                path("status");
    }

    public static void main(String[] args) {
        System.out.println(getIssueDetails("email", "password", "69"));
    }
}
