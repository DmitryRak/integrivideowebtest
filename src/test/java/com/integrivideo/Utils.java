package com.integrivideo;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

/**
 * Created by Dmitry Rak on 4/15/2017.
 */
public class Utils {

    private static final Logger LOGGER = Logger.getLogger(Utils.class.getName());

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
        return jsFromClipboard;
    }

    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void prepareDownloadFolder() {
        File downloadFolder = new File(Data.DOWNLOAD_FOLDER);

        //Create folder if does not exist
        if (!downloadFolder.exists()) {
            try {
                downloadFolder.mkdir();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }

        //Delete files and directories in the folder
        try {
            FileUtils.cleanDirectory(downloadFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
