package com.SimpleTherapy.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends BaseClass {
    // ======================= Page Elements =======================
    @FindBy(xpath = "//input[@id='react-select-2-input']")
    WebElement employerInput;

    @FindBy(xpath = "//div[contains(@id,'react-select-2-option')]")
    WebElement employerDropdownOption;

    @FindBy(xpath = "//span[contains(text(),'Continue')]")
    WebElement continueBtn;

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

    @FindBy(xpath = "//h2[contains(text(), 'Great news!')]")
    WebElement greatNewsHeading;

    @FindBy(xpath = "//div[contains(@class,'indicatorContainer')]")
    public WebElement hearAboutDropdownArrow;

    // Constructor
    public RegistrationPage() {
        PageFactory.initElements(driver, this);
    }

    // ======================= Page Actions =======================
    public void selectEmployer(String employer) {
        employerInput.sendKeys(employer);
        employerDropdownOption.click();
    }

    public boolean isContinueButtonEnabled() {
        return continueBtn.isEnabled();
    }

    public WebElement getContinueButton() {
        return continueBtn;
    }

    public void clickContinueButton() {
        click(continueBtn);
    }

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

    public void clickAcceptAllbutton() {
        click(acceptAllBtn);
    }

    public void clickHearAboutDropdownArrow() {
        click(hearAboutDropdownArrow);
    }

    public String getGreatNewsHeading() {
        return greatNewsHeading.getText();
    }

}
