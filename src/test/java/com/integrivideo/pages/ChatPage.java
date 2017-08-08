package com.integrivideo.pages;

import com.integrivideo.Message;
import com.integrivideo.User;
import com.integrivideo.elements.WebElementHelper;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry Rak on 4/15/2017.
 */
public class ChatPage extends PageObject {
    private final By OWN_MESSAGES_BY = By.xpath("//div[contains(@class, 'integri-chat-message-own')]");
    private final By USER_PICS_BY = By.xpath("//div[contains(@class, 'integri-chat-session')]");
    private final By TEXT_INPUT = By.xpath("//div[contains(@class, 'integri-chat-input')]//textarea");
    @FindBy(xpath = "//button[contains(@class,'integri-chat-upload-file')]")
    WebElement fileUploadButton;
    @FindBy(id = "invite-users-to-chat")
    WebElement inviteButton;
    @FindBy(xpath = "//button[contains(@class,'integri-chat-send-message')]")
    WebElement sendButton;
    @FindBy(xpath = "//button[contains(@class,'integri-show-user-settings')]")
    WebElement userSettingsButton;

    public void inputText(String text) {
        find(TEXT_INPUT).sendKeys(text);
    }

    public void pressEnter() {
        find(TEXT_INPUT).sendKeys(Keys.ENTER);
    }

    public void clickSendButton() {
        sendButton.click();
    }

    public void pressShiftEnter() {
        String keysPressed = Keys.chord(Keys.SHIFT, Keys.ENTER);
        find(TEXT_INPUT).sendKeys(keysPressed);
    }

    public List<User> getListOfUsers() {
        List<User> usersPics = new ArrayList<>();
        List<WebElementFacade> usersPicsElements = findAll(USER_PICS_BY);
        for (WebElementFacade user : usersPicsElements) {
            User userIcon = new User();
            userIcon.setSessionId(user.getAttribute("data-session-id"));
            userIcon.setUserName(user.findElement(By.xpath("//div[contains(@class,'integri-session-user-name')]")).getText());
            userIcon.setOnline(WebElementHelper.elementHasClass(user.findElement(By.xpath("//div[contains(@class,'integri-user-pic')]")), "integri-session-is-online"));
            usersPics.add(userIcon);
        }
        return usersPics;
    }

    public List<Message> getOwnMessages() {
        List<Message> ownMessages = new ArrayList<>();

        List<WebElementFacade> ownMessagesElements = findAll(OWN_MESSAGES_BY);
        for (WebElementFacade mess : ownMessagesElements) {
            Message message = new Message();
            message.setId(mess.getAttribute("data-message-id"));
            message.setText(mess.findElement(By.xpath("//div[contains(@class,'integri-chat-message-text')]")).getText());
            if (message.getText().equals("removed...")) {
                message.setRemoved(true);
            } else {
                message.setDate(mess.findElement(By.xpath("//span[contains(@class,'integri-chat-message-date')]")).getText());
                message.setUserName(mess.findElement(By.xpath("//span[contains(@class,'integri-session-user-name')]")).getText());
                message.setEdited(WebElementHelper.elementHasClass(mess, "integri-chat-message-edited"));
//                message.setOnline(WebElementHelper.elementHasClass(mess.findElement(By.xpath("//div[contains(@class,'integri-user-pic')]")),"integri-session-is-online"));
                if (mess.findElements(By.xpath("//div[contains(@class,'integri-chat-message-type-file')]")).size() > 0) {
                    message.setFile(true);
                    message.setFileName(mess.findElement(By.xpath("//span[contains(@class,'integri-chat-message-attachment-file-name')]")).getText());
                    message.setFileLink(mess.findElement(By.xpath("//a[contains(@class,'integri-chat-message-attachment-file-link')]")).getAttribute("href"));
                    message.setSize(mess.findElement(By.xpath("//span[contains(@class,'integri-chat-message-attachment-file-name')]/small")).getText());
                }
            }
            ownMessages.add(message);
        }
        return ownMessages;
    }

    public void editMessage(final int messageNumber, final String finalText) throws InterruptedException {
        WebElement messageContainer = findAll(By.xpath("//div[contains(@class,'integri-chat-message-container')]")).get(messageNumber - 1);
        WebElement button = messageContainer.findElement(By.xpath("//span[contains(@class,'integri-chat-edit-message')]"));
        button.click();
        WebElement messageText = messageContainer.findElement(By.tagName("textarea"));
        if (null != finalText) {
            messageText.clear();
            messageText.sendKeys(finalText);
        }
        messageText.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
    }

    public void removeMessage(final int messageNumber) throws InterruptedException {
        WebElement messageContainer = findAll(By.xpath("//div[contains(@class,'integri-chat-message-container')]")).get(messageNumber - 1);
        WebElement button = messageContainer.findElement(By.xpath("//span[contains(@class,'integri-chat-remove-message')]"));
        button.click();
        getAlert().accept();
        Thread.sleep(1000);
    }

    public void openFileUploadModal() {
        fileUploadButton.click();
    }

    public String getInviteLink() {
        inviteButton.click();
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

    public String getMessageText(final int messageNumber) {
        WebElement messageContainer = findAll(By.xpath("//div[contains(@class,'integri-chat-message-container')]")).get(messageNumber);
        return messageContainer.findElement(By.xpath("//div[contains(@class,'integri-chat-message-text')]")).getText();
    }

    public void openUserSettingsModal() {
        clickOn(userSettingsButton);
    }
}
