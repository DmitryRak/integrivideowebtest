package com.integrivideo.steps;

import com.integrivideo.pages.Chat;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

/**
 * Created by Dmitry Rak on 4/15/2017.
 */
public class ChatSteps {
    Chat chat;

    @Step
    public void sendWithButton(String text) throws InterruptedException {
        chat.inputText(text);
        Thread.sleep(1000);
    }

    @Step
    public void sendWithEnter(String text) throws InterruptedException {
        chat.inputText(text);
        Thread.sleep(1000);
    }

    @Step
    public void sendSeveralLines(String...lines) throws InterruptedException {
        for(String line:lines){
            chat.inputText(line);
        }

        Thread.sleep(1000);
    }
}
