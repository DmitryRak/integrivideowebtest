package com.integrivideo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Dmitry Rak on 5/1/2017.
 */
public class FileUploadModal {
    private By closeModalBy = By.xpath("//span[contains(@class, 'close-integri-modal')]");
    private By uploadStartBy = By.xpath("//button[contains(@class, 'integri-file-upload-start')]");
    private By uploadCancelBy = By.xpath("//button[contains(@class, 'integri-file-upload-cancel')]");
    private By fileToUploadBy = By.xpath("//input[@type='file']");


    private WebDriver driver;
    private WebElement closeModal;
    private WebElement uploadStart;
    private WebElement uploadCancel;
    private WebElement fileToUpload;

    public FileUploadModal(WebDriver webDriver){
        driver = webDriver;
        closeModal = driver.findElement(closeModalBy);
        uploadStart = driver.findElement(uploadStartBy);
        uploadCancel = driver.findElement(uploadCancelBy);
        fileToUpload = driver.findElement(fileToUploadBy);
    }

    public void addFile(String filePath){
        fileToUpload.sendKeys(filePath);
    }

    public void startUpload(){
        uploadStart.click();
    }

}
