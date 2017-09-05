package com.integrivideo;

import com.integrivideo.steps.ComponentSteps;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

/**
 * Created by Dmitry Rak on 4/15/2017.
 */
public class Utils {

    private static final Logger LOGGER = Logger.getLogger(ComponentSteps.class.getName());

    public static String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy, HH:mm");
        return now.format(formatter);
    }

    public static long getSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    public static String getTextFromClipboard() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        String jsFromClipboard = null;
        try {
            jsFromClipboard = (String) clipboard.getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info("Content of clipboard: " + jsFromClipboard);
        return  jsFromClipboard;
    }
}
