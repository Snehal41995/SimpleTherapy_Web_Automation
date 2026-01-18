package com.simpleTherapy.web.Test;

import com.simpleTherapy.web.pages.*;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ConsentPageTest extends BaseClass {
    LandingPage landingPage;
    ConsentPage consentPage;
    CustomerConfiguration customerConfig;

    @BeforeMethod
    public void setUp() throws Exception {
        initialization();
        customerConfig = new CustomerConfiguration("Dev2", 2);
        landingPage = new LandingPage();
        consentPage = new ConsentPage();
    }

    @Test(description = "Landing Page Flow")
    public void verifyLandingPageTest() throws Exception {
        addLog(Status.INFO, "---- Landing Page Flow Started ----");
        landingPage.selectEmployer(customerConfig.getEmployerNameFromExcel());
        landingPage.clickContinueBtn();
        addLog(Status.PASS, "Landing Page flow completed");
    }

    @Test(description = "Consent Page Validation", dependsOnMethods = "verifyLandingPageTest")
    public void verifyConsentPageTest() throws Exception {
        addLog(Status.INFO, "-- Starting Consent Page Validation --");
        // 1. Validate heading
        addLog(Status.INFO, "Validating Consent heading");
        Assert.assertEquals(consentPage.getConsentHeading(), customerConfig.getConsentHeadingFromExcel(), "Consent page heading mismatch");
        addLog(Status.PASS, "Consent heading is correct");

        // 2. First Checkbox
        addLog(Status.INFO, "Validating First Checkbox default state");
        Assert.assertFalse(consentPage.isFirstCheckBoxSelected(), "First checkbox should be unchecked");
        addLog(Status.INFO, "Clicking First Checkbox");
        consentPage.clickFirstCheckBox();
        Assert.assertTrue(consentPage.isFirstCheckBoxSelected(), "First checkbox should be checked");
        addLog(Status.PASS, "First checkbox validated successfully");

        // 3. Second Checkbox
        addLog(Status.INFO, "Validating Second Checkbox default state");
        Assert.assertFalse(consentPage.isSecondCheckBoxSelected(), "Second checkbox should be unchecked");
        addLog(Status.INFO, "Clicking Second Checkbox");
        consentPage.clickSecondCheckBox();
        Assert.assertTrue(consentPage.isSecondCheckBoxSelected(), "Second checkbox should be checked");
        addLog(Status.PASS, "Second checkbox validated successfully");

        // 4. Third Checkbox
        addLog(Status.INFO, "Validating Third Checkbox default state");
        Assert.assertFalse(consentPage.isThirdCheckBoxSelected(), "Third checkbox should be unchecked");
        addLog(Status.INFO, "Clicking Third Checkbox");
        consentPage.clickThirdCheckBox();
        Assert.assertTrue(consentPage.isThirdCheckBoxSelected(), "Third checkbox should be checked");
        addLog(Status.PASS, "Third checkbox validated successfully");

        // 5. Accept Button
        addLog(Status.INFO, "Validating Accept button is enabled");
        Assert.assertTrue(consentPage.isAcceptAllBtnEnabled(), "Accept button should be enabled");
        addLog(Status.INFO, "Clicking Accept button");
        consentPage.clickAcceptAllBtn();
        addLog(Status.PASS, "Consent page validation completed successfully");
    }
}
