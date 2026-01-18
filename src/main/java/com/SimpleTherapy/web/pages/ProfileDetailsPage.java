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

    public void editFirstName(String value) {
        firstName.clear();
        firstName.sendKeys(value);
    }

    public void editLastName(String value) {
        lastName.clear();
        lastName.sendKeys(value);
    }

    public void editCity(String value) {
        city.clear();
        city.sendKeys(value);
    }

    public void clickSave() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // 1️⃣ Wait for button to be present
        WebElement saveButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[normalize-space()='Save Changes']") // trims spaces
        ));

        // 2️⃣ Scroll into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveBtn);

        // 3️⃣ Wait until clickable using JS check (avoids overlay issues)
        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript(
                        "var btn = arguments[0]; return btn.offsetWidth > 0 && btn.offsetHeight > 0 && !btn.disabled;",
                        saveButton
                ).equals(Boolean.TRUE)
        );

        // 4️⃣ Click using JS to avoid Selenium intercepted click
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);
    }
}

