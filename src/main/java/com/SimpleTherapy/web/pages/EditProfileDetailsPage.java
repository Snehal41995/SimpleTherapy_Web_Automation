package com.simpleTherapy.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EditProfileDetailsPage extends BaseClass {

    @FindBy(xpath = "//input[@name='firstname']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@name='lastname']")
    private WebElement lastName;

    @FindBy(xpath = "//input[@name='address1']")
    private WebElement address;

    @FindBy(xpath = "//input[@name='city']")
    private WebElement city;

    @FindBy(xpath = "//input[@name='phone_number']")
    private WebElement phone;

    @FindBy(xpath = "//input[@name='height']/preceding-sibling::div[contains(@class,'control')]")
    private WebElement heightDropdown;

    @FindBy(xpath = "//input[@name='weight']/preceding-sibling::div[contains(@class,'control')]")
    private WebElement weightDropdown;

    @FindBy(xpath = "//button[.//span[text()='Save Changes']]")
    private WebElement saveBtn;

    public EditProfileDetailsPage() {
        PageFactory.initElements(driver, this);
    }

    /* ---------- EDIT ACTIONS ---------- */

    public void editFirstName(String value) {
        clearAndType(firstName, value);
    }

    public void editLastName(String value) {
        clearAndType(lastName, value);
    }

    public void editAddress(String value) {
        clearAndType(address, value);
    }

    public void editCity(String value) {
        clearAndType(city, value);
    }

    public void editPhone(String value) {
        clearAndType(phone, value);
    }

    private String escapeForXPath(String value) {
        if (value.contains("'")) {
            return "concat('" + value.replace("'", "',\"'\",'") + "')";
        }
        return "'" + value + "'";
    }

    public void selectHeight(String heightValue) {
        click(heightDropdown);

        WebElement option = driver.findElement(
                By.xpath("//div[@role='listbox']//div[@role='option' and normalize-space(.)="
                        + escapeForXPath(heightValue) + "]")
        );
        option.click();
    }



    public void selectWeight(String weightValue) {
        weightDropdown.click();

        driver.findElement(By.xpath("//div[@role='listbox']//div[@role='option' and normalize-space()='"
                + weightValue + "']"
        )
        ).click();
    }


    /* ---------- SAVE ---------- */

    public void clickSave() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        scrollToElement(saveBtn);
        wait.until(d -> saveBtn.isEnabled());

        try {
            saveBtn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", saveBtn);
        }
    }

    /* ---------- COMMON ---------- */

    private void clearAndType(WebElement element, String value) {
        scrollToElement(element);
        element.clear();
        element.sendKeys(value);
        element.sendKeys("\t");
    }
}
