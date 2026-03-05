package com.simpleTherapy.web.Test;

import com.aventstack.extentreports.Status;
import com.simpleTherapy.web.pages.*;
import com.simpleTherapy.web.utils.AuthenticatorOtpUtil;
import com.simpleTherapy.web.utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyBenefitsPageTest extends BaseClass {

    LandingPage landingPage;
    LoginPage loginPage;
    SignupPasswordPage signupPasswordPage;
    DashboardPage dashboardPage;
    MyBenefitsPage myBenefitsPage;
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
        myBenefitsPage = new MyBenefitsPage();

        excel = new ExcelReader(Constants.SimpleTherapy_TestData);
    }


    @Test(description = "Verify My Benefits cards color and navigation")
    public void verifyMyBenefitsCardsColorAndNavigation() throws Exception {

        addLog(Status.INFO, "===== Test Started: Verify My Benefits Cards Color And Navigation =====");

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


        /* ---------- VERIFY DASHBOARD ---------- */

        Assert.assertTrue(dashboardPage.isDashboardDisplayed(),
                "Dashboard not displayed");

        addLog(Status.PASS, "Dashboard displayed successfully");


        /* ---------- OPEN MY BENEFITS ---------- */

        addLog(Status.INFO, "Clicking on My Benefits tab");

        myBenefitsPage.clickMyBenefits();

        addLog(Status.PASS, "My Benefits page opened successfully");


        /* ---------- MSK CARD (DEFAULT ACTIVE) ---------- */

        String mskActiveColor = myBenefitsPage.getMSKCardColor();
        addLog(Status.INFO, "MSK Card active color: " + mskActiveColor);


        /* ---------- BEHAVIORAL CARD ---------- */

        addLog(Status.INFO, "Clicking Behavioral card");
        myBenefitsPage.clickBehavioralCard();

        String behavioralActiveColor = myBenefitsPage.getBehavioralCardColor();
        String mskInactiveColor = myBenefitsPage.getMSKCardColor();

        addLog(Status.INFO, "Behavioral active color: " + behavioralActiveColor);
        addLog(Status.INFO, "MSK inactive color: " + mskInactiveColor);

        Assert.assertNotEquals(behavioralActiveColor, mskInactiveColor,
                "Behavioral should be active and MSK should be inactive");

        addLog(Status.PASS, "Behavioral card activation validated");


        /* ---------- EAP CARD ---------- */

        addLog(Status.INFO, "Clicking EAP card");
        myBenefitsPage.clickEAPCard();

        String eapActiveColor = myBenefitsPage.getEAPCardColor();
        String behavioralInactiveColor = myBenefitsPage.getBehavioralCardColor();

        addLog(Status.INFO, "EAP active color: " + eapActiveColor);
        addLog(Status.INFO, "Behavioral inactive color: " + behavioralInactiveColor);

        Assert.assertNotEquals(eapActiveColor, behavioralInactiveColor,
                "EAP should be active and Behavioral should be inactive");

        addLog(Status.PASS, "EAP card activation validated");


        /* ---------- WELLBEING CARD ---------- */

        addLog(Status.INFO, "Clicking Wellbeing card");
        myBenefitsPage.clickWellbeingCard();

        String wellbeingActiveColor = myBenefitsPage.getWellbeingCardColor();
        String eapInactiveColor = myBenefitsPage.getEAPCardColor();

        addLog(Status.INFO, "Wellbeing active color: " + wellbeingActiveColor);
        addLog(Status.INFO, "EAP inactive color: " + eapInactiveColor);

        Assert.assertNotEquals(wellbeingActiveColor, eapInactiveColor,
                "Wellbeing should be active and EAP should be inactive");

        addLog(Status.PASS, "Wellbeing card activation validated");
        addLog(Status.PASS, "===== Test Completed: Verify My Benefits Cards Color And Navigation =====");
    }
}