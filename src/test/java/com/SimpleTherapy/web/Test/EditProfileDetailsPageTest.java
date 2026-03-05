package com.simpleTherapy.web.Test;

import com.aventstack.extentreports.Status;
import com.simpleTherapy.web.pages.*;
import com.simpleTherapy.web.utils.AuthenticatorOtpUtil;
import com.simpleTherapy.web.utils.ExcelReader;
import com.simpleTherapy.web.utils.PhoneUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EditProfileDetailsPageTest extends BaseClass {

    LandingPage landingPage;
    LoginPage loginPage;
    SignupPasswordPage signupPasswordPage;
    DashboardPage dashboardPage;
    EditProfileDetailsPage editProfileDetailsPage;
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
        editProfileDetailsPage = new EditProfileDetailsPage();
        profileDetailsPage = new ProfileDetailsPage();

        excel = new ExcelReader(Constants.SimpleTherapy_TestData);
    }

    @Test(description = "Edit and validate My Profile details")
    public void editAndValidateProfileDetails() {

        /* ---------- LOGIN ---------- */
        landingPage.clickMemberLoginLink();

        String email = excel.getCellData("OTP_Data", "Email", 2);
        String secretKey = excel.getCellData("OTP_Data", "OtpSecret", 2);

        loginPage.enterEmail(email);
        loginPage.clickContinue();

        signupPasswordPage.enterPassword(customerConfig.getSignUpPasswordFromExcel());
        signupPasswordPage.clickContinueBtn();

        loginPage.enterOtp(AuthenticatorOtpUtil.generateOtp(secretKey));
        loginPage.clickContinue();

        Assert.assertTrue(dashboardPage.isDashboardDisplayed());

        /* ---------- NAVIGATION ---------- */
        dashboardPage.clickProfileIcon();
        dashboardPage.clickProfileDetailsOption();

        /* ---------- UPDATED DATA FROM EXCEL ---------- */
        String newFirstName = excel.getCellData("OTP_Data", "Edit_FirstName", 2);
        String newLastName  = excel.getCellData("OTP_Data", "Edit_LastName", 2);
        String newAddress   = excel.getCellData("OTP_Data", "Edit_Address1", 2);
        String newCity      = excel.getCellData("OTP_Data", "Edit_City", 2);
        String newPhone     = excel.getCellData("OTP_Data", "Edit_Phone", 2);
        String newHeight    = excel.getCellData("OTP_Data", "Height_Updated", 2);
        String newWeight    = excel.getCellData("OTP_Data", "Weight_Updated", 2);

        /* ---------- EDIT ---------- */
        editProfileDetailsPage.editFirstName(newFirstName);
        editProfileDetailsPage.editLastName(newLastName);
        editProfileDetailsPage.editAddress(newAddress);
        editProfileDetailsPage.editCity(newCity);
        editProfileDetailsPage.editPhone(newPhone);
        editProfileDetailsPage.selectHeight(newHeight);
        editProfileDetailsPage.selectWeight(newWeight);
        editProfileDetailsPage.clickSave();

        /* ---------- RELOAD PROFILE ---------- */
        dashboardPage.clickProfileIcon();
        dashboardPage.clickProfileDetailsOption();

        /* ---------- VALIDATION ---------- */
        Assert.assertEquals(profileDetailsPage.getFirstName(), newFirstName);
        Assert.assertEquals(profileDetailsPage.getLastName(), newLastName);
        Assert.assertEquals(profileDetailsPage.getAddress(), newAddress);
        Assert.assertEquals(profileDetailsPage.getCity(), newCity);
        Assert.assertEquals(
                profileDetailsPage.getPhone(),
                PhoneUtil.normalize(newPhone)
        );

        addLog(Status.PASS, "Profile edited and validated successfully");
    }
}
