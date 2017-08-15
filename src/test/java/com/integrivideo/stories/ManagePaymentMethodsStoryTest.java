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

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.junit.Assert.assertEquals;

@Story(ManagePaymentMethodsStoryTest.class)
@RunWith(SerenityRunner.class)
public class ManagePaymentMethodsStoryTest extends BaseTest {

    @Steps
    LoginSteps loginSteps;

    @Steps
    BillingSteps billingSteps;

    @Steps
    CommonSteps commonSteps;

    @Test
    public void paymentMethodCanBeAdded() {
        loginSteps.enterCredentialsAndLogin(Data.USER_2_EMAIL, Data.USER_2_PASSWORD);

        int cardCount = billingSteps.getCardCount();
        billingSteps.addNewCard(Data.CARD_NUMBER, Data.EXPIRATION_MONTH, Data.EXPIRATION_YEAR, Data.CARDHOLDER_NAME);
        commonSteps.currentPageShouldBe(Data.BILLING_PAGE);
        assertEquals(cardCount + 1, billingSteps.getCardCount());
    }

    @Test
    public void defaultPaymentMethodCanBeChanged() {
        loginSteps.enterCredentialsAndLogin(Data.USER_2_EMAIL, Data.USER_2_PASSWORD);
        getDriver().get(Data.BILLING_PAGE);
        billingSteps.addNewCard(Data.CARD_NUMBER, Data.EXPIRATION_MONTH, Data.EXPIRATION_YEAR, Data.CARDHOLDER_NAME);
        billingSteps.makeDefaultCard(billingSteps.getCardCount() - 1);
        commonSteps.notificationMessageShouldBeLike("Default payment method successfully changed");
    }

    @Test
    public void removePaymentMethod() {
        loginSteps.enterCredentialsAndLogin(Data.USER_2_EMAIL, Data.USER_2_PASSWORD);
        getDriver().get(Data.BILLING_PAGE);
        billingSteps.addNewCard(Data.CARD_NUMBER, Data.EXPIRATION_MONTH, Data.EXPIRATION_YEAR, Data.CARDHOLDER_NAME);
        billingSteps.removeCard(billingSteps.getCardCount() - 1);
        commonSteps.notificationMessageShouldBeLike("Payment method successfully removed");
    }
}