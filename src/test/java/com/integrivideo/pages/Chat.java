package com.integrivideo.pages;

import com.integrivideo.Message;
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
    private By textInputBy = By.xpath("//integri-div[contains(@class, 'integri-chat-input')]//textarea");
    private By ownMessagesBy = By.xpath("//integri-div[contains(@class, 'integri-chat-message-own')]");
    private By sendButtonBy = By.xpath("//integri-button[contains(@class,'integri-chat-send-message')]");
    private By fileUploadButtonBy = By.xpath("//integri-button[contains(@class,'integri-chat-upload-file')]");

    private WebDriver driver;
    private WebElement textInput;
    private WebElement sendButton;
    private WebElement fileUploadButton;
    private WebElement inviteButton;
    private List<WebElement> ownMessagesElements = new ArrayList<>();
    private List <Message> ownMessages;

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

    public List<Message> getOwnMessages(){
        ownMessages = new ArrayList<>();
        ownMessagesElements = driver.findElements(ownMessagesBy);
        for(WebElement mess:ownMessagesElements){
            Message message = new Message();
            message.setId(mess.getAttribute("data-message-id"));
            if(mess.getText().equals("removed...")){
                message.setRemoved(true);
            }else {
                message.setDate(mess.findElement(By.xpath("//integri-span[contains(@class,'integri-chat-message-date')]")).getText());
                message.setUserName(mess.findElement(By.xpath("//integri-span[contains(@class,'integri-chat-message-user-name')]")).getText());
                message.setText(mess.findElement(By.xpath("//integri-div[contains(@class,'integri-chat-message-text')]")).getText());
                message.setEdited(WebElementHelper.elementHasClass(mess, "integri-chat-message-edited"));
                //TODO add isOnline
            }
            ownMessages.add(message);
        }
        Reporter.log(driver.getPageSource(),false);
        return ownMessages;
    }

    public void editMessage(final String text, final String finalText) throws InterruptedException {
        WebElement element = driver.findElements(By.xpath("//integri-div[contains(.,'"+text+"')]/..//integri-span[contains(@class,'integri-chat-edit-message')]")).get(0);;
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
        WebElement element = driver.findElements(By.xpath("//integri-div[contains(.,'"+text+"')]/..//integri-span[contains(@class,'integri-chat-remove-message')]")).get(0);
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
