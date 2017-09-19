package com.integrivideo.stories;

import com.integrivideo.Data;
import com.integrivideo.pages.ComponentTypeEnum;
import com.integrivideo.steps.CommonSteps;
import com.integrivideo.steps.ComponentSteps;
import com.integrivideo.steps.ProjectSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by drak on 7/19/2017.
 */
@Story(ManageComponentStoryTest.class)
@RunWith(SerenityRunner.class)
public class ManageComponentStoryTest extends BaseTest {


    @Steps
    ComponentSteps componentSteps;
    @Steps
    ProjectSteps projectSteps;
    @Steps
    CommonSteps commonSteps;


    @Before
    public void openCreateComponentPage(){
        projectSteps.createProject();
        projectSteps.openProjectPage(projectSteps.getProjectCount() - 2);
        componentSteps.openCreateComponentPage();
    }

    @Test
    public void videoChatComponentCanBeCreated() {
        componentSteps.createComponent(ComponentTypeEnum.VIDEO_CHAT, Data.RANDOM_COMPONENT_NAME);
        componentSteps.componentDetailsShouldBeLike(ComponentTypeEnum.VIDEO_CHAT, Data.RANDOM_COMPONENT_NAME);
    }

    @Test
    public void singleVideoComponentCanBeCreated() {
        componentSteps.createComponent(ComponentTypeEnum.SINGLE_VIDEO, Data.RANDOM_COMPONENT_NAME);
        componentSteps.componentDetailsShouldBeLike(ComponentTypeEnum.SINGLE_VIDEO, Data.RANDOM_COMPONENT_NAME);
    }

    @Test
    public void multiPartyVideoComponentCanBeCreated() {
        componentSteps.createComponent(ComponentTypeEnum.MULTIPARTY_VIDEO, Data.RANDOM_COMPONENT_NAME);
        componentSteps.componentDetailsShouldBeLike(ComponentTypeEnum.MULTIPARTY_VIDEO, Data.RANDOM_COMPONENT_NAME);
    }

    @Test
    public void componentCanBeUpdated() {
        componentSteps.createComponent(ComponentTypeEnum.VIDEO_CHAT, Data.RANDOM_COMPONENT_NAME);
        componentSteps.editComponent("edited");
        componentSteps.shouldBeOnComponentsPage();
        componentSteps.openComponentPage(componentSteps.getComponentCount() - 2);
        componentSteps.componentDetailsShouldBeLike(ComponentTypeEnum.VIDEO_CHAT, "edited");
    }

    @Test
    public void componentVideoChatPriceIsCorrect() {
        componentSteps.createComponent(ComponentTypeEnum.VIDEO_CHAT, Data.RANDOM_COMPONENT_NAME);
        componentSteps.returnToProject();
        projectSteps.projectPricesShouldBeLike(15, 0, 15);
    }

    @Test
    public void componentSingleVideoPriceIsCorrect() {
        componentSteps.createComponent(ComponentTypeEnum.SINGLE_VIDEO, Data.RANDOM_COMPONENT_NAME);
        componentSteps.returnToProject();
        projectSteps.projectPricesShouldBeLike(10, 0, 10);
    }

    @Test
    public void componentMultiPartyVideoPriceIsCorrect() {
        componentSteps.createComponent(ComponentTypeEnum.MULTIPARTY_VIDEO, Data.RANDOM_COMPONENT_NAME);
        componentSteps.returnToProject();
        projectSteps.projectPricesShouldBeLike(10, 0, 10);
    }

    @Test
    public void componentMultiDeviceVideoPlayerPriceIsCorrect() {
        componentSteps.createComponent(ComponentTypeEnum.MULTI_DEVICE_VIDEO_PLAYER, Data.RANDOM_COMPONENT_NAME);
        componentSteps.returnToProject();
        projectSteps.projectPricesShouldBeLike(10, 0, 10);
    }

    @Test
    public void componentJsCodeCanBeCopied() {
        componentSteps.createComponent(ComponentTypeEnum.MULTI_DEVICE_VIDEO_PLAYER, Data.RANDOM_COMPONENT_NAME);
        componentSteps.copyJsCode();
        commonSteps.notificationMessageShouldBeLike("Code was copied");
        componentSteps.validateJsCode();
    }
}
