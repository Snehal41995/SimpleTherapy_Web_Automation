package com.SimpleTherapy.web.Test;

import com.SimpleTherapy.web.pages.*;
import com.SimpleTherapy.web.utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProgramsInfoPageTest extends BaseClass {
    ExcelReader reader;
    LandingPage landingPage;
    ConsentPage consentPage;
    GreatNewsPage greatNewsPage;
    NameDetailsPage nameDetailsPage;
    CustomerConfiguration customerConfig;
    GreetingPage greetingPage;
    ProgramsInfoPage programsInfoPage;

    @BeforeMethod
    public void setUp() throws Exception {
        initialization();
        reader = new ExcelReader(Constants.SimpleTherapy_TestData);
        customerConfig = new CustomerConfiguration("ProgramsInfoPage_Data");
        landingPage = new LandingPage();
        consentPage = new ConsentPage();
        greatNewsPage = new GreatNewsPage();
        nameDetailsPage = new NameDetailsPage();
        greetingPage = new GreetingPage();
        programsInfoPage = new ProgramsInfoPage();
    }

    @Test(description = "ProgramsInfo Page Validation")
    public void VerifyProgramsInfoPage() throws Exception {
        System.out.println("=== Starting ProgramsInfo Page Validation Test ===");

        String employer = customerConfig.getEmployerNameFromExcel();
        landingPage.selectEmployer(employer);
        scrollToElement(landingPage.getContinueButton());
        Assert.assertTrue(landingPage.isContinueButtonEnabled(), "Continue not enabled");
        landingPage.clickContinueButton();

        consentPage.clickFirstCheckBox();
        consentPage.clickSecondCheckBox();
        consentPage.clickThirdCheckBox();
        consentPage.clickAcceptAllBtn();

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

        greetingPage.clickContinueBtn();

        // ProgramsInfo Page checks
        String actualHeading = programsInfoPage.getProgramsInfoHeading();
        String expectedHeading = customerConfig.getProgramsInfoHeadingFromExcel();
        System.out.println("Verifying ProgramsInfo Page heading: " + actualHeading);
        Assert.assertEquals(actualHeading, expectedHeading, "ProgramsInfo heading mismatch");


        System.out.println("=== ProgramsInfo Page Validation Test Completed Successfully ===");
    }
}
