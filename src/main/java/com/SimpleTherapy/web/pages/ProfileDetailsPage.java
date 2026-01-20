package com.simpleTherapy.web.pages;

import com.simpleTherapy.web.utils.DateUtil;
import com.simpleTherapy.web.utils.PhoneUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    @FindBy(xpath = "//input[@name = 'address1']")
    WebElement address1;

    @FindBy(xpath = "//div[normalize-space()='United States']")
    WebElement country;

    @FindBy(xpath = "//input[@name = 'city']")
    WebElement city;

    @FindBy(xpath = "//div[contains(@class,'singleValue') and normalize-space()='California']")
    WebElement state;

    @FindBy(xpath = "//input[@name='height']/preceding-sibling::div[contains(@class,'control')]")
    private WebElement heightDropdown;

    @FindBy(xpath = "//input[@name='weight']/preceding-sibling::div[contains(@class,'control')]")
    private WebElement weightDropdown;

    @FindBy(xpath = "//input[@placeholder='Minor date of birth']")
    WebElement dob;

    @FindBy(xpath = "//button[.//span[text()='Save Changes']]")
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
        return getValue(email);
    }

    public String getNormalizedPhoneNumber() {
        return PhoneUtil.normalize(phoneInput.getAttribute("value"));
    }

    public String getAddress1() {
        return getValue(address1);
    }

    public String getCountry() {
        scrollToElement(country);
        return country.getText().trim();
    }

    public String getCity() {
        return getValue(city);
    }

    public String getState() {
        scrollToElement(state);
        return state.getText().trim();
    }

    public String getDobNormalized() {
        scrollToElement(dob);
        return DateUtil.normalizeDate(getValue(dob));
    }

    public void selectHeight(String heightValue) {
        click(heightDropdown);
        click(heightDropdown);
    }

    public void selectWeight(String weightValue) {
        click(weightDropdown);
        click(weightDropdown);
    }



    public void editFirstName(String value) {
        firstName.clear();
        firstName.sendKeys(value);
        firstName.sendKeys("\t");
    }

    public void editLastName(String value) {
        lastName.clear();
        lastName.sendKeys(value);
        lastName.sendKeys("\t");
    }

    public void editCity(String value) {
        city.clear();
        city.sendKeys(value);
        city.sendKeys("\t"); // triggers blur → enables Save
    }

    public void editAddress(String value) {
        address1.clear();
        address1.sendKeys(value);
        address1.sendKeys("\t");
    }

    public void editPhone(String value) {
        phoneInput.clear();
        phoneInput.sendKeys(value);
        phoneInput.sendKeys("\t");
    }


    public void clickSave() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            scrollToElement(saveBtn);

            // wait until button is enabled (React-safe)
            wait.until(driver -> saveBtn.isDisplayed() && saveBtn.isEnabled());

            try {
                saveBtn.click();
            } catch (ElementClickInterceptedException e) {
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].click();", saveBtn);
            }
    }
}

