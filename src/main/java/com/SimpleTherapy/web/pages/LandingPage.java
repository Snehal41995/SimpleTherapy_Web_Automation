package com.SimpleTherapy.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends BaseClass {
    // ======================= Page Elements =======================
    @FindBy(xpath = "//h1[text()='Welcome to SimpleTherapy']")
    WebElement titleHeading;

    @FindBy(xpath = "//input[@id='react-select-2-input']")
    WebElement employerInput;

    @FindBy(xpath = "//div[contains(@id,'react-select-2-option')]")
    WebElement employerDropdownOption;

    @FindBy(xpath = "//span[contains(text(),'Continue')]")
    WebElement continueBtn;

    @FindBy(xpath = "//a[text()='Click here for help.']")
    WebElement helpLink;

    @FindBy(css = "iframe[title='Intercom live chat']")
    WebElement intercomFrame;

    @FindBy(xpath = "//p[contains(text(), \"We're here to answer your questions\")]")
    WebElement operatorHeading;

    @FindBy(xpath = "//div[@data-testid='close-button']")
    WebElement operatorCloseBtn;

    @FindBy(xpath = "//a[@role='button']")
    WebElement loginHereBtn;

    @FindBy(xpath = "//span[normalize-space()='Need Help?']")
    WebElement needHelpBtn;

    @FindBy(xpath = "//h4[text()='Need Help? Call us at']")
    WebElement popupHeading;

    @FindBy(xpath = "//button[contains(@class,'top-3') and contains(@class,'right-3')]")
    WebElement closePopupBtn;

    @FindBy(xpath = "//span[contains(text(),'Member Login')]")
    WebElement memberLoginLink;

    @FindBy(name = "username")
    WebElement emailInput;

    // Constructor
    public LandingPage() {
        PageFactory.initElements(driver, this);
    }

    // ======================= Page Actions =======================
    public String getTitle() {
        return titleHeading.getText();
    }

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

    public void clickHereForHelp () {
        click(helpLink);
    }

    public String getOperatorHeading() {
        driver.switchTo().frame(intercomFrame);
        String text = operatorHeading.getText();
        driver.switchTo().defaultContent();  
        return text;
    }

    public void closeOperator() {
        driver.switchTo().frame(intercomFrame);
        operatorCloseBtn.click();
        driver.switchTo().defaultContent();
    }

    public void clickLoginHere() {
        click(loginHereBtn);
    }

    public void clickNeedHelp() {
        click(needHelpBtn);
    }

    public String getPopUpHeading() {
        return popupHeading.getText();
    }

    public void closePopup() {
        click(closePopupBtn);
    }
    
    public void clickMemberLogin () {
        click(memberLoginLink);
    }

    public boolean isEmailAddressInputDisplayed() {
        return emailInput.isDisplayed();
    }

    //Language



}
