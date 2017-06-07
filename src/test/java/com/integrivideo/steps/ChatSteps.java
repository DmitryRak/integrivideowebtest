package com.integrivideo.steps;

import com.integrivideo.Message;
import com.integrivideo.pages.Chat;
import com.integrivideo.pages.FileUploadModal;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

/**
 * Created by Dmitry Rak on 4/15/2017.
 */
public class ChatSteps {
    Chat chat;
    FileUploadModal fileUpload;

    @Step
    public void sendWithButton(String text) throws InterruptedException {
        chat.inputText(text);
        Thread.sleep(2000);
        chat.clickSendButton();
    }
    @Step
    public void sendWithEnter(String text) throws InterruptedException {
        chat.inputText(text);
        Thread.sleep(2000);
        chat.pressEnter();
    }
    @Step
    public void sendSeveralLines(String...lines) throws InterruptedException {
        for(String line:lines){
            chat.inputText(line);
            chat.pressShiftEnter();
        }
        Thread.sleep(2000);
        chat.pressEnter();
    }
    @Step
    public void ownMessageShouldBeVisible(final String text, String dateTime){
        assertThat("Message should be visible",chat.getOwnMessages().stream().filter(mess -> mess.getText().equals(text)).anyMatch(mess -> mess.getDate().equals(dateTime)));
   }
    @Step
    public void ownMessageShouldBeShownAsEdited(final String text, String dateTime){
        assertThat("Message should be edited",chat.getOwnMessages().stream().filter(mess -> mess.getText().equals(text)).filter(mess -> mess.getDate().equals(dateTime)).findFirst().get().isEdited());
    }
    @Step
    public void ownMessageShouldBeShownAsRemoved(String id){
       assertThat("Message should be removed",chat.getOwnMessages().stream().filter(mess -> mess.getId().equals(id)).findFirst().get().isRemoved());
    }

    /**
     *
     * @param text
     * @param finalText - provide null to edit action -> enter
     * @throws InterruptedException
     */
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
    @Step
    public String uploadFile(String filePath){
        chat.openFileUploadModal();
        fileUpload.addFile(filePath);
        fileUpload.startUpload();
        return chat.getOwnMessages().stream().findFirst().get().getId();
    }
    @Step
    public String getInviteLink(){
        return chat.getInviteLink();
    }
    @Step
    public void inviteLinkShouldBeLike(String pattern){
       assertThat(getInviteLink(),equalToIgnoringCase(pattern));
    }
    @Step
    public void userInfoShouldBeLike(int numberInList, String name, boolean isOnline){
        assertThat(chat.getListOfUsers().get(numberInList).getUserName(),containsString(name));
        assertThat(chat.getListOfUsers().get(numberInList).isOnline(),equalTo(isOnline));
    }
    @Step
    public void ownMessageShouldContainFileInfo(String messageId, String fileName){
        Message message = chat.getOwnMessages().stream().filter(mess -> mess.getId().equals(messageId)).findFirst().get();
        assertThat(message.getFileName(), equalTo(fileName));
    }
}
