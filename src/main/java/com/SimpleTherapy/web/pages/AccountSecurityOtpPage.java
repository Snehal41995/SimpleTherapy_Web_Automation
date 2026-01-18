package com.simpleTherapy.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSecurityOtpPage extends BaseClass {
    @FindBy(xpath = "//h1[text()='Secure Your Account']")
    WebElement accountSecurityOtpHeading;

    @FindBy(xpath = "//button[contains(text(),'Trouble Scanning')]")
    WebElement btnTroubleScanning;

    @FindBy(xpath = "//div[normalize-space(text()) and string-length(normalize-space(text())) > 25]")
    private WebElement secretKeyText;

    // OTP input
    @FindBy(xpath = "//input[@name='code']")
    private WebElement otpInput;

    // Continue button
    @FindBy(xpath = "//button[text()='Continue']")
    private WebElement continueBtn;

    public AccountSecurityOtpPage() {
        PageFactory.initElements(driver, this);
    }

    public String getAccountSecurityOtpHeading() {
        return accountSecurityOtpHeading.getText();
    }

    public void clickTroubleScanning() {
        btnTroubleScanning.click();
    }

    public String getSecretKey() throws InterruptedException {
        Thread.sleep(2000);
        return secretKeyText.getText().replace(" ", "").trim();
    }

    public void enterOtp(String otp) {
        otpInput.clear();
        otpInput.sendKeys(otp);
    }

    public void clickContinue() {
        scrollToElement(continueBtn);
        continueBtn.click();
    }

}
