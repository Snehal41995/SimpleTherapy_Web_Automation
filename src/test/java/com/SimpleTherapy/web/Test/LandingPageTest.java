package com.simpleTherapy.web.Test;

import com.simpleTherapy.web.pages.*;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LandingPageTest extends BaseClass {
    LandingPage landingPage;
    CustomerConfiguration customerConfig;

    @BeforeMethod
    public void setUp() throws Exception {
        initialization();
        customerConfig = new CustomerConfiguration("Dev2", 2);
        landingPage = new LandingPage();
    }

    @Test(description = "Full Landing Page Validation")
    public void verifyLandingPageTest() throws InterruptedException {

        addLog(Status.INFO, "=== Starting Landing Page Validation Test ===");

        /* 1. Title Validation */
        Assert.assertEquals(landingPage.getTitleHeading(), customerConfig.getTitleHeadingFromExcel(),
                "Landing page title mismatch");
        addLog(Status.PASS, "Landing page title verified");

        /* 2. Employer Selection */
        scrollDown(800);
        addLog(Status.INFO, "Selecting employer: " + customerConfig.getEmployerNameFromExcel());
        landingPage.selectEmployer(customerConfig.getEmployerNameFromExcel());
        Assert.assertTrue(landingPage.isContinueBtnEnabled(),
                "Continue button not enabled after employer selection");
        addLog(Status.PASS, "Employer selected and Continue button enabled");

        /* 3. Intercom Support */
        landingPage.clickHereForHelpLink();
        Assert.assertEquals(landingPage.getChatBotHeading(), customerConfig.getChatBotHeadingFromExcel(),
                "ChatBot heading mismatch");
        landingPage.closeChatBot();
        addLog(Status.PASS, "Intercom ChatBot validated and closed");

        /* 4. Login Here Navigation */
        landingPage.clickLoginHereLink();
        Assert.assertTrue(landingPage.isEmailAddressInputDisplayed(),
                "Login page did not load after clicking Login Here");
        driver.navigate().back();
        addLog(Status.PASS, "'Login Here' navigation verified");

        /* 5. Language Validation */
        String[][] languages = {
                {"pl", "Witamy w SimpleTherapy"},
                {"it", "Benvenuto su SimpleTherapy"}
        };

        for (String[] data : languages) {
            String code = data[0];
            String expectedHeading = data[1];

            addLog(Status.INFO, "Validating language: " + code);
            landingPage.selectLanguageByCode(code);

            Thread.sleep(3000);
            Assert.assertEquals(landingPage.getHeading(), expectedHeading,
                    "Heading translation mismatch for language: " + code);
            addLog(Status.PASS, "Language '" + code + "' verified successfully");
        }

        addLog(Status.INFO, "Switching language back to English");
        landingPage.selectLanguageByCode("en");

        /* 6. Need Help Popup */
        landingPage.clickNeedHelpBtn();

        Assert.assertEquals(landingPage.getNeedHelpPopUpHeading(), customerConfig.getPopupHeadingFromExcel(),
                "Need Help popup heading mismatch");
        landingPage.closePopup();
        addLog(Status.PASS, "Need Help popup validated and closed");

        /* 7. Member Login */
        landingPage.clickMemberLoginLink();

        Assert.assertTrue(landingPage.isEmailAddressInputDisplayed(),
                "Member Login page did not load");
        addLog(Status.PASS, "Member Login page loaded successfully");

        addLog(Status.INFO, "=== Landing Page Validation Test Completed Successfully ===");
    }
}