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

import static org.assertj.core.api.Assertions.assertThat;

@Story(ManagePaymentMethodsStoryTest.class)
@RunWith(SerenityRunner.class)
public class ManagePaymentMethodsStoryTest extends BaseTest {

    @Steps
    BillingSteps billingSteps;
    @Steps
    CommonSteps commonSteps;
    @Steps
    LoginSteps loginSteps;

    @Test
    public void paymentMethodCanBeAdded() {
        billingSteps.isOnBillingPage();
        int cardCount = billingSteps.getCardCount();
        billingSteps.addNewCard(Data.CARD_NUMBER, Data.EXPIRATION_MONTH, Data.EXPIRATION_YEAR, Data.CARDHOLDER_NAME);
        commonSteps.currentPageShouldBe(Data.BILLING_PAGE);
        assertThat(billingSteps.getCardCount()).isEqualTo(cardCount + 1);
    }

    //TODO at least 2 payment methods should exist
    @Test
    public void defaultPaymentMethodCanBeChanged() {
        billingSteps.isOnBillingPage();
        billingSteps.addNewCard(Data.CARD_NUMBER, Data.EXPIRATION_MONTH, Data.EXPIRATION_YEAR, Data.CARDHOLDER_NAME);
        billingSteps.makeDefaultCard(billingSteps.getCardCount() - 1);
        commonSteps.notificationMessageShouldBeLike("Default payment method successfully changed");
    }

    @Test
    public void removePaymentMethod() {
        billingSteps.isOnBillingPage();
        billingSteps.addNewCard(Data.CARD_NUMBER, Data.EXPIRATION_MONTH, Data.EXPIRATION_YEAR, Data.CARDHOLDER_NAME);
        billingSteps.removeCard(billingSteps.getCardCount() - 1);
        commonSteps.notificationMessageShouldBeLike("Payment method successfully removed");
    }
}
