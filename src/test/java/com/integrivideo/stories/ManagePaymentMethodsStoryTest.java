package com.integrivideo.stories;

import com.integrivideo.Data;
import com.integrivideo.steps.BillingSteps;
import com.integrivideo.steps.CommonSteps;
import com.integrivideo.steps.LoginSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import org.junit.Test;
import org.junit.runner.RunWith;

@Story(ManageProjectStoryTest.class)
@RunWith(SerenityRunner.class)
public class ManagePaymentMethodsStoryTest extends BaseTest {

    @Steps
    LoginSteps loginSteps;

    @Steps
    BillingSteps billingSteps;

    @Steps
    CommonSteps commonSteps;

    @Test
    public void paymentMethodCanBeAdded(){
        loginSteps.enterCredentialsAndLogin(Data.USER_2_EMAIL, Data.USER_2_PASSWORD);
        billingSteps.addNewCard(Data.CARD_NUMBER, Data.EXPIRATION_MONTH, Data.EXPIRATION_YEAR, Data.CARDHOLDER_NAME);
        commonSteps.currentPageShouldBe(Data.BILLING_PAGE);
    }
}
