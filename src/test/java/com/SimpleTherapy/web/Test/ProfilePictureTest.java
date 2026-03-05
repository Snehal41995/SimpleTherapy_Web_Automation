package com.simpleTherapy.web.Test;

import com.aventstack.extentreports.Status;
import com.simpleTherapy.web.pages.*;
import com.simpleTherapy.web.utils.AuthenticatorOtpUtil;
import com.simpleTherapy.web.utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProfilePictureTest extends BaseClass {

    LandingPage landingPage;
    LoginPage loginPage;
    SignupPasswordPage signupPasswordPage;
    DashboardPage dashboardPage;
    ProfileDetailsPage profileDetailsPage;
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
        excel = new ExcelReader(Constants.SimpleTherapy_TestData);
    }

    @Test(description = "Verify profile picture persists after logout and login")
    public void verifyProfilePictureAfterLogoutLogin() throws InterruptedException {

        addLog(Status.INFO, "===== Test Started: Profile Picture Persistence =====");

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

        /* ---------- PROFILE NAVIGATION ---------- */
        addLog(Status.INFO, "Navigating to Profile Details page");
        dashboardPage.clickProfileIcon();
        dashboardPage.clickProfileDetailsOption();

        /* ---------- UPLOAD PROFILE PICTURE ---------- */
        addLog(Status.INFO, "Uploading profile picture");
        profileDetailsPage.uploadProfilePicture("src/test/resources/images/profile.jpg");

        Assert.assertTrue(profileDetailsPage.isProfileImageDisplayed(),
                "Profile image not displayed after upload");
        addLog(Status.PASS, "Profile image displayed after upload");

        String imageSrcBeforeLogout = profileDetailsPage.getProfileImageSrc();

        /* ---------- LOGOUT ---------- */
        addLog(Status.INFO, "Starting logout process");
        profileDetailsPage.clickBackIcon();

        dashboardPage.clickProfileIcon();
        addLog(Status.INFO, "Profile icon clicked");

        profileDetailsPage.clickLogoutBtn();
        addLog(Status.INFO, "Logout button clicked");

        profileDetailsPage.confirmLogoutBtn();
        addLog(Status.INFO, "Logout confirmed");

        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "User not logged out");
        addLog(Status.PASS, "User logged out successfully");

        /* ---------- LOGIN AGAIN ---------- */
        addLog(Status.INFO, "Logging in again");

        loginPage.enterEmail(email);
        loginPage.clickContinue();

        signupPasswordPage.enterPassword(customerConfig.getSignUpPasswordFromExcel());
        signupPasswordPage.clickContinueBtn();

        loginPage.enterOtp(AuthenticatorOtpUtil.generateOtp(secretKey));
        loginPage.clickContinue();

        Thread.sleep(5000);

        Assert.assertTrue(dashboardPage.isDashboardDisplayed(),
                "Dashboard not displayed after re-login");
        addLog(Status.PASS, "Dashboard displayed after re-login");

        /* ---------- VERIFY IMAGE PERSISTENCE ---------- */
        addLog(Status.INFO, "Navigating to Profile Details page after re-login");
        dashboardPage.clickProfileIcon();
        dashboardPage.clickProfileDetailsOption();

        Assert.assertTrue(profileDetailsPage.isProfileImageDisplayed(),
                "Profile image not displayed after re-login");
        addLog(Status.PASS, "Profile image displayed after re-login");

        String imageSrcAfterLogin = profileDetailsPage.getProfileImageSrc();

        Assert.assertEquals(imageSrcAfterLogin, imageSrcBeforeLogout,
                "Profile image did not persist after logout/login");

        addLog(Status.PASS, "Profile image persisted after logout and login");
    }
}
