package com.integrivideo.stories;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Story;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by drak on 8/9/2017.
 */
@Story(ComponentAccessStoryTest.class)
@RunWith(SerenityRunner.class)
public class ComponentAccessStoryTest extends BaseTest {

    @Test
    public void toDOTest() {
        //TODO test for demo without domain specified (1 domain)
        //TODO test for component without domain specified (2 domain)
        //TODO test for component with domain specified (3 domain)
        //TODO test for component with domain specified in another project (4 domain)
    }
}
