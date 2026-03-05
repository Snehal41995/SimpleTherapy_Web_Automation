package com.simpleTherapy.web.pages;

import com.simpleTherapy.web.utils.DateUtil;
import com.simpleTherapy.web.utils.PhoneUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.nio.file.Paths;

public class ProfileDetailsPage extends BaseClass {

    @FindBy(xpath = "//input[@name='firstname']")
    WebElement firstName;

    @FindBy(xpath = "//input[@name='lastname']")
    WebElement lastName;

    @FindBy(xpath = "//input[@type='text' and @disabled and contains(@value,'@')]")
    WebElement email;

    @FindBy(xpath = "//input[@name='phone_number']")
    WebElement phone;

    @FindBy(xpath = "//input[@name='address1']")
    WebElement address;

    @FindBy(xpath = "//div[normalize-space()='United States']")
    WebElement country;

    @FindBy(xpath = "//input[@name='city']")
    WebElement city;

    @FindBy(xpath = "//div[contains(@class,'singleValue') and normalize-space()='California']")
    WebElement state;

    @FindBy(xpath = "//input[@placeholder='Minor date of birth']")
    WebElement dob;

    // Profile image
    @FindBy(xpath = "//input[@type='file']")
    WebElement uploadInput;

    @FindBy(xpath = "//img[contains(@src,'data:image')]")
    WebElement profileImage;

    @FindBy(xpath = "//*[name()='svg' and contains(@class,'arrow-left')]")
    WebElement backIcon;

    @FindBy(xpath = "//button[@aria-label='User Menu']")
    WebElement profileIcon;

    @FindBy(xpath = "//span[normalize-space()='Logout']")
    WebElement logoutBtn;

    @FindBy(xpath = "//button[.//span[normalize-space()='Confirm']]")
    WebElement confirmLogoutBtn;

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

    public String getEmail() {
        return getValue(email);
    }

    public String getPhone() {
        return PhoneUtil.normalize(phone.getAttribute("value"));
    }

    public String getAddress() {
        return getValue(address);
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

    public String getDob() {
        return DateUtil.normalizeDate(getValue(dob));
    }

    // Profile picture upload
    public void uploadProfilePicture(String imagePath) {
        String absolutePath = Paths.get(imagePath).toAbsolutePath().toString();
        uploadInput.sendKeys(absolutePath);
    }


    public boolean isProfileImageDisplayed() throws InterruptedException {
        Thread.sleep(2000);
        return profileImage.isDisplayed();
    }

    public String getProfileImageSrc() {
        return profileImage.getAttribute("src");
    }

    public void clickBackIcon() {
        click(backIcon);
    }

    public void clickProfileIcon() {
        click(profileIcon);
    }

    public void clickLogoutBtn() {
        click(logoutBtn);
    }

    public void confirmLogoutBtn() {
        click(confirmLogoutBtn);
    }

}

