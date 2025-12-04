package com.SimpleTherapy.web.Test;

import com.SimpleTherapy.web.pages.*;
import com.SimpleTherapy.web.utils.ExcelReader;
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
        customerConfig = new CustomerConfiguration("NameDetailsPage_Data");
        landingPage = new LandingPage();
        consentPage = new ConsentPage();
        greatNewsPage = new GreatNewsPage();
        nameDetailsPage = new NameDetailsPage();
    }

    @Test(description = "Name Details Page Validation")
    public void VerifyNameDetailsPage() throws Exception {
        System.out.println("=== Starting Name Details Page Validation Test ===");

        String employer = customerConfig.getEmployerNameFromExcel();
        landingPage.selectEmployer(employer);
        scrollToElement(landingPage.getContinueButton());
        Assert.assertTrue(landingPage.isContinueButtonEnabled(), "Continue not enabled");
        landingPage.clickContinueButton();

        consentPage.clickFirstCheckBox();
        consentPage.clickSecondCheckBox();
        consentPage.clickThirdCheckBox();
        consentPage.clickAcceptAllBtn();

        // Name Details Page checks
        String actualHeading = nameDetailsPage.getNameDetailsHeading();
        String expectedHeading = customerConfig.getNameDetailsHeadingFromExcel();
        System.out.println("Verifying Name Details Page heading: " + actualHeading);
        Assert.assertEquals(actualHeading, expectedHeading, "Name Details Page Heading mismatch");

        greatNewsPage.openHearAboutUsDropdown();
        Thread.sleep(3000);
        greatNewsPage.selectDemoOption();
        Thread.sleep(3000);
        greatNewsPage.clickContinueBtn();

        String firstName = customerConfig.getFirstNameFromExcel();
        String lastName = customerConfig.getLastNameFromExcel();
        String prefName = customerConfig.getPrefNameFromExcel();

        nameDetailsPage.enterFirstName(firstName);
        nameDetailsPage.enterLastName(lastName);
        nameDetailsPage.enterPrefName(prefName);
        nameDetailsPage.clickContinueBtn();
        System.out.println("=== Names Details Page Validation Test Completed Successfully ===");
    }
}
