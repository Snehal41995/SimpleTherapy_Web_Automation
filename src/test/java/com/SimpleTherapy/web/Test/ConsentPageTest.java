package com.SimpleTherapy.web.Test;

import com.SimpleTherapy.web.pages.*;
import com.SimpleTherapy.web.utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ConsentPageTest extends BaseClass {
    ExcelReader reader;
    LandingPage landingPage;
    ConsentPage consentPage;
    CustomerConfiguration customerConfig;

    @BeforeMethod
    public void setUp() throws Exception {
        initialization();
        reader = new ExcelReader(Constants.SimpleTherapy_TestData);
        customerConfig = new CustomerConfiguration("ConsentPage_Data");
        landingPage = new LandingPage();
        consentPage = new ConsentPage();
    }

    @Test(description = "Consent Page Validation")
    public void VerifyConsentPage() throws Exception {
        System.out.println("=== Starting Consent Page Validation Test ===");
        String employer = customerConfig.getEmployerNameFromExcel();
        landingPage.selectEmployer(employer);
        scrollToElement(landingPage.getContinueButton());
        Assert.assertTrue(landingPage.isContinueButtonEnabled(), "Continue not enabled");
        landingPage.clickContinueButton();

        // Consent Page checks
        String actualHeading = consentPage.getConsentHeading();
        String expectedHeading = customerConfig.getConsentHeadingFromExcel();
        System.out.println("Verifying Consent Page heading: " + actualHeading);
        Assert.assertEquals(actualHeading, expectedHeading, "Consent heading mismatch");

        System.out.println("Selecting Consent Checkboxes...");
        consentPage.clickFirstCheckBox();
        consentPage.clickSecondCheckBox();
        consentPage.clickThirdCheckBox();
        System.out.println("All checkboxes selected successfully.");

        System.out.println("Clicking Accept All button...");
        consentPage.clickAcceptAllBtn();
        System.out.println("Consent accepted successfully.");
        System.out.println("=== Consent Page Validation Test Completed Successfully ===");
    }
}
