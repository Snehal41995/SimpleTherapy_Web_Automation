package com.SimpleTherapy.web.Test;

import com.SimpleTherapy.web.pages.*;
import com.SimpleTherapy.web.utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LandingPageTest extends BaseClass {

    ExcelReader reader;
    LandingPage landingPage;
    CustomerConfiguration customerConfig;

    @BeforeMethod
    public void setUp() throws Exception {
        initialization();
        reader = new ExcelReader(Constants.SimpleTherapy_TestData);
        customerConfig = new CustomerConfiguration("LandingPage_Data");
        landingPage = new LandingPage();
    }

    @Test (description = "Full Landing Page Validation")
    public void VerifyLandingPage() throws Exception {
        System.out.println("=== Starting Landing Page Validation Test ===");

        // 1. Verify Title
        String actualTitle = landingPage.getTitle();
        String expectedTitle = customerConfig.getTitleHeadingFromExcel();
        System.out.println("Verifying page title: " + actualTitle);
        Assert.assertEquals(actualTitle, expectedTitle, "Title mismatch");

        // 2. Select Employer
        scrollDown(800);
        System.out.println("Selecting employer: " + customerConfig.getEmployerNameFromExcel());
        landingPage.selectEmployer(customerConfig.getEmployerNameFromExcel());
        Thread.sleep(5000);
        scrollToElement(landingPage.getContinueButton());
        Thread.sleep(5000);
        Assert.assertTrue(landingPage.isContinueButtonEnabled(), "Continue button not enabled");
        System.out.println("Continue button enabled successfully.");

        // 3. Intercom Support
        System.out.println("Opening Intercom Help popup...");
        landingPage.clickHereForHelp();
        Thread.sleep(5000);
        String operatorHeading = landingPage.getOperatorHeading();
        System.out.println("Operator heading on Intercom popup: " + operatorHeading);
        Assert.assertEquals(operatorHeading, customerConfig.getOperatorHeadingFromExcel(), "Operator heading incorrect");
        Thread.sleep(2000);
        landingPage.closeOperator();
        driver.switchTo().defaultContent();

        // 4. Login Here button
        System.out.println("Clicking 'Login Here' button...");
        landingPage.clickLoginHere();
        Thread.sleep(5000);
        driver.navigate().back();
        scrollUp(800);
        System.out.println("Navigated back from Login page.");

        // 5. Language Testing

        // 6. Need Help Popup
        System.out.println("Opening Need Help popup...");
        landingPage.clickNeedHelp();
        Thread.sleep(5000);
        String popupHeading = landingPage.getPopUpHeading();
        System.out.println("Need Help popup heading: " + popupHeading);
        Assert.assertEquals(popupHeading, customerConfig.getPopupHeadingFromExcel(), "Need Help popup heading mismatch");
        landingPage.closePopup();

        // 7. Member Login Redirection
        System.out.println("Clicking Member Login...");
        landingPage.clickMemberLogin();
        Thread.sleep(3000);
        Assert.assertTrue(landingPage.isEmailAddressInputDisplayed(), "Login page did not load");
        System.out.println("Successfully redirected to Login Page!");
        System.out.println("=== Landing Page Validation Test Completed Successfully ===");
    }
}
