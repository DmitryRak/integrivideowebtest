package com.integrivideo.steps;

import com.integrivideo.pages.Chat;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

/**
 * Created by Dmitry Rak on 4/15/2017.
 */
public class ChatSteps {
    private Chat chat;

    @Step
    public void sendWithButton(String text) throws InterruptedException {
        chat.inputText(text);
        Thread.sleep(1000);
        chat.clickSendButton();
    }

    @Step
    public void sendWithEnter(String text) throws InterruptedException {
        chat.inputText(text);
        Thread.sleep(1000);
        chat.pressEnter();
    }

    @Step
    public void sendSeveralLines(String...lines) throws InterruptedException {
        for(String line:lines){
            chat.inputText(line);
            chat.pressShiftEnter();
        }

        Thread.sleep(1000);
        chat.pressEnter();
    }

    @Step
    public void ownMessageShouldBeVisible(final String text, String dateTime){
        System.out.println("Expected: " + text);
        //Reporter.log(chat.getOwnMessages().stream().filter(mess -> mess.getText().equals(text)).filter(mess -> mess.getDate().equals(dateTime)).findFirst().get().getText(), true);
        assertTrue(chat.getOwnMessages().stream().filter(mess -> mess.getText().equals(text)).filter(mess -> mess.getDate().equals(dateTime)).findFirst().isPresent());
   }
    @Step
    public void ownMessageShouldBeShownAsEdited(final String text, String dateTime){
        System.out.println("Expected: " + text);
        //Reporter.log(chat.getOwnMessages().stream().filter(mess -> mess.getText().equals(text)).filter(mess -> mess.getDate().equals(dateTime)).findFirst().get().getText(), true);
        assertTrue(chat.getOwnMessages().stream().filter(mess -> mess.getText().equals(text)).filter(mess -> mess.getDate().equals(dateTime)).findFirst().get().isEdited());
    }

    @Step
    public void ownMessageShouldBeShownAsRemoved(String id){
        //Reporter.log(chat.getOwnMessages().stream().filter(mess -> mess.getText().equals(text)).filter(mess -> mess.getDate().equals(dateTime)).findFirst().get().getText(), true);
        assertTrue(chat.getOwnMessages().stream().filter(mess -> mess.getId().equals(id)).findFirst().get().isRemoved());
    }

    @Step
    public void editOwnMessage(final String text, String finalText) throws InterruptedException {
        chat.editMessage(text, finalText);
    }

    @Step
    public String removeMessage(final String text) throws InterruptedException {
        String id = chat.getOwnMessages().stream().filter(mess -> mess.getText().equals(text)).findFirst().get().getId();
        chat.removeMessage(text);
        return id;
    }

    public ChatSteps(WebDriver driver){
        chat = new Chat(driver);
    }
    public ChatSteps(){};
}
