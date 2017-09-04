package com.integrivideo.modals;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

/**
 * Created by Dmitry Rak on 5/1/2017.
 */
public class FileUploadModal extends PageObject {

    private static final By ADD_FILE_BY = By.xpath("//input[@type='file']");
    private static final By START_UPLOAD_BY = By.xpath("//button[contains(@class,'integri-file-upload-start')]");

    public void addFile(String filePath) {
        find(ADD_FILE_BY).sendKeys(filePath);
    }

    public void startUpload() {
        clickOn(find(START_UPLOAD_BY));
    }
}
