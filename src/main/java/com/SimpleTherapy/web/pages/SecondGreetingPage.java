package com.simpleTherapy.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SecondGreetingPage extends BaseClass {
    // ======================= Page Elements =======================
    @FindBy(xpath = "//h1//span[contains(text(),'Care made simple')]]")
    WebElement secondGreetingHeading;

    @FindBy(xpath = "//img")
    List<WebElement> allImages;

    @FindBy(xpath = "//span[contains(text(), 'Continue')]")
    WebElement continueBtn;

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

    public void clickContinueBtn() {
        continueBtn.click();
    }
}
