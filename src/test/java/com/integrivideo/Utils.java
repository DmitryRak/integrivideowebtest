package com.integrivideo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Dmitry Rak on 4/15/2017.
 */
public class Utils {
    public static String getCurrentTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy, HH:mm");
        return now.format(formatter);
    }
}
