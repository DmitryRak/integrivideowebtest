package com.integrivideo.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

/**
 * Created by Dmitry Rak on 5/1/2017.
 */
public class FileUploadModal extends PageObject{
    @FindBy(xpath="//span[contains(@class, 'close-integri-modal')]")
    WebElement closeModal;
    @FindBy(xpath="//button[contains(@class, 'integri-file-upload-start')]")
    WebElement uploadStart;
    @FindBy(xpath="//button[contains(@class, 'integri-file-upload-cancel')]")
    WebElement uploadCancel;
    @FindBy(xpath="//input[@type='file']")
    WebElement fileToUpload;

    public void addFile(String filePath){
        fileToUpload.sendKeys(filePath);
    }

    public void startUpload(){
        clickOn(uploadStart);
    }

}
