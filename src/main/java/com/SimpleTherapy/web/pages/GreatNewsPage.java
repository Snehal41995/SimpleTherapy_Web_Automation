package com.SimpleTherapy.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GreatNewsPage extends BaseClass {
    // ======================= Page Elements =======================
    @FindBy(xpath = "//h2[contains(text(), 'Great news!')]")
    WebElement greatNewsHeading;

    @FindBy(xpath = "//div[contains(@class,'indicatorContainer')]")
    WebElement hearAboutUsDropdown;

    @FindBy(xpath = "//div[@role='option' and contains(., 'Demo')]")
    WebElement demoOption;

    @FindBy(xpath = "//span[contains(text(),'Continue')]")
    WebElement continueBtn;

    // Constructor
    public GreatNewsPage() {

        PageFactory.initElements(driver, this);
    }

    public String getGreatNewsHeading() {

        return greatNewsHeading.getText();
    }

    public void openHearAboutUsDropdown() {
        click(hearAboutUsDropdown);
    }

    public void selectDemoOption() {
        click(demoOption);
    }

    public void clickContinueBtn() {
        click(continueBtn);
    }
}
