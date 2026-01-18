package com.simpleTherapy.web.Test;

import com.simpleTherapy.web.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProfilePicturePersistenceTest extends BaseClass {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ProfileDetailsPage profileDetailsPage;
    MembershipPage membershipPage;
    CustomerConfiguration customerConfig;

    @BeforeMethod
    public void setUp() throws Exception {
        initialization();
        //customerConfig = new CustomerConfiguration("Dev2");
        customerConfig = new CustomerConfiguration("Dev2", 2);
        //customerConfig = new CustomerConfiguration("Dev2", 3);

        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        profileDetailsPage = new ProfileDetailsPage();
        membershipPage = new MembershipPage();
    }

    @Test
    public void verifyProfilePicturePersistsAfterLogoutAndLogin() {

        // Login
        loginPage.isLoginPageDisplayed("testuser@email.com", "Password@123");

        // Navigate to Membership
        dashboardPage.clickProfile();
        dashboardPage.clickMembership();

        // Validate image before logout
        String srcBeforeLogout = membershipPage.getProfileImageSrc();

        Assert.assertTrue(
                srcBeforeLogout != null && srcBeforeLogout.contains("data:image"),
                "Profile image src is invalid before logout"
        );

        // Logout
        headerPage.logout();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("login"),
                "Logout failed - user not redirected to login"
        );

        // Login again
        loginPage.login("testuser@email.com", "Password@123");

        // Navigate again
        dashboardPage.clickProfile();
        dashboardPage.clickMembership();

        // Validate image after relogin
        String srcAfterLogin = membershipPage.getProfileImageSrc();

        Assert.assertTrue(
                srcAfterLogin != null && srcAfterLogin.contains("data:image"),
                "Profile image not persisted after logout & login"
        );

        // Strong persistence validation
        Assert.assertEquals(
                srcAfterLogin,
                srcBeforeLogout,
                "Profile image src changed after relogin"
        );
    }
}