package com.integrivideo.steps;

import com.integrivideo.Data;
import com.integrivideo.Message;
import com.integrivideo.pages.ChatPage;
import com.integrivideo.pages.FileUploadModal;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

/**
 * Created by Dmitry Rak on 4/15/2017.
 */
public class ChatSteps extends ScenarioSteps {
    ChatPage chatPage;
    FileUploadModal fileUpload;

    @Step
    public void sendWithButton(String text) throws InterruptedException {
        chatPage.inputText(text);
        Thread.sleep(2000);
        chatPage.clickSendButton();
    }
    @Step
    public void sendWithEnter(String text) throws InterruptedException {
        chatPage.inputText(text);
        Thread.sleep(2000);
        chatPage.pressEnter();
    }
    @Step
    public void sendSeveralLines(String...lines) throws InterruptedException {
        for(String line:lines){
            chatPage.inputText(line);
            chatPage.pressShiftEnter();
        }
        Thread.sleep(2000);
        chatPage.pressEnter();
    }

    @Step
    public void messageShouldBeShownAsEdited(final int messageNumber){
        assertThat("Message should be edited",chatPage.getOwnMessages().get(messageNumber-1).isEdited());
    }
    @Step
    public void messageShouldBeShownAsRemoved(final int messageNumber){
       assertThat("Message should be removed",chatPage.getOwnMessages().get(messageNumber-1).isRemoved());
    }

    /**
     *
     * @param messageNumber
     * @param finalText - provide null to edit action -> enter
     * @throws InterruptedException
     */
    @Step
    public void editMessage(final int messageNumber, String finalText) throws InterruptedException {
        chatPage.editMessage(messageNumber, finalText);
    }
    @Step
    public void removeMessage(final int messageNumber) throws InterruptedException {
        //String id = chatPage.getOwnMessages().stream().filter(mess -> mess.getText().equals(text)).findFirst().get().getId();
        chatPage.removeMessage(messageNumber);
    }
    @Step
    public void uploadFile(String filePath){
        chatPage.openFileUploadModal();
        fileUpload.addFile(filePath);
        fileUpload.startUpload();
    }
    @Step
    public String getInviteLink(){
        return chatPage.getInviteLink();
    }
    @Step
    public void inviteLinkShouldBeLike(String pattern){
       assertThat(getInviteLink(),equalToIgnoringCase(pattern));
    }
    @Step
    public void userInfoShouldBeLike(int numberInList, String name, boolean isOnline){
        assertThat(chatPage.getListOfUsers().get(numberInList).getUserName(),containsString(name));
        assertThat(chatPage.getListOfUsers().get(numberInList).isOnline(),equalTo(isOnline));
    }
    @Step
    public void messageShouldContainFileInfo(final int messageNumber, String fileName){
        Message message = chatPage.getOwnMessages().get(messageNumber-1);
        assertThat(message.getFileName(), equalTo(fileName));
    }
    @Step
    public void messageTextShouldBeLike(final int messageNumber, String text){
        assertThat(chatPage.getMessageText(messageNumber-1), equalTo(text));
    }

    @Step
    public void openDemoChatAsAnonym(){
        getDriver().get(Data.TEST_CHAT_URL);
        //chatPage.closeSettingsWindow();
    }
}
