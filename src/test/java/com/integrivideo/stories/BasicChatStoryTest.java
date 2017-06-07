package com.integrivideo.stories;

import com.integrivideo.Data;
import com.integrivideo.Utils;
import com.integrivideo.steps.ChatSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

/**
 * Created by Dmitry Rak on 4/15/2017.
 */
@Story(BasicChatStoryTest.class)
@RunWith(SerenityRunner.class)
public class BasicChatStoryTest extends BaseTest{
    @Managed(driver="chrome", uniqueSession = true)
    WebDriver driver;

    @Steps
    ChatSteps chatSteps;

    @Test
    public void sendMessageUsingEnter() throws InterruptedException {
        String id = System.getProperty("webdriver.chrome.driver");
        String id2 = System.getProperty("webdriver.chrome.driver");
        driver.get("https://www.integrivideo.com/demo/chat/new");
        chatSteps.sendWithEnter(Data.TEST_MGS1);
        chatSteps.ownMessageShouldBeVisible(Data.TEST_MGS1, Utils.getCurrentTime());
    }

    @Test
    public void useOfShiftEnter() throws InterruptedException {
        driver.get("https://www.integrivideo.com/demo/chat/new");
        chatSteps.sendSeveralLines(Data.TEST_MGS1, Data.SECOND_LINE);
        chatSteps.ownMessageShouldBeVisible(Data.TEST_MGS1 + "\n" + Data.SECOND_LINE, Utils.getCurrentTime());
    }

    @Test
    public void sendMessageUsingButton() throws InterruptedException {
        driver.get("https://www.integrivideo.com/demo/chat/new");
        chatSteps.sendWithButton(Data.TEST_MGS1);
        chatSteps.ownMessageShouldBeVisible(Data.TEST_MGS1, Utils.getCurrentTime());
    }

    @Test
    public void sendMessageWithSpecialSymbols() throws InterruptedException {
        driver.get("https://www.integrivideo.com/demo/chat/new");
        chatSteps.sendWithButton(Data.SPECIAL_CHARS);
        chatSteps.ownMessageShouldBeVisible(Data.SPECIAL_CHARS, Utils.getCurrentTime());
    }

    @Test
    public void editMessage() throws InterruptedException {
        driver.get("https://www.integrivideo.com/demo/chat/new");
        chatSteps.sendWithEnter(Data.TEST_MGS1);
        chatSteps.editOwnMessage(Data.TEST_MGS1, Data.EDITED_MSG);
        chatSteps.ownMessageShouldBeVisible(Data.EDITED_MSG, Utils.getCurrentTime());
        chatSteps.ownMessageShouldBeShownAsEdited(Data.EDITED_MSG, Utils.getCurrentTime());
    }
    @Test
    public void removeMessage() throws InterruptedException {
        driver.get("https://www.integrivideo.com/demo/chat/new");
        chatSteps.sendWithEnter(Data.TEST_MGS1);
        chatSteps.ownMessageShouldBeShownAsRemoved(chatSteps.removeMessage(Data.TEST_MGS1));
    }
    @Test
    public void uploadFile(){
        driver.get(Data.TEST_CHAT_URL);
        String messageId = chatSteps.uploadFile(Data.IMAGE_FILE_PATH.getAbsolutePath());
        chatSteps.ownMessageShouldContainFileInfo(messageId, Data.IMAGE_FILE_PATH.getName() + " (2kb)");

    }
    @Test
    public void editMessageWithLineBreaks() throws InterruptedException {
        driver.get(Data.TEST_CHAT_URL);
        chatSteps.sendSeveralLines(Data.TEST_MGS1, Data.SECOND_LINE);
        chatSteps.editOwnMessage(Data.TEST_MGS1 + "\n" + Data.SECOND_LINE, null);
        chatSteps.ownMessageShouldBeVisible(Data.TEST_MGS1 + "\n" + Data.SECOND_LINE, Utils.getCurrentTime());
    }
    @Test
    public void escapingCharacters() throws InterruptedException {
        driver.get(Data.TEST_CHAT_URL);
        chatSteps.sendWithEnter(Data.XSS_TEXT);
        chatSteps.ownMessageShouldBeVisible(Data.XSS_TEXT, Utils.getCurrentTime());
    }
    //TODO handle IE11 asking for access to clipboard
    @Test
    public void inviteUserButton(){
        driver.get(Data.TEST_CHAT_URL);
        chatSteps.inviteLinkShouldBeLike(driver.getCurrentUrl());
    }
    @Test
    public void userInfoShouldBeCorrect(){
        driver.get(Data.TEST_CHAT_URL);
        chatSteps.userInfoShouldBeLike(1, Data.NAME, true);
    }
}
