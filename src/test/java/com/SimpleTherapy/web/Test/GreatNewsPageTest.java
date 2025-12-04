package com.SimpleTherapy.web.Test;

import com.SimpleTherapy.web.pages.*;
import com.SimpleTherapy.web.utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GreatNewsPageTest extends BaseClass {
    ExcelReader reader;
    LandingPage landingPage;
    ConsentPage consentPage;
    GreatNewsPage greatNewsPage;
    CustomerConfiguration customerConfig;

    @BeforeMethod
    public void setUp() throws Exception {
        initialization();
        reader = new ExcelReader(Constants.SimpleTherapy_TestData);
        customerConfig = new CustomerConfiguration("GreatNewsPage_Data");
        landingPage = new LandingPage();
        consentPage = new ConsentPage();
        greatNewsPage = new GreatNewsPage();
    }

    @Test(description = "Great News Page Validation")
    public void VerifyGreatNewsPage() throws Exception {
        System.out.println("=== Starting Great News Page Validation Test ===");
        String employer = customerConfig.getEmployerNameFromExcel();
        landingPage.selectEmployer(employer);
        scrollToElement(landingPage.getContinueButton());
        Assert.assertTrue(landingPage.isContinueButtonEnabled(), "Continue not enabled");
        landingPage.clickContinueButton();

        consentPage.clickFirstCheckBox();
        consentPage.clickSecondCheckBox();
        consentPage.clickThirdCheckBox();
        consentPage.clickAcceptAllBtn();

        // Great News Page checks
        String actualHeading = greatNewsPage.getGreatNewsHeading();
        String expectedHeading = customerConfig.getGreatNewsHeadingFromExcel();
        System.out.println("Verifying Great News Page heading: " + actualHeading);
        Assert.assertEquals(actualHeading, expectedHeading, "Great News Page Heading mismatch");

        greatNewsPage.openHearAboutUsDropdown();
        Thread.sleep(3000);
        greatNewsPage.selectDemoOption();
        Thread.sleep(3000);
        greatNewsPage.clickContinueBtn();
        System.out.println("=== Great News Page Validation Test Completed Successfully ===");
    }
}
