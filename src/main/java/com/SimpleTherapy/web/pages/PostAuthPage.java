package com.simpleTherapy.web.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PostAuthPage extends BaseClass {
    @FindBy(xpath = "//h1[contains(.,'Your account is activated')]")
    WebElement postAuthHeading;

    @FindBy(xpath = "//span[text()='Continue']")
    WebElement continueBtn;

    public PostAuthPage() {
        PageFactory.initElements(driver, this);
    }

    public String getPostAuthHeading() throws InterruptedException {
        Thread.sleep(2000);
        return postAuthHeading.getText();
    }

    public void clickContinue() {
        scrollToElement(continueBtn);
        click(continueBtn);
    }

}
