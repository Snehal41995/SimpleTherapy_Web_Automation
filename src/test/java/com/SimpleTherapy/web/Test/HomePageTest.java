//package com.SimpleTherapy.web.Test;
//
//import com.SimpleTherapy.web.pages.BaseClass;
//import com.SimpleTherapy.web.pages.Constants;
//import com.SimpleTherapy.web.pages.CustomerConfiguration;
//import org.openqa.selenium.WebElement;
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import com.SimpleTherapy.web.utils.ExcelReader;
//import com.SimpleTherapy.web.pages.HomePage;
//
//import java.util.List;
//
//public class HomePageTest extends BaseClass {
//    ExcelReader reader;
//    HomePage homePage;
//    CustomerConfiguration customerConfiguration;
//    String sheetName1 = "HomePage_Data";
//
//    @BeforeMethod
//    public void setUp() throws Exception {
//        initialization();
//        Thread.sleep(3000);
//        reader = new ExcelReader(Constants.SKILLSHUB_TESTDATA);
//        customerConfiguration = new CustomerConfiguration(sheetName1);
//        homePage = new HomePage();
//    }
//
//    @Test
//    public void verifyHomeScreen() throws Exception {
//        // Title check
//        String expectedTitleHeading = customerConfiguration.getTitleHeading();
//        String actualTitleHeading = homePage.getTitle();
//        System.out.println("The title heading is : " + actualTitleHeading);
//        Assert.assertEquals(actualTitleHeading, expectedTitleHeading, "Title name is not correct");
//        Thread.sleep(3000);
//
//        // Navigation links + headers check
//        List<WebElement> links = homePage.getNavigationLinks();
//        int totalRows = customerConfiguration.getRowCount();
//
//        for (int i = 2; i <= totalRows; i++) {
//            String expectedHeader = customerConfiguration.getHeaderName(i);
//
//            homePage.clickOnNavLinks(i - 2);
//
//            // Decide which header to fetch dynamically
//            String actualHeader = "";
//            switch (expectedHeader) {
//                case "Remote Learning":
//                    actualHeader = homePage.getRemoteLearningHeaderText();
//                    break;
//                case "Get Skills Hub App":
//                    actualHeader = homePage.getGetSkillsHubAppHeaderText();
//                    break;
//                case "Build your portfolio with the Skills Hub App":
//                    actualHeader = homePage.getBuildPortfolioHeaderText();
//                    break;
//                case "BUZZ Secure Medical Messenger App":
//                    actualHeader = homePage.getBuzzMessengerHeaderText();
//                    break;
//                case "Educators":
//                    actualHeader = homePage.getEducatorsHeaderText();
//                    break;
//                default:
//                    throw new RuntimeException("No matching header found for expected: " + expectedHeader);
//            }
//
//            System.out.println("Expected Header: " + expectedHeader + " | Actual Header: " + actualHeader);
//            Assert.assertEquals(actualHeader, expectedHeader, "Header name mismatch at row " + i);
//
//            driver.navigate().back();
//            Thread.sleep(5000);
//        }
//    }
//}
