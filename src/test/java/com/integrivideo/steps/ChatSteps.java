package com.integrivideo.steps;

import com.integrivideo.pages.Chat;
import com.integrivideo.pages.FileUploadModal;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Dmitry Rak on 4/15/2017.
 */
public class ChatSteps {
    private Chat chat;
    private FileUploadModal fileUpload;

    public void sendWithButton(String text) throws InterruptedException {
        chat.inputText(text);
        Thread.sleep(2000);
        chat.clickSendButton();
    }

    public void sendWithEnter(String text) throws InterruptedException {
        chat.inputText(text);
        Thread.sleep(2000);
        chat.pressEnter();
    }

    public void sendSeveralLines(String...lines) throws InterruptedException {
        for(String line:lines){
            chat.inputText(line);
            chat.pressShiftEnter();
        }

        Thread.sleep(2000);
        chat.pressEnter();
    }

    public void ownMessageShouldBeVisible(final String text, String dateTime){
        Reporter.log("Expected: " + text, true);
        //Reporter.log(chat.getOwnMessages().stream().filter(mess -> mess.getText().equals(text)).filter(mess -> mess.getDate().equals(dateTime)).findFirst().get().getText(), true);
        assertTrue(chat.getOwnMessages().stream().filter(mess -> mess.getText().equals(text)).anyMatch(mess -> mess.getDate().equals(dateTime)));
   }
    public void ownMessageShouldBeShownAsEdited(final String text, String dateTime){
        Reporter.log("Expected: " + text, true);
        //Reporter.log(chat.getOwnMessages().stream().filter(mess -> mess.getText().equals(text)).filter(mess -> mess.getDate().equals(dateTime)).findFirst().get().getText(), true);
        assertTrue(chat.getOwnMessages().stream().filter(mess -> mess.getText().equals(text)).filter(mess -> mess.getDate().equals(dateTime)).findFirst().get().isEdited());
    }

    public void ownMessageShouldBeShownAsRemoved(String id){
        //Reporter.log(chat.getOwnMessages().stream().filter(mess -> mess.getText().equals(text)).filter(mess -> mess.getDate().equals(dateTime)).findFirst().get().getText(), true);
        assertTrue(chat.getOwnMessages().stream().filter(mess -> mess.getId().equals(id)).findFirst().get().isRemoved());
    }

    public void editOwnMessage(final String text, String finalText) throws InterruptedException {
        chat.editMessage(text, finalText);
    }

    public String removeMessage(final String text) throws InterruptedException {
        String id = chat.getOwnMessages().stream().filter(mess -> mess.getText().equals(text)).findFirst().get().getId();
        chat.removeMessage(text);
        return id;
    }
    public ChatSteps(WebDriver driver){
        chat = new Chat(driver);
        fileUpload = new FileUploadModal(driver);
    }

    public void uploadFile(String filePath){
        chat.openFileUploadModal();
        fileUpload.addFile(filePath);
        fileUpload.startUpload();
    }
}
