package com.simpleTherapy.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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
    WebElement chatBotHeading;

    @FindBy(xpath = "//div[@data-testid='close-button']")
    WebElement chatBotCloseBtn;

    @FindBy(xpath = "//a[@role='button']")
    WebElement loginHereLink;

    @FindBy(xpath = "//span[normalize-space()='Need Help?']")
    WebElement needHelpBtn;

    @FindBy(xpath = "//h4[text()='Need Help? Call us at']")
    WebElement needHelpPopupHeading;

    @FindBy(xpath = "//button[contains(@class,'top-3') and contains(@class,'right-3')]")
    WebElement needHelpClosePopupBtn;

    @FindBy(xpath = "//*[@id='langSelector']")
    WebElement languageDropdown;

    @FindBy(css = "ul li")
    List<WebElement> allLanguages;

    @FindBy(css = "h1")
    WebElement pageHeading;

    @FindBy(xpath = "//span[contains(text(),'Member Login')]")
    WebElement memberLoginLink;

    @FindBy(name = "username")
    WebElement emailInput;

    // Constructor
    public LandingPage() {
        PageFactory.initElements(driver, this);
    }

    // ======================= Page Actions =======================
    public String getTitleHeading() {
        return titleHeading.getText();
    }

    public void selectEmployer(String employer) {
        sendKeys(employerInput, employer);
        click(employerDropdownOption);
    }

    public boolean isContinueBtnEnabled() {
        return continueBtn.isEnabled();
    }

    public void clickContinueBtn() {
        click(continueBtn);
    }

    public void clickHereForHelpLink() {
        click(helpLink);
    }

    public String getChatBotHeading() {
        switchToFrame(intercomFrame);
        String text = chatBotHeading.getText();
        switchToDefault();
        return text;
    }

    public void closeChatBot() {
        switchToFrame(intercomFrame);
        click(chatBotCloseBtn);
        switchToDefault();
    }

    public void clickLoginHereLink() {
        click(loginHereLink);
    }

    public void clickNeedHelpBtn() {
        click(needHelpBtn);
    }

    public String getNeedHelpPopUpHeading() {
        return needHelpPopupHeading.getText();
    }

    public void closePopup() {
        click(needHelpClosePopupBtn);
    }

    //language
    public void openLanguageDropdown(){
        click(languageDropdown);
    }

    public void selectLanguage(String expectedLanguage){
        for(WebElement lang : allLanguages){
            if(lang.getText().trim().equalsIgnoreCase(expectedLanguage)){
                lang.click();
                break;
            }
        }
    }

    public String getHeading(){
        return pageHeading.getText().trim();
    }

    public void selectLanguageByCode(String code) throws InterruptedException {
        languageDropdown.click();
        Thread.sleep(1000);

        String xpath = String.format("//li[.//img[@alt='%s']]", code);
        driver.findElement(By.xpath(xpath)).click();

        Thread.sleep(1500);  // allow heading to update
    }

    public void clickMemberLoginLink() {
        click(memberLoginLink);
    }

    public boolean isEmailAddressInputDisplayed() {
        return emailInput.isDisplayed();
    }
}
