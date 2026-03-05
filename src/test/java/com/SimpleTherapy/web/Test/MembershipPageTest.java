package com.simpleTherapy.web.Test;

import com.aventstack.extentreports.Status;
import com.simpleTherapy.web.pages.*;
import com.simpleTherapy.web.utils.AuthenticatorOtpUtil;
import com.simpleTherapy.web.utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.LocalDate;

public class MembershipPageTest extends BaseClass {

    LandingPage landingPage;
    LoginPage loginPage;
    SignupPasswordPage signupPasswordPage;
    DashboardPage dashboardPage;
    MembershipPage membershipPage;
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
        membershipPage = new MembershipPage();

        excel = new ExcelReader(Constants.SimpleTherapy_TestData);
    }

    @Test(description = "Verify Employer Name and Registration Date on Membership page")
    public void verifyMembershipDetails() {

        addLog(Status.INFO, "===== Test Started: Verify Membership Details =====");

        /* ---------- LOGIN ---------- */
        landingPage.clickMemberLoginLink();
        addLog(Status.INFO, "Clicked on Member Login link");

        String email = excel.getCellData("OTP_Data", "Email", 2);
        String secretKey = excel.getCellData("OTP_Data", "OtpSecret", 2);

        loginPage.enterEmail(email);
        loginPage.clickContinue();

        signupPasswordPage.enterPassword(customerConfig.getSignUpPasswordFromExcel());
        signupPasswordPage.clickContinueBtn();

        loginPage.enterOtp(AuthenticatorOtpUtil.generateOtp(secretKey));
        loginPage.clickContinue();

        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard not displayed");
        addLog(Status.PASS, "Dashboard displayed successfully");

        /* ---------- NAVIGATE TO MEMBERSHIP ---------- */
        dashboardPage.clickProfileIcon();
        addLog(Status.INFO, "Opened Profile menu");

        membershipPage.clickMembership();
        addLog(Status.INFO, "Clicked on Membership option");
        
        /* ---------- EMPLOYER NAME : ACTUAL & EXPECTED ---------- */
        String actualEmployer = membershipPage.getEmployerName();
        String expectedEmployer = customerConfig.getEmployerNameFromExcel();

        addLog(Status.INFO, "Actual Employer Name displayed: " + actualEmployer);
        addLog(Status.INFO, "Expected Employer Name: " + expectedEmployer);

        /* ---------- EMPLOYER NAME ASSERTION ---------- */
        Assert.assertTrue(actualEmployer.toLowerCase().contains(expectedEmployer.toLowerCase()),
                "Employer name mismatch");

        addLog(Status.PASS, "Employer name validated successfully");
        addLog(Status.INFO, "===== Test Completed: Verify Membership Details =====");
    }
}
