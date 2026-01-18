package com.simpleTherapy.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPasswordPage extends BaseClass {
    @FindBy(xpath = "//h1[text()='Create Your Account']")
    WebElement signUpPasswordHeading;

    @FindBy(xpath = "//input[@name='password']")
    WebElement txtPassword;

    @FindBy(xpath = "//button[@type='submit' and normalize-space()='Continue']")
    WebElement continueBtn;

    public SignupPasswordPage() {
        PageFactory.initElements(driver, this);
    }

    public String getSignUpPasswordHeading() {
        return signUpPasswordHeading.getText();
    }

    public void enterPassword(String password) {
        txtPassword.clear();
        txtPassword.sendKeys(password);
    }

    public void clickContinueBtn() {
        scrollToElement(continueBtn);
        click(continueBtn);
    }

}
