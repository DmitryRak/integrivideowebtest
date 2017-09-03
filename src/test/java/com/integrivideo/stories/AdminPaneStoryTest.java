package com.integrivideo.stories;

import com.integrivideo.Data;
import com.integrivideo.pages.AdminTabEnum;
import com.integrivideo.steps.AdminsPaneSteps;
import com.integrivideo.steps.CommonSteps;
import com.integrivideo.steps.ProjectSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import org.junit.Test;
import org.junit.runner.RunWith;

@Story(AdminPaneStoryTest.class)
@RunWith(SerenityRunner.class)
public class AdminPaneStoryTest extends BaseTest {

    @Steps
    ProjectSteps projectSteps;

    @Steps
    CommonSteps commonSteps;

    @Steps
    AdminsPaneSteps adminsPaneSteps;

    @Test
    public void adminsPaneIsNotAccessibleForNonAdmins(){
        projectSteps.isOnProjectListPage();
        commonSteps.openUrl(Data.ADMIN_HOME_PAGE);
        commonSteps.currentPageShouldBe(Data.PROJECTS_LIST_URL);
    }

    @Test
    public void adminsPaneContainsUsersPage(){
        adminsPaneSteps.isOnAdminsPanePage();
        adminsPaneSteps.AdminTableShouldBeVisible(AdminTabEnum.USERS);
    }

    @Test
    public void adminsPaneContainsProjectsPage(){
        adminsPaneSteps.isOnAdminsPanePage();
        adminsPaneSteps.AdminTableShouldBeVisible(AdminTabEnum.PROJECTS);
    }

    @Test
    public void adminsPaneContainsComponentsPage(){
        adminsPaneSteps.isOnAdminsPanePage();
        adminsPaneSteps.AdminTableShouldBeVisible(AdminTabEnum.COMPONENTS);
    }

    @Test
    public void adminsPaneContainsSessionsPage(){
        adminsPaneSteps.isOnAdminsPanePage();
        adminsPaneSteps.AdminTableShouldBeVisible(AdminTabEnum.SESSIONS);
    }

    @Test
    public void adminsPaneContainsLogsPage(){
        adminsPaneSteps.isOnAdminsPanePage();
        adminsPaneSteps.AdminTableShouldBeVisible(AdminTabEnum.LOGS);
    }

    @Test
    public void adminCanOpenServicePage(){
        adminsPaneSteps.isOnAdminsPanePage();
        adminsPaneSteps.goToService();
        commonSteps.currentPageShouldBe(Data.PROJECTS_LIST_URL);
    }
}
