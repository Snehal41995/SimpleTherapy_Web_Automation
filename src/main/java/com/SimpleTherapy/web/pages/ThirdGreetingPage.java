package com.simpleTherapy.web.pages;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.simpleTherapy.web.pages.BaseClass.driver;

public class ThirdGreetingPage extends BaseClass {
    @FindBy(xpath = "//h1//span[contains(text(), 'Personal, 1-on-1')]")
    WebElement thirdGreetingHeading;

    @FindBy(xpath = "//img[contains(@alt, 'Personal, 1-on-1')]")
    WebElement heroImage;

    @FindBy(xpath = "(//*[text()='Continue' or normalize-space()='Continue'])[last()]")
    WebElement continueBtn;

    public ThirdGreetingPage() {
        PageFactory.initElements(driver, this);
    }

    public String getThirdGreetingHeading() {
        return thirdGreetingHeading.getText();
    }

    public boolean isGreetingImageDisplayed() {
        return heroImage.isDisplayed();
    }

    public WebElement getContinueBtn() {
        return continueBtn;
    }

//    public void clickContinueBtn() throws InterruptedException {
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", continueBtn);
//        Thread.sleep(500);
//        continueBtn.click();
//    }

    public void clickContinueBtn() {
        click(continueBtn);
    }



}
