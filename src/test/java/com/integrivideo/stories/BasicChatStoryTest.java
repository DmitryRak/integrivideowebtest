package com.integrivideo.stories;

import com.integrivideo.Data;
import com.integrivideo.Utils;
import com.integrivideo.steps.ChatSteps;
import org.testng.annotations.Test;

/**
 * Created by Dmitry Rak on 4/15/2017.
 */

public class BasicChatStoryTest extends BaseTest{

    private ChatSteps chatSteps;

    @Test
    public void sendMessageUsingEnter() throws InterruptedException {
        driver.get(Data.testChatUrl);
        chatSteps = new ChatSteps(driver);
        chatSteps.sendWithEnter(Data.TEST_MGS1);
        chatSteps.ownMessageShouldBeVisible(Data.TEST_MGS1, Utils.getCurrentTime());
    }

    @Test
    public void useOfShiftEnter() throws InterruptedException {
        driver.get(Data.testChatUrl);
        chatSteps = new ChatSteps(driver);
        chatSteps.sendSeveralLines(Data.TEST_MGS1, Data.SECOND_LINE);
        chatSteps.ownMessageShouldBeVisible(Data.TEST_MGS1 + "\n" + Data.SECOND_LINE, Utils.getCurrentTime());
    }

    @Test
    public void sendMessageUsingButton() throws InterruptedException {
        driver.get(Data.testChatUrl);
        chatSteps = new ChatSteps(driver);
        chatSteps.sendWithButton(Data.TEST_MGS1);
        chatSteps.ownMessageShouldBeVisible(Data.TEST_MGS1, Utils.getCurrentTime());
    }

    @Test
    public void sendMessageWithSpecialSymbols() throws InterruptedException {
        driver.get(Data.testChatUrl);
        chatSteps = new ChatSteps(driver);
        chatSteps.sendWithButton(Data.SPECIAL_CHARS);
        chatSteps.ownMessageShouldBeVisible(Data.SPECIAL_CHARS, Utils.getCurrentTime());
    }

    @Test
    public void editMessage() throws InterruptedException {
        driver.get(Data.testChatUrl);
        chatSteps = new ChatSteps(driver);
        chatSteps.sendWithEnter(Data.TEST_MGS1);
        chatSteps.editOwnMessage(Data.TEST_MGS1, Data.EDITED_MSG);
        chatSteps.ownMessageShouldBeVisible(Data.EDITED_MSG, Utils.getCurrentTime());
        chatSteps.ownMessageShouldBeShownAsEdited(Data.EDITED_MSG, Utils.getCurrentTime());
    }
    @Test
    public void removeMessage() throws InterruptedException {
        driver.get(Data.testChatUrl);
        chatSteps = new ChatSteps(driver);
        chatSteps.sendWithEnter(Data.TEST_MGS1);
        chatSteps.ownMessageShouldBeShownAsRemoved(chatSteps.removeMessage(Data.TEST_MGS1));
    }
    @Test
    public void uploadFile(){
        driver.get(Data.testChatUrl);
        chatSteps = new ChatSteps(driver);
        chatSteps.uploadFile(Data.IMAGE_FILE_PATH.getAbsolutePath());
        //chatSteps.ownMessageShouldBeVisible(Data.IMAGE_FILE_PATH.getName(), Utils.getCurrentTime());
        //TODO
        //1. Open link on click
        //2. CHeck fileSize
        //3. Check remove
        //4. Check no edit
    }
}
