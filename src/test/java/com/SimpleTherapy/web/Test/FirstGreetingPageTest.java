package com.simpleTherapy.web.Test;

import com.simpleTherapy.web.pages.*;
import com.simpleTherapy.web.utils.ExcelReader;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FirstGreetingPageTest extends BaseClass {
    ExcelReader reader;
    LandingPage landingPage;
    ConsentPage consentPage;
    GreatNewsPage greatNewsPage;
    NameDetailsPage nameDetailsPage;
    CustomerConfiguration customerConfig;
    FirstGreetingPage firstGreetingPage;

    @BeforeMethod
    public void setUp() throws Exception {
        initialization();
        reader = new ExcelReader(Constants.SimpleTherapy_TestData);
        customerConfig = new CustomerConfiguration("GreetingPage_Data");
        landingPage = new LandingPage();
        consentPage = new ConsentPage();
        greatNewsPage = new GreatNewsPage();
        nameDetailsPage = new NameDetailsPage();
        firstGreetingPage = new FirstGreetingPage();
    }

    @Test(description = "Landing Page Flow")
    public void verifyLandingPageTest() throws Exception {
        addLog(Status.INFO, "---- Landing Page flow Started ----");
        landingPage.selectEmployer(customerConfig.getEmployerNameFromExcel());
        landingPage.clickContinueBtn();
        addLog(Status.PASS, "Landing Page flow completed");
    }

    @Test(description = "Consent Page Flow", dependsOnMethods = "verifyLandingPageTest")
    public void verifyConsentPageTest() throws Exception {
        addLog(Status.INFO, "---- Consent Page flow Started ----");
        consentPage.clickFirstCheckBox();
        consentPage.clickSecondCheckBox();
        consentPage.clickThirdCheckBox();
        consentPage.clickAcceptAllBtn();
        addLog(Status.PASS, "Consent Page flow completed");
    }

    @Test(description = "Great News Page Flow", dependsOnMethods = "verifyConsentPageTest")
    public void verifyGreatNewsPageTest() throws Exception {
        addLog(Status.INFO, "----Great News Page flow Started ----");
        greatNewsPage.clickHearAboutUsDropdown();
        greatNewsPage.selectDemoOption();
        greatNewsPage.clickContinueBtn();
        addLog(Status.PASS, "Great News Page flow completed");
    }

    @Test(description = "Name Details Page Validation", dependsOnMethods = "verifyGreatNewsPageTest")
    public void verifyNameDetailsPageTest() throws Exception {
        addLog(Status.INFO, "---- Name Details Page Validation Started ----");
        nameDetailsPage.enterFirstName(customerConfig.getFirstNameFromExcel());
        nameDetailsPage.enterLastName(customerConfig.getLastNameFromExcel());
        nameDetailsPage.enterPrefName(customerConfig.getPrefNameFromExcel());
        addLog(Status.INFO, "Entered First, Last, and Preferred Name");
        nameDetailsPage.clickContinueBtn();
        addLog(Status.PASS, "Name Details Page flow completed successfully");
    }

    @Test(description = "First Greeting Page Validation", dependsOnMethods = "verifyNameDetailsPageTest")
    public void verifyGreetingPageTest() {
        addLog(Status.INFO, "---- Greeting Page Validation Started ----");
        // Validate heading (direct assertion)
        addLog(Status.INFO, "Validating Greeting Page heading");
        Assert.assertEquals(firstGreetingPage.getFirstGreetingHeading(), customerConfig.getFirstGreetingHeadingFromExcel(), "Greeting Page Heading mismatch!");
        addLog(Status.PASS, "Greeting Page heading validated");

        // Validate image is displayed
        addLog(Status.INFO, "Checking Greeting Page image is displayed");
        Assert.assertTrue(firstGreetingPage.isGreetingImageDisplayed(), "Greeting image did not load!");
        addLog(Status.PASS, "Greeting image displayed successfully");

        // Continue flow
        addLog(Status.INFO, "Clicking Continue button");
        firstGreetingPage.clickContinueBtn();
        addLog(Status.PASS, "Greeting Page flow completed successfully");
    }
}
