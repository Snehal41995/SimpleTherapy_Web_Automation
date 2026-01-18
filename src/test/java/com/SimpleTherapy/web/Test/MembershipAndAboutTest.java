//package com.simpleTherapy.web.Test;
//
//import com.simpleTherapy.web.pages.*;
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//public class MembershipAndAboutTest extends BaseClass {
//
//    DashboardPage dashboardPage;
//    ProfileDetailsPage profileDetailsPage;
//    CustomerConfiguration customerConfig;
//
//    @BeforeMethod
//    public void setUp() throws Exception {
//        initialization();
//        //customerConfig = new CustomerConfiguration("Dev2");
//        customerConfig = new CustomerConfiguration("Dev2", 2);
//        //customerConfig = new CustomerConfiguration("Dev2", 3);
//
//        dashboardPage = new DashboardPage();
//        profileDetailsPage = new ProfileDetailsPage();
//    }
//
//    @Test
//    public void verifyMembershipAndAboutTabs() {
//
//        LoginPage loginPage = new LoginPage();
//        DashboardPage dashboardPage = new DashboardPage();
//        MembershipPage membershipPage = new MembershipPage();
//        AboutPage aboutPage = new AboutPage();
//
//        // ---------- Test Data (from registration) ----------
//        String expectedEmployerName = "SimpleTherapy Corp";
//        String expectedRegistrationDate = "11 Jan 2026";
//
//        // ---------- Login ----------
//        loginPage.login("testuser@email.com", "Password@123");
//
//        // ---------- Navigate to Membership ----------
//        dashboardPage.clickProfile();
//        membershipPage.openMembershipTab();
//
//        // ---------- Validate Employer Name ----------
//        String actualEmployerName = membershipPage.getEmployerName();
//        Assert.assertEquals(
//                actualEmployerName,
//                expectedEmployerName,
//                "Employer name mismatch in Membership tab"
//        );
//
//        // ---------- Validate Membership Date ----------
//        String actualMembershipDate = membershipPage.getMembershipDate();
//        Assert.assertEquals(
//                actualMembershipDate,
//                expectedRegistrationDate,
//                "Membership date does not match registration date"
//        );
//
//        // ---------- Navigate to About ----------
//        aboutPage.openAboutTab();
//
//        // ---------- Open Privacy Policy ----------
//        aboutPage.openPrivacyPolicy();
//
//        // ---------- Validate Privacy Policy Loaded ----------
//        Assert.assertTrue(
//                aboutPage.isPrivacyPolicyLoaded(),
//                "Privacy Policy page did not load correctly"
//        );
//    }
//}
