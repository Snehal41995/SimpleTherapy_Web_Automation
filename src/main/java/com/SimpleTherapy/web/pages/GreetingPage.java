package com.SimpleTherapy.web.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GreetingPage extends BaseClass {
    // ======================= Page Elements =======================
    @FindBy(xpath = "//h1")
    WebElement greetingHeading;

    @FindBy(xpath = "//img[@alt='Step 3']")
    WebElement step3Image;

    @FindBy(xpath = "//span[contains(text(), 'Continue')]")
    WebElement continueBtn;

    // Constructor
    public GreetingPage() {
        PageFactory.initElements(driver, this);
    }

    public String getGreetingHeading() {
        return greetingHeading.getText().trim();
    }

    // Check if image is displayed AND loaded
    public boolean isGreetingImageDisplayed() {

        if (!step3Image.isDisplayed()) {
            return false;
        }

        // JS to check image is actually loaded
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Long naturalWidth = (Long) js.executeScript(
                "return arguments[0].naturalWidth;", step3Image);

        return naturalWidth != null && naturalWidth > 0;
    }

    public void clickContinueBtn() {
        continueBtn.click();
    }
}
