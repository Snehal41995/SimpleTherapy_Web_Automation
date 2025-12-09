package com.simpleTherapy.web.Test;

import com.simpleTherapy.web.pages.*;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GreatNewsPageTest extends BaseClass {
    LandingPage landingPage;
    ConsentPage consentPage;
    GreatNewsPage greatNewsPage;
    CustomerConfiguration customerConfig;

    @BeforeMethod
    public void setUp() throws Exception {
        initialization();
        customerConfig = new CustomerConfiguration("Dev2");
        landingPage = new LandingPage();
        consentPage = new ConsentPage();
        greatNewsPage = new GreatNewsPage();
    }

    @Test(description = "Landing Page Flow")
    public void verifyLandingPageTest() throws Exception {
        addLog(Status.INFO, "---- Landing Page Flow Started ----");
        landingPage.selectEmployer(customerConfig.getEmployerNameFromExcel());
        landingPage.clickContinueBtn();
        addLog(Status.PASS, "Landing Page flow completed");
    }

    @Test(description = "Consent Page Flow", dependsOnMethods = "verifyLandingPageTest")
    public void verifyConsentPageTest() throws Exception {
        addLog(Status.INFO, "---- Consent Page Flow Started ----");
        consentPage.clickFirstCheckBox();
        consentPage.clickSecondCheckBox();
        consentPage.clickThirdCheckBox();
        consentPage.clickAcceptAllBtn();
        addLog(Status.PASS, "Consent Page flow completed");
    }

    @Test(description = "Great News Page Validation", dependsOnMethods = "verifyConsentPageTest")
    public void verifyGreatNewsPageTest() throws Exception {
        addLog(Status.INFO, "---- Great News Page Validation Started ----");
        Assert.assertEquals(greatNewsPage.getGreatNewsHeading(), customerConfig.getGreatNewsHeadingFromExcel(), "Great News Page heading mismatch!");
        addLog(Status.PASS, "Great News Page heading validated");
        greatNewsPage.clickHearAboutUsDropdown();
        greatNewsPage.selectDemoOption();
        greatNewsPage.clickContinueBtn();
        addLog(Status.PASS, "Great News Page flow completed successfully");
    }
}
