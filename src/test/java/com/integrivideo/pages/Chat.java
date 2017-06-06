package com.integrivideo.pages;

import com.integrivideo.Message;
import com.integrivideo.User;
import com.integrivideo.elements.WebElementHelper;
import org.openqa.selenium.*;
import org.testng.Reporter;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry Rak on 4/15/2017.
 */
public class Chat {
    private By inviteButtonBy = By.id("invite-users-to-chat");
    private By textInputBy = By.xpath("//div[contains(@class, 'integri-chat-input')]//textarea");
    private By ownMessagesBy = By.xpath("//div[contains(@class, 'integri-chat-message-own')]");
    private By sendButtonBy = By.xpath("//button[contains(@class,'integri-chat-send-message')]");
    private By fileUploadButtonBy = By.xpath("//button[contains(@class,'integri-chat-upload-file')]");
    private By usersPicsBy = By.xpath("//div[contains(@class, 'integri-chat-session')]");

    private WebDriver driver;
    private WebElement textInput;
    private WebElement sendButton;
    private WebElement fileUploadButton;
    private WebElement inviteButton;

    public void inputText(String text){
        textInput.sendKeys(text);
    }

    public void pressEnter(){
        textInput.sendKeys(Keys.ENTER);
    }
    public void clickSendButton(){
        sendButton.click();
    }

    public void pressShiftEnter(){
        String keysPressed =  Keys.chord(Keys.SHIFT, Keys.ENTER);
        textInput.sendKeys(keysPressed);
    }
    public List<User> getListOfUsers(){
        List<User> usersPics = new ArrayList<>();
        List<WebElement> usersPicsElements = driver.findElements(usersPicsBy);
        for(WebElement user: usersPicsElements){
            User userIcon = new User();
            userIcon.setSessionId(user.getAttribute("data-session-id"));
            userIcon.setUserName(user.findElement(By.xpath("//div[contains(@class,'integri-session-user-name')]")).getText());
            userIcon.setOnline(WebElementHelper.elementHasClass(user.findElement(By.xpath("//div[contains(@class,'integri-user-pic')]")),"integri-session-is-online"));
            usersPics.add(userIcon);
        }
        Reporter.log(driver.getPageSource(),false);
        return usersPics;
    }
    public List<Message> getOwnMessages(){
        List<Message> ownMessages = new ArrayList<>();
        List<WebElement> ownMessagesElements = driver.findElements(ownMessagesBy);
        for(WebElement mess: ownMessagesElements){
            Message message = new Message();
            message.setId(mess.getAttribute("data-message-id"));
            if(mess.getText().equals("removed...")){
                message.setRemoved(true);
            }else {
                message.setDate(mess.findElement(By.xpath("//span[contains(@class,'integri-chat-message-date')]")).getText());
                message.setUserName(mess.findElement(By.xpath("//span[contains(@class,'integri-chat-message-user-name')]")).getText());
                message.setText(mess.findElement(By.xpath("//div[contains(@class,'integri-chat-message-text')]")).getText());
                message.setEdited(WebElementHelper.elementHasClass(mess, "integri-chat-message-edited"));
                message.setOnline(WebElementHelper.elementHasClass(mess.findElement(By.xpath("//div[contains(@class,'integri-user-pic')]")),"integri-session-is-online"));
                if(mess.findElements(By.xpath("//div[contains(@class,'integri-chat-message-type-file')]")).size() > 0){
                    message.setFile(true);
                    message.setFileName(mess.findElement(By.xpath("//span[contains(@class,'integri-chat-message-attachment-file-name')]")).getText());
                    message.setFileLink(mess.findElement(By.xpath("//a[contains(@class,'integri-chat-message-attachment-file-link')]")).getAttribute("href"));
                    message.setSize(mess.findElement(By.xpath("//span[contains(@class,'integri-chat-message-attachment-file-name')]/small")).getText());
                }
            }
            ownMessages.add(message);
        }
        Reporter.log(driver.getPageSource(),false);
        return ownMessages;
    }

    public void editMessage(final String text, final String finalText) throws InterruptedException {
        WebElement element = driver.findElements(By.xpath("//div[contains(.,'"+text+"')]/..//span[contains(@class,'integri-chat-edit-message')]")).get(0);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
        WebElement messageText = driver.findElement(By.xpath("//textarea[.='"+text+"']"));
        if(null != finalText) {
            messageText.clear();
            messageText.sendKeys(finalText);
        }
        messageText.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
    }

    public void removeMessage(final String text) throws InterruptedException {
        WebElement element = driver.findElements(By.xpath("//div[contains(.,'"+text+"')]/..//span[contains(@class,'integri-chat-remove-message')]")).get(0);
        element.click();
        driver.switchTo().alert().accept();
        Thread.sleep(1000);
    }

    public void openFileUploadModal(){
        fileUploadButton.click();
    }
    public Chat(WebDriver driver){
        this.driver = driver;
        textInput = driver.findElement(textInputBy);
        sendButton = driver.findElement(sendButtonBy);
        fileUploadButton = driver.findElement(fileUploadButtonBy);
    }
    public String getInviteLink(){
        inviteButton = driver.findElement(inviteButtonBy);
        inviteButton.click();
        //JavascriptExecutor executor = (JavascriptExecutor)driver;
        //executor.executeScript("arguments[0].click();", inviteButton);
        String result = "";
        try {
            result = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }
}
