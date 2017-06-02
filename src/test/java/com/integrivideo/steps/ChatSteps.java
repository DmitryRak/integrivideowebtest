package com.integrivideo.steps;

import com.integrivideo.pages.Chat;
import com.integrivideo.pages.FileUploadModal;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

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
        assertThat("Message should be visible",chat.getOwnMessages().stream().filter(mess -> mess.getText().equals(text)).anyMatch(mess -> mess.getDate().equals(dateTime)));
   }
    public void ownMessageShouldBeShownAsEdited(final String text, String dateTime){
        Reporter.log("Expected: " + text, true);
        //Reporter.log(chat.getOwnMessages().stream().filter(mess -> mess.getText().equals(text)).filter(mess -> mess.getDate().equals(dateTime)).findFirst().get().getText(), true);
        assertThat("Message should be edited",chat.getOwnMessages().stream().filter(mess -> mess.getText().equals(text)).filter(mess -> mess.getDate().equals(dateTime)).findFirst().get().isEdited());
    }

    public void ownMessageShouldBeShownAsRemoved(String id){
        //Reporter.log(chat.getOwnMessages().stream().filter(mess -> mess.getText().equals(text)).filter(mess -> mess.getDate().equals(dateTime)).findFirst().get().getText(), true);
        assertThat("Message should be removed",chat.getOwnMessages().stream().filter(mess -> mess.getId().equals(id)).findFirst().get().isRemoved());
    }

    /**
     *
     * @param text
     * @param finalText - provide null to edit action -> enter
     * @throws InterruptedException
     */
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
    public String getInviteLink(){
        return chat.getInviteLink();
    }
    public void inviteLinkShouldBeLike(String pattern){
        Reporter.log("Expected: " + pattern, true);
        Reporter.log("Actual: " + getInviteLink(), true);
        assertThat(getInviteLink(),equalToIgnoringCase(pattern));

    }
}
