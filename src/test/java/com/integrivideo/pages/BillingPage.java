package com.integrivideo.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BillingPage extends PageObject {
    public static final By CARD_NUMBER_BY = By.xpath("//input[contains(@name,'number')]");
    public static final By EXPIRATION_MONTH_BY = By.xpath("//input[contains(@name,'expirationMonth')]");
    public static final By EXPIRATION_YEAR_BY = By.xpath("//input[contains(@name,'expirationYear')]");
    public static final By CARDHOLDER_BY = By.xpath("//input[contains(@name,'cardholderName')]");
    public static final By ADD_CARD_BY = By.xpath("//button[contains(text(), 'Add')]");
    public static final By CARD_ROW_BY = By.xpath("//div[contains(@class,'cards')]/div[contains(@class,'row')]");
    public static final By MAKE_DEFAULT_BY = By.xpath("//a[contains(text(), 'Make default')]");
    public static final By REMOVE_BY = By.xpath("//a[contains(text(), 'Remove')]");
    @FindBy(xpath = "//a[contains(text(), 'Add new')]")
    WebElement addPaymentMethod;

    public void typeCardInfo(String number, String month, String year, String cardholder) {
        find(CARD_NUMBER_BY).clear();
        find(CARD_NUMBER_BY).sendKeys(number);
        find(EXPIRATION_MONTH_BY).clear();
        find(EXPIRATION_MONTH_BY).sendKeys(month);
        find(EXPIRATION_YEAR_BY).clear();
        find(EXPIRATION_YEAR_BY).sendKeys(year);
        find(CARDHOLDER_BY).clear();
        find(CARDHOLDER_BY).sendKeys(cardholder);
        clickOn(find(ADD_CARD_BY));
        waitABit(3000);
    }

    public void clickAddPayment() {
        clickOn(addPaymentMethod);
        waitFor(ExpectedConditions.elementToBeClickable(ADD_CARD_BY));
    }

    public int getCardCount() {
        return findAll(CARD_ROW_BY).size();
    }

    public void clickMakeDefault(int cardNumberInTheList) {
        clickOn(findAll(CARD_ROW_BY).get(cardNumberInTheList).find(MAKE_DEFAULT_BY));
    }

    public void clickRemoveCard(int cardNumberInTheList) {
        clickOn(findAll(CARD_ROW_BY).get(cardNumberInTheList).find(REMOVE_BY));
    }
}