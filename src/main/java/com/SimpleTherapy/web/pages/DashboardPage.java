package com.simpleTherapy.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BaseClass {
    @FindBy(xpath = "//div[@data-role='Sara Card']//h2")
    WebElement saraCardGreeting;

    @FindBy(xpath = "//button[contains(@id,'headlessui-menu-button')]")
    WebElement profileIcon;

    @FindBy(xpath = "//a[contains(@href,'/dashboard/profile/details')]")
    WebElement profileDetailsOption;

    public DashboardPage() {
        PageFactory.initElements(driver, this);
    }

    // ✅ Dashboard loaded check
    public boolean isDashboardDisplayed() {
        return saraCardGreeting.isDisplayed();
    }

    public boolean isProfileIconDisplayed() {
        return profileIcon.isDisplayed();
    }

    public String getSaraCardGreetingText() {
        return saraCardGreeting.getText().trim();
    }

    public boolean isUserNameCorrectOnSaraCard(String expectedName) throws InterruptedException {
        Thread.sleep(2000);
        String actualText = getSaraCardGreetingText();
        return actualText.contains(expectedName);
    }

    public void clickProfileIcon() {
        click(profileIcon);
    }

    public void clickProfileDetailsOption() {
        click(profileDetailsOption);
    }
}