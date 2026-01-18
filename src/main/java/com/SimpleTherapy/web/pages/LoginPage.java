package com.simpleTherapy.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BaseClass {

    @FindBy(xpath = "//input[@name='username']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@name='code']")
    private WebElement otpInput;

    @FindBy(xpath = "//button[@type='submit' and normalize-space()='Continue']")
    private WebElement continueBtn;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean isLoginPageDisplayed() {
        return emailInput.isDisplayed();
    }

    public void enterEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void clickContinue() {
        continueBtn.click();
    }

    public void enterOtp(String otp) {
        otpInput.clear();
        otpInput.sendKeys(otp);
    }
}
