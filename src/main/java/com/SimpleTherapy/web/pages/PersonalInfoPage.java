package com.simpleTherapy.web.pages;

import com.simpleTherapy.web.utils.EmailCounterUtil;
import com.simpleTherapy.web.utils.PhoneUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PersonalInfoPage extends BaseClass {

    @FindBy(xpath = "//h1[contains(text(), 'Tell us about yourself')]")
    WebElement personalInfoHeading;

    @FindBy(xpath = "//input[@id='radio-P']")
    WebElement primaryRadioBtn;

    @FindBy(xpath = "//input[@placeholder='Your Birthday']")
    WebElement dobField;

    @FindBy(xpath = "//select[contains(@class,'react-datepicker__month-select')]")
    WebElement monthSelect;

    @FindBy(xpath = "//select[contains(@class,'react-datepicker__year-select')]")
    WebElement yearSelect;

    @FindBy(xpath = "//input[@placeholder='Email address']")
    WebElement emailInput;

    @FindBy(xpath = "//input[@name='phone_number']")
    WebElement phoneNumberInput;

    @FindBy(xpath = "//div[@id='sex']//div[contains(@class,'css-1wy0on6')]")
    private WebElement sexDropdown;

    @FindBy(xpath = "//div[@role='option' and text()='Male']")
    private WebElement option;

    @FindBy(xpath = "//span[text()='Continue']")
    private WebElement continueBtn;

    public PersonalInfoPage() {
        PageFactory.initElements(driver, this);
    }

    public String getPersonalInfoHeading() {
        return personalInfoHeading.getText();
    }

    public void clickPrimaryRadioBtn() {
        clickOnMe(primaryRadioBtn);
    }

    // Select fixed DOB: 01 January 1995
    public void selectDOB() {
        dobField.click();

        new Select(monthSelect).selectByVisibleText("January");
        new Select(yearSelect).selectByVisibleText("1995");

        driver.findElement(By.xpath("//div[contains(@class,'react-datepicker__day') and text()='1']")).click();
    }

    public String enterEmail() {

        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int counter = EmailCounterUtil.getNextCounter();

        String email = "sagar+" + today + counter + "@mobiuso.com";

        emailInput.clear();
        emailInput.sendKeys(email);

        System.out.println("Generated Email: " + email);
        return email;
    }




    public void enterPhoneNumber(String phoneNumber) {
        phoneNumberInput.clear();
        phoneNumberInput.sendKeys(phoneNumber);
    }

    public String getNormalizedPhoneNumber() {
        return PhoneUtil.normalize(phoneNumberInput.getAttribute("value"));
    }

    public void selectGender() {
        click(sexDropdown);
        click(option);
    }

    public void clickContinue() {
        scrollToElement(continueBtn);
        click(continueBtn);
    }
}
