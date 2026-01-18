package com.simpleTherapy.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage extends BaseClass {

    public HeaderPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(text(),'Logout')]")
    private WebElement logoutBtn;

    @FindBy(xpath = "//button[contains(text(),'Login')]")
    private WebElement loginBtn;

    public void logout() {
        logoutBtn.click();
    }

    public boolean isLoggedOutSuccessfully() {
        return loginBtn.isDisplayed();
    }
}
