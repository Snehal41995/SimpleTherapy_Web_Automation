package com.simpleTherapy.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SecondGreetingPage extends BaseClass {
    // ======================= Page Elements =======================
    @FindBy(xpath = "//h1//span[contains(text(),'Care made simple')]")
    WebElement secondGreetingHeading;

    @FindBy(xpath = "//img")
    List<WebElement> allImages;

    @FindBy(xpath = "//button[@type='submit' and .//span[normalize-space()='Continue']]")
    List<WebElement> continueButtons;

    @FindBy(xpath = "//button[@type='submit']//span[normalize-space()='Continue']")
    private WebElement continueBtn;

    // Constructor
    public SecondGreetingPage() {
        PageFactory.initElements(driver, this);
    }

    public String getSecondGreetingHeading() {
        return secondGreetingHeading.getText();
    }

    public List<WebElement> getAllImages() {
        return allImages;
    }

    // ✅ NEW METHOD (Image Load Validation)
    public boolean isImageLoaded(WebElement image) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript(
                "return arguments[0].complete && arguments[0].naturalWidth > 0;",
                image);
    }

    public WebElement getVisibleContinueButton() {
        for (WebElement btn : continueButtons) {
            if (btn.isDisplayed() && btn.isEnabled()) {
                return btn;
            }
        }
        throw new RuntimeException("No visible Continue button found");
    }
}
