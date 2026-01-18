//package com.simpleTherapy.web.Test;
//
//import com.aventstack.extentreports.Status;
//import com.simpleTherapy.web.pages.*;
//import com.simpleTherapy.web.utils.ExcelReader;
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//public class EditProfileDetailsPageTest extends BaseClass {
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
//    public void editAndValidateProfileDetails() throws InterruptedException {
//
//        // 1. Login
//        //loginPage.login(user, password);
//
//        addLog(Status.INFO, "Navigating to Profile Details page");
//        dashboardPage.clickProfileDetailsOption();
//        Thread.sleep(3000);
//
//        ExcelReader excel = new ExcelReader(Constants.SimpleTherapy_TestData);
//        // 4. Edit fields (NEW excel columns)
//        profileDetailsPage.editFirstName(excel.get("FirstName_Updated"));
//        profileDetailsPage.editLastName(excel.get("LastName_Updated"));
//        profileDetailsPage.editCity(excel.get("City_Updated"));
//
//        // 5. Save
//        profileDetailsPage.clickSave();
//
//        // 6. Validate saved data
//        Assert.assertEquals(profileDetailsPage.getFirstName(), excel.get("FirstName_Updated"));
//    }
//
//}
