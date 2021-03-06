package com.integrivideo.pages;

import com.integrivideo.Message;
import com.integrivideo.User;
import com.integrivideo.Utils;
import com.integrivideo.elements.WebElementHelper;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry Rak on 4/15/2017.
 */
public class ChatPage extends PageObject {

    private final static String MESSAGE_TEXT_LOCATOR = "//div[contains(@class,'integri-chat-message-text')]";
    private final static String ATTACHMENT_FILE_NAME_LOCATOR = "//span[contains(@class,'integri-chat-message-attachment-file-name')]";
    private final static String MESSAGE_CONTAINER_LOCATOR = "//div[contains(@class,'integri-chat-message-container')]";

    private final static By OWN_MESSAGES_BY = By.xpath("//div[contains(@class, 'integri-chat-message-own')]");
    private final static By TEXT_INPUT = By.xpath("//div[contains(@class, 'integri-chat-input')]//textarea");
    private final static By USER_PICS_BY = By.xpath("//div[contains(@class, 'integri-chat-session')]");
    private final static By DEMO_DISCLAIMER_BY = By.xpath("//div[contains(@class, 'integri-demo-version')]");
    @FindBy(xpath = "//span[contains(@class,'integri-chat-manual-upload')]")
    private WebElement fileUploadButton;
    @FindBy(id = "invite-users-to-chat")
    private WebElement inviteButton;
    @FindBy(xpath = "//button[contains(@class,'integri-chat-send-message')]")
    private WebElement sendButton;
    @FindBy(xpath = "//span[contains(@class,'integri-chat-settings')]")
    private WebElement userSettingsButton;

    public void inputText(String text) {
        find(TEXT_INPUT).sendKeys(text);
    }

    public void pressEnter() {
        find(TEXT_INPUT).sendKeys(Keys.ENTER);
    }

    public void clickSendButton() {
        clickOn(sendButton);
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
            message.setText(mess.findElement(By.xpath(MESSAGE_TEXT_LOCATOR)).getText());
            if (message.getText().equals("removed...")) {
                message.setRemoved(true);
            } else {
                message.setDate(mess.findElement(By.xpath("//span[contains(@class,'integri-chat-message-date')]")).getText());
                message.setUserName(mess.findElement(By.xpath("//span[contains(@class,'integri-session-user-name')]")).getText());
                message.setEdited(WebElementHelper.elementHasClass(mess, "integri-chat-message-edited"));
                //                message.setOnline(WebElementHelper.elementHasClass(mess.findElement(By.xpath("//div[contains(@class,'integri-user-pic')]")),"integri-session-is-online"));
                if (mess.findElements(By.xpath("//div[contains(@class,'integri-chat-message-type-file')]")).size() > 0) {
                    message.setFile(true);
                    message.setFileName(mess.findElement(By.xpath(ATTACHMENT_FILE_NAME_LOCATOR)).getText());
                    message.setFileLink(mess.findElement(By.xpath("//a[contains(@class,'integri-chat-message-attachment-file-link')]")).getAttribute("href"));
                    message.setSize(mess.findElement(By.xpath(ATTACHMENT_FILE_NAME_LOCATOR.concat("/small"))).getText());
                }
            }
            ownMessages.add(message);
        }
        return ownMessages;
    }

    public void editMessage(final int messageNumber, final String finalText) {
        WebElement messageContainer = findAll(By.xpath(MESSAGE_CONTAINER_LOCATOR)).get(messageNumber - 1);
        WebElement button = messageContainer.findElement(By.xpath("//span[contains(@class,'integri-chat-edit-message')]"));
        clickOn(button);
        WebElement messageText = messageContainer.findElement(By.tagName("textarea"));
        if (null != finalText) {
            messageText.clear();
            messageText.sendKeys(finalText);
        }
        messageText.sendKeys(Keys.ENTER);
        waitABit(1000);
    }

    public void removeMessage(final int messageNumber) {
        WebElement messageContainer = findAll(By.xpath(MESSAGE_CONTAINER_LOCATOR)).get(messageNumber - 1);
        WebElement button = messageContainer.findElement(By.xpath("//span[contains(@class,'integri-chat-remove-message')]"));
        clickOn(button);
        getAlert().accept();
        waitABit(1000);
    }

    public void openFileUploadModal() {
        clickOn(fileUploadButton);
    }

    public String getInviteLink() {
        clickOn(inviteButton);
        return Utils.getTextFromClipboard();
    }

    public String getMessageText(final int messageNumber) {
        WebElement messageContainer = findAll(By.xpath(MESSAGE_CONTAINER_LOCATOR)).get(messageNumber);
        return messageContainer.findElement(By.xpath(MESSAGE_TEXT_LOCATOR)).getText();
    }

    public WebElement getAttachment(final int messageNumber) {
        String attachmentXpath = String.format(MESSAGE_CONTAINER_LOCATOR.concat("[%s]/div[contains(@class,'integri-chat-message')]/div[contains(@class,'integri-chat-message-attachment')]/a[2]"), messageNumber);
        WebElement attachment = waitFor(findAll(By.xpath(attachmentXpath)).get(0));
        return attachment;
    }

    public void openUserSettingsModal() {
        waitABit(500);
        clickOn(userSettingsButton);
    }

    public boolean isThisIsTrialVersionModalShown() {
        return find(DEMO_DISCLAIMER_BY).isDisplayed();
    }
}
