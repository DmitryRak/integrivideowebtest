package com.integrivideo.stories;

import com.integrivideo.Data;
import com.integrivideo.steps.ChatSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Dmitry Rak on 4/15/2017.
 */
@Story(BasicChatStoryTest.class)
@RunWith(SerenityRunner.class)
public class BasicChatStoryTest extends BaseTest{

    @Steps
    ChatSteps chatSteps;

    @Test
    public void sendMessageUsingEnter() throws InterruptedException {
        driver.get(Data.TEST_CHAT_URL);
        chatSteps.sendWithEnter(Data.TEST_MGS1);
        chatSteps.messageTextShouldBeLike(1, Data.TEST_MGS1);
    }

    @Test
    public void useOfShiftEnter() throws InterruptedException {
        driver.get(Data.TEST_CHAT_URL);
        chatSteps.sendSeveralLines(Data.TEST_MGS1, Data.SECOND_LINE);
        chatSteps.messageTextShouldBeLike(1, Data.TEST_MGS1 + "\n" + Data.SECOND_LINE);
    }

    @Test
    public void sendMessageUsingButton() throws InterruptedException {
        driver.get(Data.TEST_CHAT_URL);
        chatSteps.sendWithButton(Data.TEST_MGS1);
        chatSteps.messageTextShouldBeLike(1, Data.TEST_MGS1);
    }

    @Test
    public void sendMessageWithSpecialSymbols() throws InterruptedException {
        driver.get(Data.TEST_CHAT_URL);
        chatSteps.sendWithButton(Data.SPECIAL_CHARS);
        chatSteps.messageTextShouldBeLike(1, Data.SPECIAL_CHARS);
    }

    @Test
    public void editMessage() throws InterruptedException {
        driver.get(Data.TEST_CHAT_URL);
        chatSteps.sendWithEnter(Data.TEST_MGS1);
        chatSteps.editMessage(1, Data.EDITED_MSG);
        chatSteps.messageTextShouldBeLike(1, Data.EDITED_MSG);
        chatSteps.messageShouldBeShownAsEdited(1);
    }
    @Test
    public void removeMessage() throws InterruptedException {
        driver.get(Data.TEST_CHAT_URL);
        chatSteps.sendWithEnter(Data.TEST_MGS1);
        chatSteps.removeMessage(1);
        chatSteps.messageShouldBeShownAsRemoved(1);
    }
    @Test
    public void uploadFile(){
        driver.get(Data.TEST_CHAT_URL);
        chatSteps.uploadFile(Data.IMAGE_FILE_PATH.getAbsolutePath());
        chatSteps.messageShouldContainFileInfo(1, Data.IMAGE_FILE_PATH.getName() + " (2kb)");

    }
    @Test
    public void editMessageWithLineBreaks() throws InterruptedException {
        driver.get(Data.TEST_CHAT_URL);
        chatSteps.sendSeveralLines(Data.TEST_MGS1, Data.SECOND_LINE);
        chatSteps.editMessage(1, null);
        chatSteps.messageTextShouldBeLike(1, Data.TEST_MGS1 + "\n" + Data.SECOND_LINE);
    }
    @Test
    public void escapingCharacters() throws InterruptedException {
        driver.get(Data.TEST_CHAT_URL);
        chatSteps.sendWithEnter(Data.XSS_TEXT);
        chatSteps.messageTextShouldBeLike(1, Data.XSS_TEXT);
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
