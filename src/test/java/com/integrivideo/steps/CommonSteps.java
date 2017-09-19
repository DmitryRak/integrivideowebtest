package com.integrivideo.steps;

import com.integrivideo.Data;
import com.integrivideo.Utils;
import com.integrivideo.pages.CommonPage;
import com.integrivideo.popups.NotificationMessagePopup;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.io.File;

import static com.integrivideo.TimeOut.DOUBLE_LONG_TIMEOUT;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created by asus on 7/12/2017.
 */
public class CommonSteps extends ScenarioSteps {

    private CommonPage commonPage;
    private NotificationMessagePopup notificationMessagePopup;

    public void currentPageShouldBe(String url) {
        assertThat(getDriver().getCurrentUrl()).isEqualToIgnoringCase(url);
    }

    public void notificationMessageShouldBeLike(String message) {
        assertThat(notificationMessagePopup.getNotificationText()).contains(message);
    }

    @Step
    public void doLogout() {
        commonPage.clickLogout();
    }

    @Step
    public void openUrl(String url) {
        getDriver().get(url);
    }

    @Step
    public void textFromClipBoardShouldBeLike(String expectedText) {
        assertThat(Utils.getTextFromClipboard()).isEqualTo(expectedText);
    }

    @Step
    public void waitForFileToDownload(String filename) {
        File file = new File(Data.DOWNLOAD_FOLDER.concat(filename));
        int waiting = 0;
        while (!file.exists() && waiting < DOUBLE_LONG_TIMEOUT) {
            waitABit(500);
            waiting += 500;
        }
    }

    @Step
    public static void validateDownloadedFile(String filename) {
        File file = new File(Data.DOWNLOAD_FOLDER);
        File[] files = file.listFiles();

        //Only single document should be downloaded
        assertThat(files.length == 1);

        File downloadedFile = files[0];

        //File should not be empty
        assertThat(downloadedFile.length()>0);

        //File with a correct name is downloaded
        assertThat(downloadedFile.getName()).isEqualTo(filename);
    }
}
