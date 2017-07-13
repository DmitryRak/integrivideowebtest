package com.integrivideo;

import java.io.File;

/**
 * Created by Dmitry Rak on 4/15/2017.
 */
public class Data {
    //TODO get this config from some config file (will be useful in future)
    public final static String BASE_URL = "https://dev.integrivideo.com/";
    public final static String TEST_CHAT_URL = BASE_URL + "demo/chat/new";
    public final static String SIGNUP_PAGE_URL = BASE_URL + "signup";
    public final static String LOGIN_PAGE_URL = BASE_URL + "login";
    public final static String PROJECTS_PAGE_URL = BASE_URL + "app/projects";
    public final static String TEST_MGS1 = "Test Message 1";
    public final static String SECOND_LINE = "String on second line";
    public final static String SPECIAL_CHARS = Character.toString((char)0x0601) + Character.toString((char)0x0600) + Character.toString((char)0x0602);
    public final static String EDITED_MSG = "editText";
    public static final File IMAGE_FILE_PATH = new File("target" +File.separator + "classes" + File.separator + "2kb.gif");
    public final static String XSS_TEXT = "<script>alert('TEXT');</script>";
    public final static String NAME = "Anonymous";
    public final static String USER_1_EMAIL = "integriuser1@mailinator.com";
    public final static String USER_1_PASSWORD = "integripassword";
    public final static String USER_2_EMAIL = "integriuser2@mailinator.com";
    public final static String USER_2_PASSWORD = "integripassword";
    public final static String RANDOM_EMAIL = System.currentTimeMillis() +"@mailinator.com";

}
