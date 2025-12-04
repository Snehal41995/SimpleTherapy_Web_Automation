package com.SimpleTherapy.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConsentPage extends BaseClass {
    // ======================= Page Elements =======================
    @FindBy(xpath = "//h1[contains(text(),'Your data is secure')]")
    WebElement consentHeading;

    @FindBy(xpath = "//label[@class='inline-flex group cursor-pointer'][1]")
    WebElement firstCheckBox;

    @FindBy(xpath = "//label[.//input[@id='telehealth_consent']]")
    WebElement secondCheckBox;

    @FindBy(xpath = "//input[@id='terms']/ancestor::label[1]/span[1]")
    WebElement thirdCheckBox;

    @FindBy(xpath = "//span[contains(text(), 'Accept All')]")
    WebElement acceptAllBtn;

    // Constructor
    public ConsentPage() {

        PageFactory.initElements(driver, this);
    }

    // ======================= Page Actions =======================
    public String getConsentHeading() {
        return consentHeading.getText();
    }

    public void clickFirstCheckBox() {
        click(firstCheckBox);
    }

    public void clickSecondCheckBox() {
        click(secondCheckBox);
    }

    public void clickThirdCheckBox() {
        click(thirdCheckBox);
    }

    public void clickAcceptAllBtn() {
        click(acceptAllBtn);
    }
}
