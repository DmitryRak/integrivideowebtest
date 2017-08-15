package com.integrivideo.stories;

import com.integrivideo.Data;
import com.integrivideo.steps.ChatSteps;
import com.integrivideo.steps.CommonSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.integrivideo.Data.SPECIAL_CHARS;

/**
 * Created by Dmitry Rak on 4/15/2017.
 */
@Story(BasicChatStoryTest.class)
@RunWith(SerenityRunner.class)
public class BasicChatStoryTest extends BaseTest {

    @Steps
    ChatSteps chatSteps;

    @Steps
    CommonSteps commonSteps;

    @Test
    public void sendMessageUsingEnter() throws InterruptedException {
        chatSteps.openDemoChat();
        chatSteps.sendWithEnter(Data.TEST_MGS1);
        chatSteps.messageTextShouldBeLike(1, Data.TEST_MGS1);
    }

    @Test
    public void useOfShiftEnter() throws InterruptedException {
        chatSteps.openDemoChat();
        chatSteps.sendSeveralLines(Data.TEST_MGS1, Data.SECOND_LINE);
        chatSteps.messageTextShouldBeLike(1, Data.TEST_MGS1 + "\n" + Data.SECOND_LINE);
    }

    @Test
    public void sendMessageUsingButton() throws InterruptedException {
        chatSteps.openDemoChat();
        chatSteps.sendWithButton(Data.TEST_MGS1);
        chatSteps.messageTextShouldBeLike(1, Data.TEST_MGS1);
    }

    @Test
    public void sendMessageWithSpecialSymbols() throws InterruptedException {
        chatSteps.openDemoChat();
        chatSteps.sendWithButton(SPECIAL_CHARS);
        chatSteps.messageTextShouldBeLike(1, SPECIAL_CHARS);
    }

    @Test
    public void editMessage() throws InterruptedException {
        chatSteps.openDemoChat();
        chatSteps.sendWithEnter(Data.TEST_MGS1);
        chatSteps.editMessage(1, Data.EDITED_MSG);
        chatSteps.messageTextShouldBeLike(1, Data.EDITED_MSG);
        chatSteps.messageShouldBeShownAsEdited(1);
    }

    @Test
    public void removeMessage() throws InterruptedException {
        chatSteps.openDemoChat();
        chatSteps.sendWithEnter(Data.TEST_MGS1);
        chatSteps.removeMessage(1);
        chatSteps.messageShouldBeShownAsRemoved(1);
    }

    @Test
    public void uploadFile() {
        chatSteps.openDemoChat();
        chatSteps.uploadFile(Data.IMAGE_FILE_PATH.getAbsolutePath());
        chatSteps.messageShouldContainFileInfo(1, Data.IMAGE_FILE_PATH.getName() + " (2kb)");

    }

    @Test
    public void editMessageWithLineBreaks() throws InterruptedException {
        chatSteps.openDemoChat();
        chatSteps.sendSeveralLines(Data.TEST_MGS1, Data.SECOND_LINE);
        chatSteps.editMessage(1, null);
        chatSteps.messageTextShouldBeLike(1, Data.TEST_MGS1 + "\n" + Data.SECOND_LINE);
    }

    @Test
    public void escapingCharacters() throws InterruptedException {
        chatSteps.openDemoChat();
        chatSteps.sendWithEnter(Data.XSS_TEXT);
        chatSteps.messageTextShouldBeLike(1, Data.XSS_TEXT);
    }

    //TODO handle IE11 asking for access to clipboard
    @Test
    public void inviteUserButton() {
        chatSteps.openDemoChat();
        chatSteps.inviteLinkShouldBeLike(driver.getCurrentUrl());
        commonSteps.notificationMessageShouldBeLike("Link was copied");
    }

    @Test
    public void userInfoShouldBeCorrect() {
        chatSteps.openDemoChat();
        chatSteps.userInfoShouldBeLike(1, Data.NAME, true);
    }

    @Test
    public void userSettingsCanBeUpdated() {
        chatSteps.openDemoChat();
        chatSteps.openSettingsModal();
        chatSteps.updateUserSettings(Data.SPECIAL_CHARS, Data.USER_2_EMAIL, Data.IMAGE_URL_4K);
        chatSteps.openSettingsModal();
        chatSteps.validateUserSettings(Data.SPECIAL_CHARS, Data.USER_2_EMAIL, Data.IMAGE_URL_4K);
    }
}
