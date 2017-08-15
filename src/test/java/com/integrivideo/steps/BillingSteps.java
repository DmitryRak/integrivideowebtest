package com.integrivideo.steps;

import com.integrivideo.Data;
import com.integrivideo.pages.BillingPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;

public class BillingSteps extends ScenarioSteps {

    private BillingPage billingPage;

    @Steps
    private LoginSteps loginSteps;

    @Step
    public void isOnBillingPage() {
        loginSteps.opensUrlAndLogin(Data.BILLING_PAGE, Data.USER_2_EMAIL, Data.USER_2_PASSWORD);
    }

    @Step
    public void addNewCard(String number, String month, String year, String cardholder) {
        getDriver().get(Data.BILLING_PAGE);
        billingPage.clickAddPayment();
        billingPage.typeCardInfo(number, month, year, cardholder);
    }

    @Step
    public int getCardCount() {
        return billingPage.getCardCount();
    }

    @Step
    public void makeDefaultCard(int cardNumberInTheList) {
        billingPage.clickMakeDefault(cardNumberInTheList);
    }

    @Step
    public void removeCard(int cardNumberInTheList) {
        billingPage.clickRemoveCard(cardNumberInTheList);
    }
}
