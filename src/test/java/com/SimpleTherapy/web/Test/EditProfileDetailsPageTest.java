package com.simpleTherapy.web.Test;

import com.aventstack.extentreports.Status;
import com.simpleTherapy.web.pages.*;
import com.simpleTherapy.web.utils.AuthenticatorOtpUtil;
import com.simpleTherapy.web.utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EditProfileDetailsPageTest extends BaseClass {

    LandingPage landingPage;
    SignupPasswordPage signupPasswordPage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ProfileDetailsPage profileDetailsPage;
    ExcelReader excel;
    CustomerConfiguration customerConfig;

    @BeforeMethod
    public void setUp() throws Exception {
        initialization();
        customerConfig = new CustomerConfiguration("Dev2", 2);

        landingPage = new LandingPage();
        signupPasswordPage = new SignupPasswordPage();
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        profileDetailsPage = new ProfileDetailsPage();

        excel = new ExcelReader(Constants.SimpleTherapy_TestData);
    }

    @Test(description = "Edit and Validate Profile Details")
    public void editAndValidateProfileDetails() throws Exception {

        // ===== LOGIN =====
        landingPage.clickMemberLoginLink();

        String email = excel.getCellData("OTP_Data", "Email", 2);
        String secretKey = excel.getCellData("OTP_Data", "OtpSecret", 2);

        loginPage.enterEmail(email);
        loginPage.clickContinue();

        signupPasswordPage.enterPassword(customerConfig.getSignUpPasswordFromExcel());
        signupPasswordPage.clickContinueBtn();

        String otp = AuthenticatorOtpUtil.generateOtp(secretKey);
        loginPage.enterOtp(otp);
        loginPage.clickContinue();

        Assert.assertTrue(dashboardPage.isDashboardDisplayed());

        // ===== NAVIGATE =====
        dashboardPage.clickProfileIcon();
        dashboardPage.clickProfileDetailsOption();

        // ===== READ UPDATED DATA FROM EXCEL =====
        String updFirstName = excel.getCellData("OTP_Data", "FirstName_Updated", 2);
        String updLastName  = excel.getCellData("OTP_Data", "LastName_Updated", 2);
        String updCity      = excel.getCellData("OTP_Data", "City_Updated", 2);
        String updHeight = excel.getCellData("OTP_Data", "Height_Updated", 2);
        String updWeight = excel.getCellData("OTP_Data", "Weight_Updated", 2);

        profileDetailsPage.selectHeight(updHeight);
        profileDetailsPage.selectWeight(updWeight);

        // ===== EDIT =====
        addLog(Status.INFO, "Editing profile details");

        profileDetailsPage.editFirstName(updFirstName);
        profileDetailsPage.editLastName(updLastName);
        profileDetailsPage.editCity(updCity);

        profileDetailsPage.clickSave();

        // ===== VALIDATE UPDATED DATA =====
        addLog(Status.INFO, "Validating updated profile details");

        Assert.assertEquals(profileDetailsPage.getFirstName(), updFirstName);
        Assert.assertEquals(profileDetailsPage.getLastName(), updLastName);
        Assert.assertEquals(profileDetailsPage.getCity(), updCity);

        addLog(Status.PASS, "Profile details updated and validated successfully");
    }
}
