package com.simpleTherapy.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SecurityPage extends BaseClass {
    @FindBy(xpath = "//a[@href='/dashboard/profile/security?tab=privacyAndSecurity']")
    WebElement securityMenu;

    @FindBy(xpath = "//a[@href='https://www.simpletherapy.com/security-page/']")
    WebElement privacyAndSecurityLink;

    public SecurityPage() {

        PageFactory.initElements(driver, this);
    }

    public void clickSecurityMenu() {

        click(securityMenu);
    }

    public void clickPrivacyAndSecurity() {

        click(privacyAndSecurityLink);
    }

    public boolean isPrivacyAndSecurityPageDisplayed() {
        switchToNewTab();
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains("security-page");
    }

    public void switchToNewTab() {
        String currentWindow = driver.getWindowHandle();
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(currentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }


}