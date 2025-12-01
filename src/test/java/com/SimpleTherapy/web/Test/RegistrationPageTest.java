package com.SimpleTherapy.web.Test;

import com.SimpleTherapy.web.pages.*;
import com.SimpleTherapy.web.utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationPageTest extends BaseClass {
    ExcelReader reader;
    LandingPage landingPage;
    RegistrationPage registrationPage;
    CustomerConfiguration customerConfig;

    String sheetName2 = "RegistrationPage_Data";

    @BeforeMethod
    public void setUp() throws Exception {
        initialization();
        reader = new ExcelReader(Constants.SimpleTherapy_TestData);
        //customerConfig = new CustomerConfiguration("RegistrationPage_Data");
        landingPage = new LandingPage();
        registrationPage = new RegistrationPage();
        customerConfig = new CustomerConfiguration(sheetName2);
    }

    @Test(description = "Full Registration Page Validation")
    public void VerifyRegistrationPage() throws Exception {
        // 2. Select Employer
        String employerName = customerConfig.getEmployerNameFromExcelReg();
        landingPage.selectEmployer(employerName);
        Thread.sleep(5000);

        scrollToElement(landingPage.getContinueButton());
        Thread.sleep(5000);

        boolean isEnabled = landingPage.isContinueButtonEnabled();
        Assert.assertTrue(isEnabled, "Continue button should be enabled after employer selection");

        landingPage.selectEmployer(customerConfig.getEmployerNameFromExcel());
        Thread.sleep(2000);

        scrollToElement(landingPage.getContinueButton());
        Assert.assertTrue(landingPage.isContinueButtonEnabled(),
                "Continue button should be enabled");

        registrationPage.clickContinueButton();
        Thread.sleep(5000);
        System.out.println("Heading from Excel: " + customerConfig.getConsentHeadingFromExcel());
        Assert.assertEquals(registrationPage.getConsentHeading(), customerConfig.getConsentHeadingFromExcel(), "Consent Page Heading mismatch");
        Thread.sleep(2000);

        registrationPage.clickFirstCheckBox();
        Thread.sleep(2000);
        registrationPage.clickSecondCheckBox();
        Thread.sleep(2000);
        registrationPage.clickThirdCheckBox();
        Thread.sleep(2000);
        registrationPage.clickAcceptAllbutton();
        Thread.sleep(2000);

        System.out.println("Heading from Excel: " + customerConfig.getGreatNewsHeadingFromExcel());
        Assert.assertEquals(registrationPage.getGreatNewsHeading(), customerConfig.getGreatNewsHeadingFromExcel(), "Greate News Page Heading mismatch");
        Thread.sleep(2000);
    }

}
