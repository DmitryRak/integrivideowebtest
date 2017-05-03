package com.integrivideo;

import java.io.File;

/**
 * Created by Dmitry Rak on 4/15/2017.
 */
public class Data {
    public final static String testChatUrl = "https://www.integrivideo.com/demo/chat/new";
    public final static String TEST_MGS1 = "Test Message 1";
    public final static String SECOND_LINE = "String on second line";
    public final static String SPECIAL_CHARS = Character.toString((char)0x0601) + Character.toString((char)0x0600) + Character.toString((char)0x0602);
    public final static String EDITED_MSG = "editText";
    public static final File IMAGE_FILE_PATH = new File("target" +File.separator + "classes" + File.separator + "image1.gif");
    public final static String XSS_TEXT = "<script>alert('TEXT');</script>";

}
