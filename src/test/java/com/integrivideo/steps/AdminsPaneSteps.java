package com.integrivideo.steps;

import com.integrivideo.Data;
import com.integrivideo.pages.AdminTabEnum;
import com.integrivideo.pages.AdminsPanePage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;

import static org.junit.Assert.assertTrue;

public class AdminsPaneSteps  extends ScenarioSteps {

    private AdminsPanePage adminsPanePage;

    @Steps
    private LoginSteps loginSteps;

    @Steps
    private CommonSteps commonSteps;

    @Step
    public void isOnAdminsPanePage(){
        //TODO Ask Yury to validate redirects for admins
        //loginSteps.opensUrlAndLogin(Data.ADMIN_PROJECTS_PAGE_URL, Data.ADMIN_1_EMAIL, Data.ADMIN_1_PASSWORD);
        loginSteps.enterCredentialsAndLogin(Data.ADMIN_1_EMAIL, Data.ADMIN_1_PASSWORD);
        commonSteps.openUrl(Data.ADMIN_HOME_PAGE);
    }

    @Step
    public void AdminTableShouldBeVisible(AdminTabEnum adminTabEnum){
        adminsPanePage.openTab(adminTabEnum);
        assertTrue(adminsPanePage.isAdminTableVisible(adminTabEnum));
    }

    @Step
    public void goToService(){
        adminsPanePage.clickOnServiceLink();
    }
}
