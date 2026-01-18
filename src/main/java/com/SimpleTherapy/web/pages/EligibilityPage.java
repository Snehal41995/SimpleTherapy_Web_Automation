package com.simpleTherapy.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EligibilityPage extends BaseClass {
    @FindBy(xpath = "//h1[contains(text(), 'Activation Code')]")
    WebElement activationCodeHeading;

    @FindBy(name = "referral_code")
    WebElement txtActivationCode;

    @FindBy(xpath = "//span[text()='Continue']")
    WebElement continueBtn;

    public EligibilityPage() {
        PageFactory.initElements(driver, this);
    }

    public String getActivationCodeHeading() throws InterruptedException {
        Thread.sleep(2000);
        return activationCodeHeading.getText();
    }

    public void enterActivationCode(String activationCode) {
        txtActivationCode.clear();
        txtActivationCode.sendKeys(activationCode);
    }

    public void clickContinueBtn() {
        scrollToElement(continueBtn);
        click(continueBtn);
    }

}
