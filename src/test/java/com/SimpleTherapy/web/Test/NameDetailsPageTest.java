package com.simpleTherapy.web.Test;

import com.simpleTherapy.web.pages.*;
import com.simpleTherapy.web.utils.ExcelReader;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NameDetailsPageTest extends BaseClass {
    ExcelReader reader;
    LandingPage landingPage;
    ConsentPage consentPage;
    GreatNewsPage greatNewsPage;
    NameDetailsPage nameDetailsPage;
    CustomerConfiguration customerConfig;

    @BeforeMethod
    public void setUp() throws Exception {
        initialization();
        reader = new ExcelReader(Constants.SimpleTherapy_TestData);
        customerConfig = new CustomerConfiguration("NameDetailsPage_Data", 2);
        landingPage = new LandingPage();
        consentPage = new ConsentPage();
        greatNewsPage = new GreatNewsPage();
        nameDetailsPage = new NameDetailsPage();
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

    @Test(description = "Name Details Page Validation", dependsOnMethods = "verifyGreatNewsPage")
    public void verifyNameDetailsPageTest() throws Exception {
        addLog(Status.INFO, "---- Name Details Page Validation Started ----");
        Assert.assertEquals(nameDetailsPage.getNameDetailsHeading(), customerConfig.getNameDetailsHeadingFromExcel(), "Name Details Page Heading mismatch");
        addLog(Status.PASS, "Name Details Page heading validated");
        // ---- Enter Name Details ----
        nameDetailsPage.enterFirstName(customerConfig.getFirstNameFromExcel());
        nameDetailsPage.enterLastName(customerConfig.getLastNameFromExcel());
        nameDetailsPage.enterPrefName(customerConfig.getPrefNameFromExcel());
        addLog(Status.INFO, "Entered First, Last, and Preferred Name");
        // Continue action
        nameDetailsPage.clickContinueBtn();
        addLog(Status.PASS, "Name Details Page flow completed successfully");
    }
}
