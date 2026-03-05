package com.simpleTherapy.web.Test;

import com.aventstack.extentreports.Status;
import com.simpleTherapy.web.pages.*;
import com.simpleTherapy.web.utils.AuthenticatorOtpUtil;
import com.simpleTherapy.web.utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AboutPageTest extends BaseClass {

    LandingPage landingPage;
    LoginPage loginPage;
    SignupPasswordPage signupPasswordPage;
    DashboardPage dashboardPage;
    ProfileDetailsPage profileDetailsPage;
    AboutPage aboutPage;
    ExcelReader excel;
    CustomerConfiguration customerConfig;

    @BeforeMethod
    public void setUp() throws Exception {

        initialization();
        customerConfig = new CustomerConfiguration("Dev2", 2);
        landingPage = new LandingPage();
        loginPage = new LoginPage();
        signupPasswordPage = new SignupPasswordPage();
        dashboardPage = new DashboardPage();
        profileDetailsPage = new ProfileDetailsPage();
        aboutPage = new AboutPage();
        excel = new ExcelReader(Constants.SimpleTherapy_TestData);
    }

    @Test(description = "Verify Privacy Policy page opens correctly from About page")
    public void verifyPrivacyPolicyFromAboutPage() {

        addLog(Status.INFO, "===== Test Started: Verify Privacy Policy from About Page =====");

        /* ---------- LOGIN ---------- */
        addLog(Status.INFO, "Clicking on Member Login link");
        landingPage.clickMemberLoginLink();

        String email = excel.getCellData("OTP_Data", "Email", 2);
        String secretKey = excel.getCellData("OTP_Data", "OtpSecret", 2);
        addLog(Status.INFO, "Using email: " + email);

        loginPage.enterEmail(email);
        addLog(Status.INFO, "Entered email");

        loginPage.clickContinue();
        addLog(Status.INFO, "Clicked Continue on email screen");

        signupPasswordPage.enterPassword(customerConfig.getSignUpPasswordFromExcel());
        addLog(Status.INFO, "Entered password");

        signupPasswordPage.clickContinueBtn();
        addLog(Status.INFO, "Clicked Continue on password screen");

        loginPage.enterOtp(AuthenticatorOtpUtil.generateOtp(secretKey));
        addLog(Status.INFO, "Entered OTP");

        loginPage.clickContinue();
        addLog(Status.INFO, "Clicked Continue after OTP");

        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard not displayed");
        addLog(Status.PASS, "Dashboard displayed successfully");

        /* ---------- NAVIGATE TO ABOUT ---------- */
        addLog(Status.INFO, "Opening Profile menu");
        dashboardPage.clickProfileIcon();

        addLog(Status.INFO, "Clicking on About option");
        aboutPage.clickAboutMenu();

        addLog(Status.INFO, "About page opened successfully");

        /* ---------- PRIVACY POLICY ---------- */
        addLog(Status.INFO, "Clicking on Privacy Policy link");
        aboutPage.clickPrivacyPolicy();

        addLog(Status.INFO, "Verifying Privacy Policy page in new tab");
        Assert.assertTrue(aboutPage.isPrivacyPolicyPageDisplayed(),
                "Privacy Policy page did not load");

        addLog(Status.PASS, "Privacy Policy page loaded successfully");
        addLog(Status.INFO, "===== Test Completed: Verify Privacy Policy from About Page =====");
    }
}
