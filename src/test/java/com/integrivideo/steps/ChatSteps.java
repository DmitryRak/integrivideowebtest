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
    public void messageShouldBeShownAsEdited(final int messageNumber){
        assertThat("Message should be edited",chat.getOwnMessages().get(messageNumber-1).isEdited());
    }
    @Step
    public void messageShouldBeShownAsRemoved(final int messageNumber){
       assertThat("Message should be removed",chat.getOwnMessages().get(messageNumber-1).isRemoved());
    }

    /**
     *
     * @param messageNumber
     * @param finalText - provide null to edit action -> enter
     * @throws InterruptedException
     */
    @Step
    public void editMessage(final int messageNumber, String finalText) throws InterruptedException {
        chat.editMessage(messageNumber, finalText);
    }
    @Step
    public void removeMessage(final int messageNumber) throws InterruptedException {
        //String id = chat.getOwnMessages().stream().filter(mess -> mess.getText().equals(text)).findFirst().get().getId();
        chat.removeMessage(messageNumber);
    }
    @Step
    public void uploadFile(String filePath){
        chat.openFileUploadModal();
        fileUpload.addFile(filePath);
        fileUpload.startUpload();
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
    public void messageShouldContainFileInfo(final int messageNumber, String fileName){
        Message message = chat.getOwnMessages().get(messageNumber-1);
        assertThat(message.getFileName(), equalTo(fileName));
    }
    @Step
    public void messageTextShouldBeLike(final int messageNumber, String text){
        assertThat(chat.getMessageText(messageNumber-1), equalTo(text));
    }
}
