package com.simpleTherapy.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.simpleTherapy.web.pages.BaseClass.driver;

public class ThirdGreetingPage {
    // ======================= Page Elements =======================
    @FindBy(xpath = "//h1//span[contains(text(), 'Personal, 1-on-1')]")
    WebElement thirdGreetingHeading;

    @FindBy(xpath = "//img[contains(@alt, 'Personal, 1-on-1')]")
    WebElement heroImage;

    @FindBy(xpath = "//span[contains(text(), 'Continue')]")
    WebElement continueBtn;

    // Constructor
    public ThirdGreetingPage() {
        PageFactory.initElements(driver, this);
    }

    public String getSecondGreetingHeading() {
        return thirdGreetingHeading.getText();
    }

    public boolean isGreetingImageDisplayed() {
        return heroImage.isDisplayed();
    }

    public void clickContinueBtn() {
        continueBtn.click();
    }
}
