package com.simpleTherapy.web.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddressDetailsPage extends BaseClass {
    @FindBy(xpath = "//h1[contains(text(), 'Provide your location')]")
    WebElement addressDetailsHeading;

    @FindBy(name = "address1")
    WebElement txtAddress1;

    @FindBy(xpath = "(//div[contains(@class,'pac-item')])[1]")
    WebElement firstAddressSuggestion;

    @FindBy(xpath = "//span[text()='Continue']")
    WebElement continueBtn;

    public AddressDetailsPage() {
        PageFactory.initElements(driver, this);
    }

    public String getAddressDetailsHeading() throws InterruptedException {
        Thread.sleep(2000);
        return addressDetailsHeading.getText();
    }

    public void enterAddress1AndSelectFirst(String address) throws InterruptedException {
        txtAddress1.clear();
        txtAddress1.sendKeys(address);
        Thread.sleep(2000);

        // select first suggestion
        txtAddress1.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        txtAddress1.sendKeys(Keys.ENTER);
    }

    public void clickContinue() {
        scrollToElement(continueBtn);
        click(continueBtn);
    }

}
