package com.integrivideo.stories;

import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

/**
 * Created by Dmitry Rak on 7/12/2017.
 */
public class BaseTest {

    @Managed(uniqueSession = true)
    WebDriver driver;

}
