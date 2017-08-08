package com.integrivideo.steps;

import com.integrivideo.Data;
import com.integrivideo.pages.BillingPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class BillingSteps extends ScenarioSteps {
    BillingPage billingPage;

    @Step
    public void addNewCard(String number, String month, String year, String cardholder){
        getDriver().get(Data.BILLING_PAGE);
        billingPage.clickAddPayment();
        billingPage.typeCardInfo(number, month,year,cardholder);
    }
}
