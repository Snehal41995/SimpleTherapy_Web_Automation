package com.simpleTherapy.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

public class AboutPage extends BaseClass {

    public AboutPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[text()='About']")
    private WebElement aboutTab;

    @FindBy(xpath = "//a[contains(text(),'Privacy Policy')]")
    private WebElement privacyPolicyLink;

    public void openAboutTab() {
        aboutTab.click();
    }

    public void openPrivacyPolicy() {
        privacyPolicyLink.click();
    }

    public boolean isPrivacyPolicyLoaded() {
        Set<String> windows = driver.getWindowHandles();
        for (String win : windows) {
            driver.switchTo().window(win);
            if (driver.getTitle().toLowerCase().contains("privacy")) {
                return true;
            }
        }
        return false;
    }
}
