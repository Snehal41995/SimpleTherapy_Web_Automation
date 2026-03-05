package com.simpleTherapy.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

//    public void enterPassword(String password) {
//        txtPassword.clear();
//        txtPassword.sendKeys(password);
//    }

    public void enterPassword(String password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement passwordInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@name='password']")
                )
        );

        passwordInput.clear();
        passwordInput.sendKeys(password);
    }


    public void clickContinueBtn() {
        scrollToElement(continueBtn);
        click(continueBtn);
    }

}
