package com.simpleTherapy.web.pages;

import com.simpleTherapy.web.utils.PhoneUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfileDetailsPage extends BaseClass {
    @FindBy(xpath = "//input[@name='firstname']")
    WebElement firstName;

    @FindBy(xpath = "//input[@name='lastname']")
    WebElement lastName;

    @FindBy(xpath = "//input[@name='preferredName']")
    WebElement preferredName;

    @FindBy(xpath = "//input[@type='text' and @disabled and contains(@value,'@')]")
    WebElement email;

    @FindBy(xpath = "//input[@name='phone_number']")
    WebElement phoneInput;

    @FindBy(xpath = "//label[text()='Date of birth']/parent::div//input")
    WebElement dob;

    @FindBy(xpath = "//input[@name = 'address1']")
    WebElement address1;

    @FindBy(xpath = "//label[text()='Country']/parent::div//input")
    WebElement country;

    @FindBy(xpath = "//input[@name = 'city']")
    WebElement city;

    @FindBy(xpath = "//label[text()='State']/parent::div//input")
    WebElement state;

    @FindBy(xpath = "//input[@name = 'zip_code']")
    WebElement zipCode;

    @FindBy(xpath = "//button[.='Save']")
    private WebElement saveBtn;

    public ProfileDetailsPage() {
        PageFactory.initElements(driver, this);
    }

    private String getValue(WebElement element) {
        scrollToElement(element);
        return element.getAttribute("value").trim();
    }

    public String getFirstName() {
        return getValue(firstName);
    }

    public String getLastName() {
        return getValue(lastName);
    }

    public String getPreferredName() {
        return getValue(preferredName);
    }

    public String getEmail() {
        return getValue(email).trim();
    }

    public String getNormalizedPhoneNumber() {
        return PhoneUtil.normalize(phoneInput.getAttribute("value"));
    }

    public String getDob() {
        return getValue(dob);
    }

    public String getAddress1() {
        return getValue(address1);
    }

    public String getCountry() {
        return getValue(country);
    }

    public String getCity() {
        return getValue(city);
    }

    public String getState() {
        return getValue(state);
    }

    public String getZipCode() {
        return getValue(zipCode);
    }

    public void editFirstName(String value) {
        firstName.clear();
        firstName.sendKeys(value);
    }

    public void editLastName(String value) {
        lastName.clear();
        lastName.sendKeys(value);
    }
}
