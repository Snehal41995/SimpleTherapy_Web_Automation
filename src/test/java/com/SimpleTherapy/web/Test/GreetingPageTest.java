package com.SimpleTherapy.web.Test;

import com.SimpleTherapy.web.pages.*;
import com.SimpleTherapy.web.utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GreetingPageTest extends BaseClass {
    ExcelReader reader;
    LandingPage landingPage;
    ConsentPage consentPage;
    GreatNewsPage greatNewsPage;
    NameDetailsPage nameDetailsPage;
    CustomerConfiguration customerConfig;
    GreetingPage greetingPage;

    @BeforeMethod
    public void setUp() throws Exception {
        initialization();
        reader = new ExcelReader(Constants.SimpleTherapy_TestData);
        customerConfig = new CustomerConfiguration("GreetingPage_Data");
        landingPage = new LandingPage();
        consentPage = new ConsentPage();
        greatNewsPage = new GreatNewsPage();
        nameDetailsPage = new NameDetailsPage();
        greetingPage = new GreetingPage();
    }

    @Test(description = "Greeting Page Validation")
    public void VerifyGreetingPage() throws Exception {
        System.out.println("=== Starting Greeting Page Validation Test ===");

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

        // Greeting Page checks
        String actualHeading = greetingPage.getGreetingHeading();
        String expectedHeading = customerConfig.getGreetingHeadingFromExcel();
        System.out.println("Verifying Greeting Page heading: " + actualHeading);
        Assert.assertEquals(actualHeading, expectedHeading, "Greeting Page Heading mismatch");

        Assert.assertTrue(greetingPage.isGreetingImageDisplayed(), "Greeting image did not load!");

        greetingPage.clickContinueBtn();
        System.out.println("=== Greeting Page Validation Test Completed Successfully ===");
    }
}
