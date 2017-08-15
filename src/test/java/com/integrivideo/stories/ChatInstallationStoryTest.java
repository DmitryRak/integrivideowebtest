package com.integrivideo.stories;

import com.integrivideo.Data;
import com.integrivideo.steps.ChatSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import org.junit.Test;
import org.junit.runner.RunWith;

@Story(ChatInstallationStoryTest.class)
@RunWith(SerenityRunner.class)
public class ChatInstallationStoryTest extends BaseTest {

    @Steps
    ChatSteps chatSteps;

    @Test
    public void sendMessageUsingEnter() throws InterruptedException {
        driver.get(Data.INSTALLED_CHAT_WITH_DOMAIN);
        chatSteps.closeSettingModal();
        chatSteps.sendWithEnter(Data.TEST_MGS1);
        chatSteps.messageTextShouldBeLike(1, Data.TEST_MGS1);
    }

    @Test
    public void uploadFile() {
        driver.get(Data.INSTALLED_CHAT_WITH_DOMAIN);
        chatSteps.closeSettingModal();
        chatSteps.uploadFile(Data.IMAGE_FILE_PATH.getAbsolutePath());
        chatSteps.messageShouldContainFileInfo(1, Data.IMAGE_FILE_PATH.getName() + " (2kb)");

    }
}
