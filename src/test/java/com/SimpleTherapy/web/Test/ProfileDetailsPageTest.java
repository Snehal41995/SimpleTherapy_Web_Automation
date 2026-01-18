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
    SignupPasswordPage signupPasswordPage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ProfileDetailsPage profileDetailsPage;
    ExcelReader excel;
    CustomerConfiguration customerConfig;

    @BeforeMethod
    public void setUp() throws Exception {
        initialization();
        //customerConfig = new CustomerConfiguration("Dev2");
        customerConfig = new CustomerConfiguration("Dev2", 2);

        landingPage = new LandingPage();
        signupPasswordPage = new SignupPasswordPage();
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        profileDetailsPage = new ProfileDetailsPage();
        excel = new ExcelReader(Constants.SimpleTherapy_TestData);
    }

    @Test(description = "My Profile Details Validation")
    public void validateMyProfileDetails() throws Exception {
        // ===== MEMBER LOGIN =====
        addLog(Status.INFO, "Clicking Member Login");
        landingPage.clickMemberLoginLink();

        String email = excel.getCellData("OTP_Data", "Email", 2).trim();
        String secretKey = excel.getCellData("OTP_Data", "OtpSecret", 2).trim();
        addLog(Status.INFO, "Email fetched from Excel: " + email);

        // ===== EMAIL STEP =====
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page not displayed");

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
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard not displayed after login");
        addLog(Status.PASS, "Dashboard loaded successfully");

        Assert.assertTrue(dashboardPage.isProfileIconDisplayed(), "Profile icon missing");

        dashboardPage.clickProfileIcon();

        // ---- NAVIGATE TO PROFILE DETAILS ----
        addLog(Status.INFO, "Navigating to Profile Details page");
        dashboardPage.clickProfileDetailsOption();
        dashboardPage.clickProfileDetailsOption();

//        String actualEmail = profileDetailsPage.getEmail().trim();
//        addLog(Status.INFO, "Actual Email from Profile: " + actualEmail);
//        Assert.assertEquals(actualEmail, email, "Profile email does not match login email");
//
//        addLog(Status.PASS, "Profile email validated successfully");
        // ===== READ EXPECTED DATA FROM EXCEL ====

        String expFirstName = excel.getCellData("OTP_Data", "FirstName", 2).trim();
        String expLastName = excel.getCellData("OTP_Data", "LastName", 2).trim();
        String expEmail = excel.getCellData("OTP_Data", "Email", 2).trim();
        //String expPhone = excel.getCellData("OTP_Data", "Phone", 2).replaceAll("[^0-9]", "");
        String expAddress1 = excel.getCellData("OTP_Data", "Address1", 2).trim();
        String expCountry = excel.getCellData("OTP_Data", "Country", 2).trim();
        String expCity = excel.getCellData("OTP_Data", "City", 2).trim();
        String expState = excel.getCellData("OTP_Data", "State", 2).trim();
        String expDob = excel.getCellData("OTP_Data", "DOB", 2).trim();

        // ===== PROFILE DETAILS VALIDATION =====

        addLog(Status.INFO, "Validating First Name");
        Assert.assertEquals(profileDetailsPage.getFirstName(), expFirstName,
                "First name mismatch");
        addLog(Status.PASS, "First Name validated successfully");

        addLog(Status.INFO, "Validating Last Name");
        Assert.assertEquals(profileDetailsPage.getLastName(), expLastName,
                "Last name mismatch");
        addLog(Status.PASS, "Last Name validated successfully");

        addLog(Status.INFO, "Validating Email Address");
        Assert.assertEquals(profileDetailsPage.getEmail(), expEmail,
                "Email mismatch");
        addLog(Status.PASS, "Email validated successfully");

        addLog(Status.INFO, "Validating Phone Number");
        String expPhone = PhoneUtil.normalize(excel.getCellData("OTP_Data", "PhoneNumber", 2));
        String actualPhone = profileDetailsPage.getNormalizedPhoneNumber();
        Assert.assertEquals(actualPhone, expPhone, "Phone number mismatch");
        addLog(Status.PASS, "Phone number validated successfully");

        addLog(Status.INFO, "Validating Address");
        Assert.assertEquals(profileDetailsPage.getAddress1(), expAddress1,
                "Address mismatch");
        addLog(Status.PASS, "Address validated successfully");

        addLog(Status.INFO, "Validating Country");
        Assert.assertEquals(profileDetailsPage.getCountry(), expCountry,
                "Country mismatch");
        addLog(Status.PASS, "Country validated successfully");

        addLog(Status.INFO, "Validating City");
        Assert.assertEquals(profileDetailsPage.getCity(), expCity,
                "City mismatch");
        addLog(Status.PASS, "City validated successfully");

        addLog(Status.INFO, "Validating State");
        Assert.assertEquals(profileDetailsPage.getState(), expState,
                "State mismatch");
        addLog(Status.PASS, "State validated successfully");

        addLog(Status.INFO, "Validating Date of Birth");
        Assert.assertEquals(profileDetailsPage.getDobNormalized(), expDob, "DOB mismatch");
        addLog(Status.PASS, "DOB validated successfully");

        addLog(Status.PASS, "All profile details validated successfully");
    }
}
