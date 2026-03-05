package com.simpleTherapy.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSecurityPage extends BaseClass {
    @FindBy(xpath = "//h1[text()='Keep Your Account Safe']")
    WebElement accountSecurityHeading;

    @FindBy(xpath = "//button[.//span[normalize-space()='Google Authenticator or similar']]//span[last()]\n")
    WebElement googleAuthArrow;

    public AccountSecurityPage() {
        PageFactory.initElements(driver, this);
    }

    public String getAccountSecurityHeading() throws InterruptedException {
        Thread.sleep(2000);
        return accountSecurityHeading.getText();
    }

    public void clickGoogleAuthArrow() {
        googleAuthArrow.click();
    }

}
