//package com.simpleTherapy.web.Test;
//
//import com.simpleTherapy.web.pages.BaseClass;
//import com.simpleTherapy.web.pages.CustomerConfiguration;
//import com.simpleTherapy.web.pages.DashboardPage;
//import com.simpleTherapy.web.pages.ProfileDetailsPage;
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//public class ProfilePicturePersistenceTest extends BaseClass {
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
//    public void verifyProfilePictureUsingSrcAfterRelogin() {
//
//        // Login
//        loginPage.login("testuser@email.com", "Password@123");
//
//        // Navigate to Membership
//        dashboardPage.clickProfile();
//        dashboardPage.clickMembership();
//
//        // Validate image after upload
//        String srcBeforeLogout = membershipPage.getProfileImageSrc();
//
//        Assert.assertTrue(
//                srcBeforeLogout != null && srcBeforeLogout.contains("data:image"),
//                "Profile image src is invalid before logout"
//        );
//
//        // Logout
//        headerPage.logout();
//        Assert.assertTrue(headerPage.isLoginButtonDisplayed(), "Logout failed");
//
//        // Login again
//        loginPage.login("testuser@email.com", "Password@123");
//
//        // Navigate again
//        dashboardPage.clickProfile();
//        dashboardPage.clickMembership();
//
//        // Validate image persisted
//        String srcAfterLogin = membershipPage.getProfileImageSrc();
//
//        Assert.assertTrue(
//                srcAfterLogin != null && srcAfterLogin.contains("data:image"),
//                "Profile image not persisted after logout & login"
//        );
//
//        // (Optional) Strong validation
//        Assert.assertEquals(
//                srcAfterLogin,
//                srcBeforeLogout,
//                "Profile image src changed after relogin"
//        );
//    }
//
//}
