package com.simpleTherapy.web.Test;

import com.aventstack.extentreports.Status;
import com.simpleTherapy.web.pages.*;
import com.simpleTherapy.web.utils.AuthenticatorOtpUtil;
import com.simpleTherapy.web.utils.ExcelReader;
import com.simpleTherapy.web.utils.PhoneUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProfileDetailsPageTest extends BaseClass {

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

    @Test(description = "My Profile Details Validation")
    public void validateMyProfileDetails() throws Exception {

        // ===== LOGIN =====
        landingPage.clickMemberLoginLink();

        String email = excel.getCellData("OTP_Data", "Email", 2).trim();
        String secretKey = excel.getCellData("OTP_Data", "OtpSecret", 2).trim();

        Assert.assertTrue(loginPage.isLoginPageDisplayed());

        loginPage.enterEmail(email);
        loginPage.clickContinue();

        signupPasswordPage.enterPassword(customerConfig.getSignUpPasswordFromExcel());
        signupPasswordPage.clickContinueBtn();

        String otp = AuthenticatorOtpUtil.generateOtp(secretKey);
        loginPage.enterOtp(otp);
        loginPage.clickContinue();

        Assert.assertTrue(dashboardPage.isDashboardDisplayed());

        // ===== NAVIGATION =====
        dashboardPage.clickProfileIcon();
        dashboardPage.clickProfileDetailsOption();

        // ===== EXPECTED DATA =====
        String expFirstName = excel.getCellData("OTP_Data", "FirstName", 2).trim();
        String expLastName  = excel.getCellData("OTP_Data", "LastName", 2).trim();
        String expEmail     = excel.getCellData("OTP_Data", "Email", 2).trim();
        String expPhone     = PhoneUtil.normalize(
                excel.getCellData("OTP_Data", "PhoneNumber", 2));
        String expAddress   = excel.getCellData("OTP_Data", "Address1", 2).trim();
        String expCountry   = excel.getCellData("OTP_Data", "Country", 2).trim();
        String expCity      = excel.getCellData("OTP_Data", "City", 2).trim();
        String expState     = excel.getCellData("OTP_Data", "State", 2).trim();
        String expDob       = excel.getCellData("OTP_Data", "DOB", 2).trim();

        // ===== VALIDATIONS =====
        Assert.assertEquals(profileDetailsPage.getFirstName(), expFirstName);
        Assert.assertEquals(profileDetailsPage.getLastName(), expLastName);
        Assert.assertEquals(profileDetailsPage.getEmail(), expEmail);
        Assert.assertEquals(profileDetailsPage.getPhone(), expPhone);
        Assert.assertEquals(profileDetailsPage.getAddress(), expAddress);
        Assert.assertEquals(profileDetailsPage.getCountry(), expCountry);
        Assert.assertEquals(profileDetailsPage.getCity(), expCity);
        Assert.assertEquals(profileDetailsPage.getState(), expState);
        Assert.assertEquals(profileDetailsPage.getDob(), expDob);

        addLog(Status.PASS, "All profile details validated successfully");
    }
}
