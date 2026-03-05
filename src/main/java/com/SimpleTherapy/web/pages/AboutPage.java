package com.simpleTherapy.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AboutPage extends BaseClass {

    // About menu item (from profile dropdown)
    @FindBy(xpath = "//a[@href='/dashboard/profile/about']")
    WebElement aboutMenu;

    // Privacy Policy link inside About page
    @FindBy(xpath = "//a[contains(@href,'privacy-policy')]")
    WebElement privacyPolicyLink;

    // Privacy Policy page header
    @FindBy(xpath = "//h1[contains(text(),'Privacy Policy')]")
    WebElement privacyPolicyHeader;

    public AboutPage() {
        PageFactory.initElements(driver, this);
    }

    // Click About from profile menu
    public void clickAboutMenu() {
        click(aboutMenu);
    }

    // Click Privacy Policy link
    public void clickPrivacyPolicy() {
        click(privacyPolicyLink);
    }

    // Verify Privacy Policy page loaded
    public boolean isPrivacyPolicyPageDisplayed() {
        switchToNewTab();
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains("privacy-policy");
    }

    // Switch to newly opened tab
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
