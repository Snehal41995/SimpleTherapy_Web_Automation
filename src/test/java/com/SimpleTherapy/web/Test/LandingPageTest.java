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
    public void verifyLandingPageTest() throws Exception {
        addLog(Status.INFO, "-- Starting Landing Page Validation Test --");
        // 1. Verify Title
        Assert.assertEquals(landingPage.getTitleHeading(), customerConfig.getTitleHeadingFromExcel(), "Title mismatch");
        addLog(Status.PASS, "Landing page title verified successfully");

        // 2. Select Employer
        addLog(Status.INFO, "Scrolling down to select employer");
        scrollDown(800);
        addLog(Status.INFO, "Selecting employer: " + customerConfig.getEmployerNameFromExcel());
        landingPage.selectEmployer(customerConfig.getEmployerNameFromExcel());
        Thread.sleep(5000);
        Assert.assertTrue(landingPage.isContinueBtnEnabled(), "Continue button not enabled");
        addLog(Status.PASS, "Continue button enabled successfully");

        // 3. Intercom Support
        addLog(Status.INFO, "Opening Intercom Help popup");
        landingPage.clickHereForHelpLink();
        Thread.sleep(5000);
        Assert.assertEquals(landingPage.getChatBotHeading(), customerConfig.getChatBotHeadingFromExcel(), "ChatBot heading incorrect");
        Thread.sleep(2000);
        landingPage.closeChatBot();
        driver.switchTo().defaultContent();
        addLog(Status.PASS, "Intercom ChatBot closed successfully");

        // 4. Login Here button
        addLog(Status.INFO, "Clicking 'Login Here' button");
        landingPage.clickLoginHereLink();
        Thread.sleep(5000);
        driver.navigate().back();
        Thread.sleep(2000);
        addLog(Status.PASS, "Navigated back from Login page");

        // 5. Language Testing
        addLog(Status.INFO, "Language Testing placeholder");
        String[][] languages = {
                {"pl", "Witamy w SimpleTherapy"},
                {"it", "Benvenuto su SimpleTherapy"}
        };

        for (String[] data : languages) {

            String code = data[0];
            String expectedHeading = data[1];

            landingPage.selectLanguageByCode(code);

            Thread.sleep(3000);

            String actualHeading = landingPage.getHeading();
            System.out.println("Selected: " + code + " | Heading: " + actualHeading);

            Assert.assertEquals(actualHeading, expectedHeading,
                    "Heading translation mismatch for: " + code);
        }

        addLog(Status.INFO, "Switching language back to English");
        landingPage.selectLanguageByCode("en");
        Thread.sleep(2000);

        // 6. Need Help Popup
        addLog(Status.INFO, "Opening Need Help popup");
        landingPage.clickNeedHelpBtn();
        Thread.sleep(5000);
        Assert.assertEquals(landingPage.getNeedHelpPopUpHeading(), customerConfig.getPopupHeadingFromExcel(), "Need Help popup heading mismatch");
        landingPage.closePopup();
        addLog(Status.PASS, "Need Help popup validated and closed");

        // 7. Member Login Redirection
        addLog(Status.INFO, "Clicking Member Login");
        landingPage.clickMemberLoginLink();
        Thread.sleep(3000);
        Assert.assertTrue(landingPage.isEmailAddressInputDisplayed(), "Login page did not load");
        addLog(Status.PASS, "Successfully redirected to Member Login Page");
        addLog(Status.INFO, "=== Landing Page Validation Test Completed Successfully ===");
    }
}
