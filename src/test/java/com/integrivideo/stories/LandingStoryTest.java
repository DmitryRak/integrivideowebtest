package com.integrivideo.stories;

import com.integrivideo.steps.LandingPageSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Pavel Ananich on 11/09/2017.
 */

@Story(LandingStoryTest.class)
@RunWith(SerenityRunner.class)
public class LandingStoryTest extends BaseTest {

    @Steps
    LandingPageSteps landingPageSteps;

    @Test
    public void userCanDownloadWhitePaper() {
        landingPageSteps.downloadWhitePaper();
    }
}
