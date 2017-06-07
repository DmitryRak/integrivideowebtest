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
public class BasicChatStoryTest{
    @Managed(driver="chrome", uniqueSession = true)
    WebDriver driver;

    @Steps
    ChatSteps chatSteps;

    @Test
    public void sendMessageUsingEnter() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "target\\classes\\chromedriver.exe");
        driver.get("https://www.integrivideo.com/demo/chat/new");
        chatSteps.sendWithEnter(Data.TEST_MGS1);
    }

    @Test
    public void useOfShiftEnter() throws InterruptedException {
        driver.get("https://www.integrivideo.com/demo/chat/new");
        chatSteps.sendSeveralLines(Data.TEST_MGS1, Data.SECOND_LINE);
    }

    @Test
    public void sendMessageUsingButton() throws InterruptedException {
        driver.get("https://www.integrivideo.com/demo/chat/new");
        chatSteps.sendWithButton(Data.TEST_MGS1);
    }

    @Test
    public void sendMessageWithSpecialSymbols() throws InterruptedException {
        driver.get("https://www.integrivideo.com/demo/chat/new");
        chatSteps.sendWithButton(Data.SPECIAL_CHARS);
    }

    @Test
    public void editMessage() throws InterruptedException {
        driver.get("https://www.integrivideo.com/demo/chat/new");
        chatSteps.sendWithEnter(Data.TEST_MGS1);
    }
    @Test
    public void removeMessage() throws InterruptedException {
        driver.get("https://www.integrivideo.com/demo/chat/new");
        chatSteps.sendWithEnter(Data.TEST_MGS1);
    }
}
