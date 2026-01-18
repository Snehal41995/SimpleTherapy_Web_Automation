package com.simpleTherapy.web.Test;

import com.aventstack.extentreports.Status;
import com.simpleTherapy.web.pages.*;
import com.simpleTherapy.web.utils.AuthenticatorOtpUtil;
import com.simpleTherapy.web.utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseClass {

    LandingPage landingPage;
    SignupPasswordPage signupPasswordPage;
    DashboardPage dashboardPage;
    ProfileDetailsPage profileDetailsPage;
    LoginPage loginPage;
    CustomerConfiguration customerConfig;
    ExcelReader excel;

    @BeforeMethod
    public void setUp() {
        initialization();

        customerConfig = new CustomerConfiguration("Dev2", 2);
        landingPage = new LandingPage();
        signupPasswordPage = new SignupPasswordPage();
        dashboardPage = new DashboardPage();
        profileDetailsPage = new ProfileDetailsPage();
        loginPage = new LoginPage();
        excel = new ExcelReader(Constants.SimpleTherapy_TestData);
    }

    @Test(description = "Login using Email, Password, OTP and validate dashboard & profile email")
    public void verifyLoginWithOtpAndDashboardValidation() throws Exception {

        // ===== MEMBER LOGIN =====
        addLog(Status.INFO, "Clicking Member Login");
        landingPage.clickMemberLoginLink();

        // ===== READ DATA =====
        String email = excel.getCellData("OTP_Data", "Email", 2).trim();
        String secretKey = excel.getCellData("OTP_Data", "OtpSecret", 2).trim();

        addLog(Status.INFO, "Email fetched from Excel: " + email);

        // ===== EMAIL STEP =====
        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                "Login page not displayed");

        loginPage.enterEmail(email);
        addLog(Status.PASS, "Email entered");

        loginPage.clickContinue();
        addLog(Status.INFO, "Continue clicked after email");

        // ===== PASSWORD STEP =====
        addLog(Status.INFO, "Entering Password");
        signupPasswordPage.enterPassword(customerConfig.getSignUpPasswordFromExcel());
        addLog(Status.PASS, "Password entered");

        signupPasswordPage.clickContinueBtn();
        addLog(Status.INFO, "Continue clicked after password");

        // ===== OTP STEP =====
        String otp = AuthenticatorOtpUtil.generateOtp(secretKey);
        addLog(Status.INFO, "OTP generated successfully");

        loginPage.enterOtp(otp);
        addLog(Status.PASS, "OTP entered");

        loginPage.clickContinue();
        addLog(Status.PASS, "OTP verified successfully");

        // ===== DASHBOARD VALIDATION =====
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(),
                "Dashboard not displayed after login");
        addLog(Status.PASS, "Dashboard loaded successfully");

        Assert.assertTrue(dashboardPage.isProfileIconDisplayed(),
                "Profile icon missing");

        // ===== PROFILE EMAIL VALIDATION =====
        dashboardPage.clickProfileIcon();
        dashboardPage.clickProfileDetailsOption();

        String actualEmail = profileDetailsPage.getEmail().trim();
        addLog(Status.INFO, "Actual Email from Profile: " + actualEmail);
        Assert.assertEquals(actualEmail, email, "Profile email does not match login email");

        addLog(Status.PASS, "Profile email validated successfully");
    }
}