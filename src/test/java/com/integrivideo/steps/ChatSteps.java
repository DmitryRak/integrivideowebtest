package com.integrivideo.steps;

import com.integrivideo.Data;
import com.integrivideo.Message;
import com.integrivideo.User;
import com.integrivideo.modals.FileUploadModal;
import com.integrivideo.modals.UserSettings;
import com.integrivideo.modals.UserSettingsModal;
import com.integrivideo.pages.ChatPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created by Dmitry Rak on 4/15/2017.
 */
public class ChatSteps extends ScenarioSteps {

    private ChatPage chatPage;
    private FileUploadModal fileUpload;
    private UserSettingsModal userSettingsModal;

    @Steps
    private CommonSteps commonSteps;

    @Step
    public void sendWithButton(String text) {
        chatPage.inputText(text);
        waitABit(1000);
        chatPage.clickSendButton();
    }

    @Step
    public void sendWithEnter(String text) {
        chatPage.inputText(text);
        waitABit(1000);
        chatPage.pressEnter();
    }

    @Step
    public void sendSeveralLines(String... lines) {
        for (String line : lines) {
            chatPage.inputText(line);
            chatPage.pressShiftEnter();
        }
        waitABit(1000);
        chatPage.pressEnter();
    }

    @Step
    public void messageShouldBeShownAsEdited(final int messageNumber) {
        assertThat(chatPage.getOwnMessages().get(messageNumber - 1).isEdited()).
                as("Message should be shows as edited");
    }

    @Step
    public void messageShouldBeShownAsRemoved(final int messageNumber) {
        assertThat(chatPage.getOwnMessages().get(messageNumber - 1).isRemoved()).
                as("Message should be shows as removed");
    }

    /**
     * @param messageNumber
     * @param finalText     - provide null to edit action -> enter
     */
    @Step
    public void editMessage(final int messageNumber, String finalText) {
        chatPage.editMessage(messageNumber, finalText);
    }

    @Step
    public void removeMessage(final int messageNumber) {
        chatPage.removeMessage(messageNumber);
    }

    @Step
    public void uploadFile(String filePath) {
        chatPage.openFileUploadModal();
        fileUpload.addFile(filePath);
        fileUpload.startUpload();
    }

    @Step
    public String getInviteLink() {
        return chatPage.getInviteLink();
    }

    @Step
    public void inviteLinkShouldBeLike(String pattern) {
        assertThat(getInviteLink()).isEqualToIgnoringCase(pattern);
    }

    @Step
    public void userInfoShouldBeLike(int numberInList, String name, boolean isOnline) {
        User user = chatPage.getListOfUsers().get(numberInList);
        assertThat(user.getUserName()).contains(name);
        assertThat(user.isOnline()).isEqualTo(isOnline);
    }

    @Step
    public void messageShouldContainFileInfo(final int messageNumber, String fileName) {
        Message message = chatPage.getOwnMessages().get(messageNumber - 1);
        assertThat(message.getFileName()).isEqualTo(fileName);
    }

    @Step
    public void messageTextShouldBeLike(final int messageNumber, String text) {
        assertThat(chatPage.getMessageText(messageNumber - 1)).isEqualTo(text);
    }

    @Step
    public void openDemoChat() {
        getDriver().get(Data.TEST_CHAT_URL);
    }

    @Step
    public void openSettingsModal() {
        chatPage.openUserSettingsModal();
    }

    @Step
    public void closeSettingModal() {
        userSettingsModal.closeSettingsWindow();
    }

    /**
     * @param name
     * @param email
     * @param imageUrl
     */
    @Step
    public void validateUserSettings(String name, String email, String imageUrl) {
        UserSettings userSettings = userSettingsModal.getUserSettings();
        assertThat(userSettings.getName()).isEqualTo(name);
        assertThat(userSettings.getEmail()).isEqualTo(email);
        assertThat(userSettings.getUserPicUrl()).isEqualTo(imageUrl);
    }

    /**
     * @param name
     * @param email
     * @param imageUrl
     */
    @Step
    public void updateUserSettings(String name, String email, String imageUrl) {
        userSettingsModal.insertSettings(name, email, imageUrl);
        userSettingsModal.saveSettings();
    }

    @Step
    public void isThisIsTrialVersionModalShown() {
        assertThat(chatPage.isThisIsTrialVersionModalShown());
    }

    @Step
    public void clickAttachment(final int messageNumber) {
        chatPage.getAttachment(messageNumber).click();
    }

    @Step
    public void verifyTabLink(final int tabNumber, final String url) {
        getDriver().switchTo().window(getDriver().getWindowHandles().toArray()[tabNumber - 1].toString());
        commonSteps.currentPageShouldBe(url);
    }

    @Step
    public void clickInviteUsers() {
        chatPage.inviteUsers();
        commonSteps.notificationMessageShouldBeLike("Link was copied");
    }

    @Step
    public void validateInviteLink() {
        String currentUrl = getDriver().getCurrentUrl();
        commonSteps.textFromClipBoardShouldBeLike(currentUrl);
    }
}
