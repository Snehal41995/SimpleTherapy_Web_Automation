package com.simpleTherapy.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LandingPage extends BaseClass {

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
    List<WebElement> allLanguages; //Stores multiple language options

    @FindBy(css = "h1")
    WebElement pageHeading;

    @FindBy(xpath = "//span[contains(text(),'Member Login')]")
    WebElement memberLoginLink;

    @FindBy(name = "username")
    WebElement emailInput;

    public LandingPage() {
        PageFactory.initElements(driver, this);
    }

    /* ---------------- TITLE ---------------- */

    public String getTitleHeading() {
        waitForElementVisibility(titleHeading);
        return titleHeading.getText().trim();
    }

    /* ---------------- EMPLOYER ---------------- */

    public void selectEmployer(String employer) {
        waitForElementVisibility(employerInput);
        sendKeys(employerInput, employer);
        waitForClickable(employerDropdownOption);
        click(employerDropdownOption);
    }

    public boolean isContinueBtnEnabled() {
        waitForClickable(continueBtn);
        return continueBtn.isEnabled();
    }

    public void clickContinueBtn() {
        waitForClickable(continueBtn);
        click(continueBtn);
    }

    /* ---------------- INTERCOM ---------------- */

    public void clickHereForHelpLink() {
        waitForClickable(helpLink);
        click(helpLink);
    }

    public String getChatBotHeading() {
        switchToFrame(intercomFrame);
        waitForElementVisibility(chatBotHeading);
        String text = chatBotHeading.getText().trim();
        switchToDefault();
        return text;
    }

    public void closeChatBot() {
        switchToFrame(intercomFrame);
        waitForClickable(chatBotCloseBtn);
        click(chatBotCloseBtn);
        switchToDefault();
    }

    /* ---------------- LOGIN ---------------- */

    public void clickLoginHereLink() {
        waitForClickable(loginHereLink);
        click(loginHereLink);
    }

    public void clickMemberLoginLink() {
        waitForClickable(memberLoginLink);
        click(memberLoginLink);
    }

    public boolean isEmailAddressInputDisplayed() {
        waitForElementVisibility(emailInput);
        return emailInput.isDisplayed();
    }

    /* ---------------- NEED HELP ---------------- */

    public void clickNeedHelpBtn() {
        waitForClickable(needHelpBtn);
        click(needHelpBtn);
    }

    public String getNeedHelpPopUpHeading() {
        waitForElementVisibility(needHelpPopupHeading);
        return needHelpPopupHeading.getText().trim();
    }

    public void closePopup() {
        waitForClickable(needHelpClosePopupBtn);
        click(needHelpClosePopupBtn);
    }

    /* ---------------- LANGUAGE ---------------- */

    public void openLanguageDropdown() {
        waitForClickable(languageDropdown);
        click(languageDropdown);
    }

    public void selectLanguage(String expectedLanguage) {
        openLanguageDropdown();
        for (WebElement lang : allLanguages) {
            if (lang.getText().trim().equalsIgnoreCase(expectedLanguage)) {
                waitForClickable(lang);
                lang.click();
                break;
            }
        }
    }

    public void selectLanguageByCode(String code) {
        openLanguageDropdown();
        String xpath = String.format("//li[.//img[@alt='%s']]", code);
        WebElement langOption = driver.findElement(By.xpath(xpath));
        waitForClickable(langOption);
        click(langOption);
    }

    public String getHeading() {
        waitForElementVisibility(pageHeading);
        return pageHeading.getText().trim();
    }
}
