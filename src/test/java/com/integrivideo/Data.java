package com.integrivideo;

import java.io.File;

/**
 * Created by Dmitry Rak on 4/15/2017.
 */
public class Data {

    //TODO get this config from some config file (will be useful in future)
    public final static String BASE_URL = "https://dev.integrivideo.com/";
    public final static String BILLING_PAGE = BASE_URL + "app/billing";
    public final static String CARDHOLDER_NAME = "Integri User";
    public final static String CARD_NUMBER = "378282246310005";
    public final static String CREATE_PROJECT_URL = BASE_URL + "app/projects/new";
    public final static String EDITED_MSG = "editText";
    public final static String EXPIRATION_MONTH = "12";
    public final static String EXPIRATION_YEAR = "2021";
    public static final File IMAGE_FILE_PATH = new File("target" + File.separator + "classes" + File.separator + "2kb.gif");
    public final static String IMAGE_URL_4K = "http://4k.com/wp-content/uploads/2014/06/4k-image-tiger-jumping.jpg";
    public final static String INSTALLED_CHAT_WITH_DOMAIN = "http://dmitryrak11.000webhostapp.com/COMPONENT_CHAT_WITH_DOMAIN/";
    public final static String LOGIN_PAGE_URL = BASE_URL + "login";
    public final static String NAME = "Guest";
    public final static String PROJECTS_LIST_URL = BASE_URL + "app/projects";
    public final static String PROJECT_DESCRIPTION = "PROJECT_DESCRIPTION";
    public final static String PROJECT_DOMAIN1 = "easyhire.me";
    public final static String PROJECT_NAME = "PROJECT_NAME";
    public final static String RANDOM_EMAIL = System.currentTimeMillis() + "@mailinator.com";
    public final static String RANDOM_PROJECT_DESCRIPTION = "PROJECT_DESCRIPTION" + System.currentTimeMillis();
    public final static String RANDOM_PROJECT_DOMAIN = System.currentTimeMillis() + "easyhire.me";
    public final static String RANDOM_PROJECT_NAME = "PROJECT_NAME" + System.currentTimeMillis();
    public final static String SECOND_LINE = "String on second line";
    public final static String SIGNUP_PAGE_URL = BASE_URL + "signup";
    public final static String SPECIAL_CHARS = Character.toString((char) 0x0601) + Character.toString((char) 0x0600) + Character.toString((char) 0x0602);
    public final static String TEST_CHAT_URL = BASE_URL + "demo/chat/new";
    public final static String TEST_MGS1 = "Test Message 1";
    public final static String USER_1_EMAIL = "integriuser1@mailinator.com";
    public final static String USER_1_PASSWORD = "integripassword";
    //TODO this user should be added directly into DB
    public final static String USER_2_EMAIL = "integriuser2@mailinator.com";
    public final static String USER_2_PASSWORD = "integripassword";
    public final static String XSS_TEXT = "<script>alert('TEXT');</script>";
    public final static int MAX_DEMO_MESSAGE_COUNT = 10;
}
