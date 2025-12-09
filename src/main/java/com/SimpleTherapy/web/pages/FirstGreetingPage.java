package com.simpleTherapy.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FirstGreetingPage extends BaseClass {
    // ======================= Page Elements =======================
    @FindBy(xpath = "//h1")//check id
    WebElement firstGreetingHeading;

    @FindBy(xpath = "//img[@alt='Step 3']")
    WebElement step3Image;

    @FindBy(xpath = "//span[contains(text(), 'Continue')]")
    WebElement continueBtn;

    // Constructor
    public FirstGreetingPage() {
        PageFactory.initElements(driver, this);
    }

    public String getFirstGreetingHeading() {
        return firstGreetingHeading.getText().trim();
    }

    public boolean isGreetingImageDisplayed() {
        return step3Image.isDisplayed();
    }

    public void clickContinueBtn() {
        continueBtn.click();
    }
}
