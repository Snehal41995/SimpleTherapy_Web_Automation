package com.simpleTherapy.web.Test;

import com.aventstack.extentreports.Status;
import com.simpleTherapy.web.pages.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class RegistrationFlowTest extends BaseClass {

    LandingPage landingPage;
    ConsentPage consentPage;
    GreatNewsPage greatNewsPage;
    NameDetailsPage nameDetailsPage;
    FirstGreetingPage firstGreetingPage;
    SecondGreetingPage secondGreetingPage;
    CustomerConfiguration customerConfig;

    @BeforeMethod
    public void setUp() throws Exception {
        initialization();
        customerConfig = new CustomerConfiguration("Dev2");

        landingPage = new LandingPage();
        consentPage = new ConsentPage();
        greatNewsPage = new GreatNewsPage();
        nameDetailsPage = new NameDetailsPage();
        firstGreetingPage = new FirstGreetingPage();
        secondGreetingPage = new SecondGreetingPage();
    }

    @Test(description = "Complete User Registration E2E Flow")
    public void verifyRegistrationFlow() throws Exception {

        // ---- LANDING PAGE ----
        addLog(Status.INFO, "Landing Page Validation");
        landingPage.selectEmployer(customerConfig.getEmployerNameFromExcel());
        Thread.sleep(2000);
        landingPage.clickContinueBtn();

        // ---- CONSENT PAGE ----
        addLog(Status.INFO, "Consent Page Validation");
        Assert.assertEquals(consentPage.getConsentHeading(), customerConfig.getConsentHeadingFromExcel(), "Consent page heading mismatch");
        addLog(Status.PASS, "Consent heading is correct");
        consentPage.clickFirstCheckBox();
        consentPage.clickSecondCheckBox();
        consentPage.clickThirdCheckBox();
        consentPage.clickAcceptAllBtn();
        Thread.sleep(2000);

        // ---- GREAT NEWS PAGE ----
        addLog(Status.INFO, "Great News Page Validation");
        Assert.assertEquals(greatNewsPage.getGreatNewsHeading(), customerConfig.getGreatNewsHeadingFromExcel(), "Great News heading mismatch");
        greatNewsPage.clickHearAboutUsDropdown();
        Thread.sleep(2000);
        greatNewsPage.selectDemoOption();
        Thread.sleep(2000);
        greatNewsPage.clickContinueBtn();
        Thread.sleep(2000);

        // ---- NAME DETAILS PAGE ----
        addLog(Status.INFO, "Name Details Page Validation");
        Assert.assertEquals(nameDetailsPage.getNameDetailsHeading(), customerConfig.getNameDetailsHeadingFromExcel(), "Name Details Page Heading mismatch");
        addLog(Status.PASS, "Name Details Page heading validated");
        nameDetailsPage.enterFirstName(customerConfig.getFirstNameFromExcel());
        Thread.sleep(2000);
        nameDetailsPage.enterLastName(customerConfig.getLastNameFromExcel());
        Thread.sleep(2000);
        nameDetailsPage.enterPrefName(customerConfig.getPrefNameFromExcel());
        Thread.sleep(2000);
        nameDetailsPage.clickContinueBtn();
        Thread.sleep(2000);

        // ---- FIRST GREETING PAGE ----
        addLog(Status.INFO, "Greeting Page 1 Validation");
        Assert.assertEquals(firstGreetingPage.getFirstGreetingHeading(), customerConfig.getFirstGreetingHeadingFromExcel());
        Thread.sleep(3000);
        firstGreetingPage.clickContinueBtn();
        Thread.sleep(3000);

        addLog(Status.INFO, "Checking Greeting Page image is displayed");
        Assert.assertTrue(firstGreetingPage.isGreetingImageDisplayed(), "Greeting image did not load!");
        addLog(Status.PASS, "Greeting image displayed successfully");

        // ---- SECOND GREETING PAGE ----
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
        addLog(Status.INFO, "Greeting Page 2");
        Assert.assertEquals(secondGreetingPage.getSecondGreetingHeading(),
                customerConfig.getSecondGreetingHeadingFromExcel());
        secondGreetingPage.clickContinueBtn();


        //
        addLog(Status.PASS, "Registration Flow Completed Successfully");
    }
}

