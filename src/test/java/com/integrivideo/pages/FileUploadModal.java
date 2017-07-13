package com.integrivideo.pages;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

/**
 * Created by Dmitry Rak on 5/1/2017.
 */
public class FileUploadModal extends PageObject{

    public static final By START_UPLOAD_BY= By.xpath("//button[contains(@class,'integri-file-upload-start')]");

    public static final By ADD_FILE_BY = By.xpath("//input[@type='file']");

    public void addFile(String filePath){
        find(ADD_FILE_BY).sendKeys(filePath);
    }

    public void startUpload(){
        //TODO for some reason this button is not visible
        find(START_UPLOAD_BY).click();
    }
}
