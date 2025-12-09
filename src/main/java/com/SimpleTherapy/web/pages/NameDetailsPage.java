package com.simpleTherapy.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NameDetailsPage extends BaseClass {
    // ======================= Page Elements =======================
    @FindBy(xpath = "//h2[contains(text(), 'What is your legal first and last name?')]")
    WebElement nameDetailsHeading;

    @FindBy(xpath = "//input[@name='firstname']")
    WebElement firstName;

    @FindBy(xpath = "//input[@name='lastname']")
    WebElement lastName;

    @FindBy(xpath = "//input[@name='pref_name']")
    WebElement prefName;

    @FindBy(xpath = "//span[contains(text(),'Continue')]")
    WebElement continueBtn;

    // Constructor
    public NameDetailsPage() {
        PageFactory.initElements(driver, this);
    }

    public String getNameDetailsHeading() {
        return nameDetailsHeading.getText();
    }

    public void enterFirstName(String fName) {
        firstName.clear();
        sendKeys(firstName, fName);
    }

    public void enterLastName(String lName) {
        lastName.clear();
        sendKeys(lastName, lName);
    }

    public void enterPrefName(String pName) {
        prefName.clear();
        sendKeys(prefName, pName);
    }

    public void clickContinueBtn() {
        click(continueBtn);
    }
}
