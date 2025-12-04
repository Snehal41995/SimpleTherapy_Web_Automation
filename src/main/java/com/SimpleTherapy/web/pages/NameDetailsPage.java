package com.SimpleTherapy.web.pages;

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
        firstName.sendKeys(fName);
    }

    public void enterLastName(String lName) {
        lastName.clear();
        lastName.sendKeys(lName);
    }

    public void enterPrefName(String pName) {
        prefName.clear();
        prefName.sendKeys(pName);
    }

    public void clickContinueBtn() {
        click(continueBtn);
    }

}
