package com.simpleTherapy.web.Test;

import com.simpleTherapy.web.pages.*;
import com.simpleTherapy.web.utils.ExcelReader;
import com.aventstack.extentreports.Status;
import org.apache.poi.ss.util.ImageUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SecondGreetingPageTest extends BaseClass {
    ExcelReader reader;
    LandingPage landingPage;
    ConsentPage consentPage;
    GreatNewsPage greatNewsPage;
    NameDetailsPage nameDetailsPage;
    CustomerConfiguration customerConfig;
    FirstGreetingPage firstGreetingPage;
    SecondGreetingPage secondGreetingPage;

    @BeforeMethod
    public void setUp() throws Exception {
        initialization();
        reader = new ExcelReader(Constants.SimpleTherapy_TestData);
        customerConfig = new CustomerConfiguration("ProgramsInfoPage_Data", 2);
        landingPage = new LandingPage();
        consentPage = new ConsentPage();
        greatNewsPage = new GreatNewsPage();
        nameDetailsPage = new NameDetailsPage();
        firstGreetingPage = new FirstGreetingPage();
        secondGreetingPage = new SecondGreetingPage();
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

    @Test(description = "Greeting Page Validation", dependsOnMethods = "verifyNameDetailsPageTest")
    public void verifyGreetingPageTest() {
        addLog(Status.INFO, "---- Greeting Page Validation Started ----");
        addLog(Status.INFO, "Clicking Continue button");
        firstGreetingPage.clickContinueBtn();
        addLog(Status.PASS, "Greeting Page flow completed successfully");
    }

    @Test(description = "Greeting Page 2 Validation", dependsOnMethods = "verifyGreetingPageTest")
    public void verifyGreetingPageTest2() {
        addLog(Status.INFO, "---- Greeting Page 2 Validation Started ----");
        // Validate heading
        addLog(Status.INFO, "Validating Programs Info Page heading");
        Assert.assertEquals(secondGreetingPage.getSecondGreetingHeading(), customerConfig.getSecondGreetingHeadingFromExcel(), "Greeting page 2 heading mismatch!");
        addLog(Status.PASS, "Greeting Page 2 heading validated successfully");

        List<WebElement> images = secondGreetingPage.getAllImages();
        System.out.println("Total images found: " + images.size());

        for (WebElement img : images) {

            Assert.assertTrue(img.isDisplayed(),
                    "Image not displayed on UI");

            String src = img.getAttribute("src");
            Assert.assertNotNull(src, "Image src attribute is null");
            Assert.assertFalse(src.isEmpty(), "Image src attribute is empty");

            System.out.println("✓ Image displayed: " + src);
        }

        // Add navigation or flow actions here if needed
        addLog(Status.INFO, "Clicking Continue button on Programs Info Page");
       // secondGreetingPage.clickContinueBtn();

        addLog(Status.PASS, "Programs Info Page flow completed successfully");
    }
}
