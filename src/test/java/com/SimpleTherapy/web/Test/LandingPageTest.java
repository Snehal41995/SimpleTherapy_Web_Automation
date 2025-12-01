package com.SimpleTherapy.web.Test;

import com.SimpleTherapy.web.pages.*;
import com.SimpleTherapy.web.utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LandingPageTest extends BaseClass {

    ExcelReader reader;
    LandingPage landingPage;
    CustomerConfiguration customerConfig;

    @BeforeMethod
    public void setUp() throws Exception {
        initialization();
        reader = new ExcelReader(Constants.SimpleTherapy_TestData);
        customerConfig = new CustomerConfiguration("LandingPage_Data");
        landingPage = new LandingPage();
    }

    @Test (description = "Full Landing Page Validation")
    public void VerifyLandingPage() throws Exception {
        // 1. Verify Title
        Assert.assertEquals(landingPage.getTitle(), customerConfig.getTitleHeadingFromExcel(), "Title Heading mismatch");

        // 2. Select Employer
        String employerName = customerConfig.getEmployerNameFromExcel();
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

        // 3. Intercom Support
        Thread.sleep(5000);
        landingPage.clickHereForHelp();
        Thread.sleep(5000);
        Assert.assertEquals(landingPage.getOperatorHeading(), customerConfig.getOperatorHeadingFromExcel(), "Operator heading is incorrect");
        Thread.sleep(2000);
        landingPage.closeOperator();
        driver.switchTo().defaultContent();

        // 4. Login Here Button
        Thread.sleep(5000);
        landingPage.clickLoginHere();
        Thread.sleep(5000);
        driver.navigate().back();
        scrollUp(800);


        // 5. Language Testing





        // 6. Need Help Popup
        Thread.sleep(5000);
        landingPage.clickNeedHelp();
        Thread.sleep(5000);
        Assert.assertEquals(landingPage.getPopUpHeading(), customerConfig.getPopupHeadingFromExcel(), "Popup heading is incorrect");
        Thread.sleep(5000);
        landingPage.closePopup();

        // 7. Member Login Redirection
        Thread.sleep(2000);
        landingPage.clickMemberLogin();
        Thread.sleep(3000);
        Assert.assertTrue(landingPage.isEmailAddressInputDisplayed(),
                "Email address textbox not found → Login page NOT loaded!");
        System.out.println("Successfully redirected to Login Page!");
    }
}
