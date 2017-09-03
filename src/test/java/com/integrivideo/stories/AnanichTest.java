package com.integrivideo.stories;

import com.integrivideo.Data;
import com.integrivideo.steps.*;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Dmitry Rak on 7/12/2017.
 */
@Story(AnanichTest.class)
@RunWith(SerenityRunner.class)
public class AnanichTest extends BaseTest {

    @Steps
    ChatSteps chatSteps;

    @Test
    public void sendMessageWithUrl() {
        chatSteps.openDemoChat();
        chatSteps.sendWithEnter(Data.MSG_WITH_URL);
        chatSteps.messageTextShouldBeLike(1, Data.MSG_WITH_URL_FORMATTED);
        chatSteps.clickAttachment(1);
        chatSteps.verifyTabLink(2, Data.URL_BBC_SITE);
    }
}
