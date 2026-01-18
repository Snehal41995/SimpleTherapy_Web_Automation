package com.simpleTherapy.web.Test;

import com.aventstack.extentreports.Status;
import com.simpleTherapy.web.pages.*;
import com.simpleTherapy.web.utils.AuthenticatorOtpUtil;
import com.simpleTherapy.web.utils.PhoneUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.simpleTherapy.web.utils.ExcelReader;
import com.simpleTherapy.web.pages.Constants;
import java.util.List;

public class RegistrationFlowTest extends BaseClass {

    LandingPage landingPage;
    ConsentPage consentPage;
    GreatNewsPage greatNewsPage;
    NameDetailsPage nameDetailsPage;
    FirstGreetingPage firstGreetingPage;
    SecondGreetingPage secondGreetingPage;
    ThirdGreetingPage thirdGreetingPage;
    PersonalInfoPage personalInfoPage;
    AddressDetailsPage addressDetailsPage;
    EligibilityPage eligibilityPage;
    SignupPasswordPage signupPasswordPage;
    AccountSecurityPage accountSecurityPage;
    AccountSecurityOtpPage accountSecurityOtpPage;
    PostAuthPage postAuthPage;
    BenefitCardPage benefitCardPage;
    SimpleMSKPage simpleMSKPage;
    DashboardPage dashboardPage;
    ProfileDetailsPage profileDetailsPage;
    CustomerConfiguration customerConfig;

    @BeforeMethod
    public void setUp() throws Exception {
        initialization();
        //customerConfig = new CustomerConfiguration("Dev2");
        customerConfig = new CustomerConfiguration("Dev2", 2);
        //customerConfig = new CustomerConfiguration("Dev2", 3);

        landingPage = new LandingPage();
        consentPage = new ConsentPage();
        greatNewsPage = new GreatNewsPage();
        nameDetailsPage = new NameDetailsPage();
        firstGreetingPage = new FirstGreetingPage();
        secondGreetingPage = new SecondGreetingPage();
        thirdGreetingPage = new ThirdGreetingPage();
        personalInfoPage = new PersonalInfoPage();
        addressDetailsPage = new AddressDetailsPage();
        eligibilityPage = new EligibilityPage();
        signupPasswordPage = new SignupPasswordPage();
        accountSecurityPage = new AccountSecurityPage();
        accountSecurityOtpPage = new AccountSecurityOtpPage();
        postAuthPage = new PostAuthPage();
        benefitCardPage = new BenefitCardPage();
        simpleMSKPage = new SimpleMSKPage();
        dashboardPage = new DashboardPage();
        profileDetailsPage = new ProfileDetailsPage();
    }

    @Test(description = "Complete User Registration E2E Flow")
    public void verifyRegistrationFlow() throws Exception {

        // ---- LANDING PAGE ----
        addLog(Status.INFO, "Landing Page Validation");
        landingPage.selectEmployer(customerConfig.getEmployerNameFromExcel());
        Thread.sleep(2000);
        landingPage.clickContinueBtn();

        // ---- CONSENT PAGE ----
        addLog(Status.INFO, "Consent Page Validation");
        Assert.assertEquals(consentPage.getConsentHeading(), customerConfig.getConsentHeadingFromExcel(), "Consent page heading mismatch");
        addLog(Status.PASS, "Consent heading is correct");
        consentPage.clickFirstCheckBox();
        consentPage.clickSecondCheckBox();
        consentPage.clickThirdCheckBox();
        consentPage.clickAcceptAllBtn();
        Thread.sleep(2000);

        // ---- GREAT NEWS PAGE ----
        addLog(Status.INFO, "Great News Page Validation");
        Assert.assertEquals(greatNewsPage.getGreatNewsHeading(), customerConfig.getGreatNewsHeadingFromExcel(), "Great News heading mismatch");
        greatNewsPage.clickHearAboutUsDropdown();
        Thread.sleep(2000);
        greatNewsPage.selectDemoOption();
        Thread.sleep(2000);
        greatNewsPage.clickContinueBtn();
        Thread.sleep(2000);

        // ---- NAME DETAILS PAGE ----
        addLog(Status.INFO, "Name Details Page Validation");
        Assert.assertEquals(nameDetailsPage.getNameDetailsHeading(), customerConfig.getNameDetailsHeadingFromExcel(), "Name Details Page Heading mismatch");
        addLog(Status.PASS, "Name Details Page heading validated");
        nameDetailsPage.enterFirstName(customerConfig.getFirstNameFromExcel());
        Thread.sleep(2000);
        nameDetailsPage.enterLastName(customerConfig.getLastNameFromExcel());
        Thread.sleep(2000);
        nameDetailsPage.enterPrefName(customerConfig.getPrefNameFromExcel());
        Thread.sleep(2000);
        nameDetailsPage.clickContinueBtn();
        Thread.sleep(2000);

        // ---- FIRST GREETING PAGE ----
        addLog(Status.INFO, "Greeting Page 1 Validation");
        Assert.assertEquals(firstGreetingPage.getFirstGreetingHeading(), customerConfig.getFirstGreetingHeadingFromExcel());
        addLog(Status.PASS, "Greeting Page 1 heading validated successfully");
        addLog(Status.INFO, "Checking Greeting Page image is displayed");
        Assert.assertTrue(firstGreetingPage.isGreetingImageDisplayed(), "Greeting image did not load!");
        addLog(Status.PASS, "Greeting image displayed successfully");
        firstGreetingPage.clickContinueBtn();
        Thread.sleep(3000);

        // ---- SECOND GREETING PAGE ----
        addLog(Status.INFO, "Greeting Page 2 Validation");
        Assert.assertEquals(secondGreetingPage.getSecondGreetingHeading(), customerConfig.getSecondGreetingHeadingFromExcel(), "Greeting page 2 heading mismatch!");
        addLog(Status.PASS, "Greeting Page 2 heading validated successfully");
        List<WebElement> images = secondGreetingPage.getAllImages();
        System.out.println("Total images found: " + images.size());
        for (WebElement img : images) {
            // Scroll image into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", img);
            Thread.sleep(500);
            String src = img.getAttribute("src");
            Assert.assertNotNull(src, "Image src is null");
            Assert.assertFalse(src.trim().isEmpty(), "Image src attribute is empty");
            // Validate image is loaded
            Assert.assertTrue(secondGreetingPage.isImageLoaded(img), "Image not loaded properly: " + src);
            System.out.println("Image verified: " + src);
        }

        WebElement continueBtn = secondGreetingPage.getVisibleContinueButton();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].scrollIntoView({block:'center'});", continueBtn);
        click(continueBtn);
        Thread.sleep(3000);

        // ---- Third GREETING PAGE ----
        addLog(Status.INFO, "Greeting Page 3 Validation");
        Assert.assertEquals(thirdGreetingPage.getThirdGreetingHeading(), customerConfig.getThirdGreetingHeadingFromExcel());
        addLog(Status.PASS, "Greeting Page 3 heading validated successfully");
        addLog(Status.INFO, "Checking Greeting Page 3 image is displayed");
        Assert.assertTrue(thirdGreetingPage.isGreetingImageDisplayed(), "Greeting image did not load!");
        addLog(Status.PASS, "Greeting image 3 displayed successfully");
        thirdGreetingPage.clickContinueBtn();
        Thread.sleep(3000);

        // ---- PERSONAL INFO PAGE
        addLog(Status.INFO, "Personal Info Page Validation");
        Assert.assertEquals(personalInfoPage.getPersonalInfoHeading(), customerConfig.getPersonalInfoHeadingFromExcel(), "Personal Info page heading mismatch!");
        addLog(Status.PASS, "Personal Info Page heading validated successfully");
        personalInfoPage.clickPrimaryRadioBtn();
        personalInfoPage.selectDOB();

        String generatedEmail = personalInfoPage.enterEmail();
        addLog(Status.INFO, "Email generated: " + generatedEmail);

        ExcelReader excel = new ExcelReader(Constants.SimpleTherapy_TestData);

        // Save Email
        excel.setCellData("OTP_Data", "Env", 2, "Dev2");
        excel.setCellData("OTP_Data", "Email", 2, generatedEmail);

        addLog(Status.PASS, "Email saved into Excel");

        String phoneNumber = customerConfig.getPhoneNumberFromExcel();
        addLog(Status.PASS, "Phone Number fetched successfully: " + phoneNumber);

        addLog(Status.INFO, "Entering phone number");
        personalInfoPage.enterPhoneNumber(phoneNumber);
        addLog(Status.PASS, "Phone number entered successfully");

        addLog(Status.INFO, "Validating entered phone number");
        String expectedPhoneNumber = PhoneUtil.normalize(phoneNumber);
        String actualPhoneNumber = personalInfoPage.getNormalizedPhoneNumber();
        Assert.assertEquals(actualPhoneNumber, expectedPhoneNumber, "Phone number does not match!");
        addLog(Status.INFO, "Expected Phone (normalized): " + expectedPhoneNumber);
        addLog(Status.INFO, "Actual Phone (normalized): " + actualPhoneNumber);

        addLog(Status.PASS, "Phone Number validated successfully");
        Thread.sleep(2000);

        addLog(Status.INFO, "Selecting gender");
        personalInfoPage.selectGender();
        addLog(Status.PASS, "Male gender selected successfully");

        personalInfoPage.clickContinue();

        // ---- ADDRESS DETAILS PAGE
        addLog(Status.INFO, "Address Details Page Validation");
        Assert.assertEquals(addressDetailsPage.getAddressDetailsHeading(), customerConfig.getAddressDetailsHeadingFromExcel(), "Address Details Page heading mismatch!");
        addLog(Status.PASS, "Address Details Page heading validated successfully");

        addressDetailsPage.enterAddress1AndSelectFirst(customerConfig.getAddress1FromExcel());
        Thread.sleep(2000);
        addressDetailsPage.clickContinue();

        // ---- ACTIVATION CODE DETAILS PAGE
        addLog(Status.INFO, "Activation Code Page Validation");
        Assert.assertEquals(eligibilityPage.getActivationCodeHeading(), customerConfig.getActivationCdeHeadingFromExcel(), "Activation Code Page heading mismatch!");
        addLog(Status.PASS, "Activation Code Page heading validated successfully");

        addLog(Status.INFO, "Entering Activation Code");
        eligibilityPage.enterActivationCode(customerConfig.getActivationCodeFromExcel());
        addLog(Status.PASS, "Activation code entered successfully");
        Thread.sleep(500);
        eligibilityPage.clickContinueBtn();
        Thread.sleep(2000);

        // ---- SIGNUP PASSWORD PAGE
        addLog(Status.INFO, "SignUp Password Page Validation");
        Assert.assertEquals(signupPasswordPage.getSignUpPasswordHeading(), customerConfig.getSignUpPasswordHeadingFromExcel(), "SignUp Password Page heading mismatch!");
        addLog(Status.PASS, "SignUp Password Page heading validated successfully");

        addLog(Status.INFO, "Entering Password");
        signupPasswordPage.enterPassword(customerConfig.getSignUpPasswordFromExcel());
        addLog(Status.PASS, "Password entered successfully");
        Thread.sleep(500);
        signupPasswordPage.clickContinueBtn();
        Thread.sleep(2000);

        // ---- ACCOUNT SECURITY PAGE
        addLog(Status.INFO, "Account Security Page Validation");
        Assert.assertEquals(accountSecurityPage.getAccountSecurityHeading(), customerConfig.getAccountSecurityHeadingFromExcel(), "Account Security Page heading mismatch!");
        addLog(Status.PASS, "Account Security Page heading validated successfully");

        accountSecurityPage.clickGoogleAuthArrow();
        Thread.sleep(2000);

        // ---- ACCOUNT SECURITY OTP PAGE
        addLog(Status.INFO, "Account Security Otp Page Validation");
        Assert.assertEquals(accountSecurityOtpPage.getAccountSecurityOtpHeading(), customerConfig.getAccountSecurityOtpHeadingFromExcel(), "Account Security Otp Page heading mismatch!");
        addLog(Status.PASS, "Account Security Otp Page heading validated successfully");

        accountSecurityOtpPage.clickTroubleScanning();
        Thread.sleep(2000);

//        String secretKey = accountSecurityOtpPage.getSecretKey();
//        addLog(Status.INFO, "Secret key captured from UI");

        String secretKey = accountSecurityOtpPage.getSecretKey();
        addLog(Status.INFO, "Secret key captured from UI");

        // Sheet: OTP_Data
        // Columns: Env | OtpSecret
        // Row 2: Dev2 user
        excel.setCellData("OTP_Data", "Env", 2, "Dev2");
        excel.setCellData("OTP_Data", "OtpSecret", 2, secretKey);
        addLog(Status.PASS, "OTP Secret key saved into Excel");

        // Generate OTP using authenticator logic
        String otp = AuthenticatorOtpUtil.generateOtp(secretKey);
        System.out.println(otp);
        addLog(Status.INFO, "OTP generated");

        accountSecurityOtpPage.enterOtp(otp);
        addLog(Status.PASS, "OTP entered successfully");

        accountSecurityOtpPage.clickContinue();
        addLog(Status.PASS, "OTP verification completed");
        Thread.sleep(5000);

        // ---- POST AUTH PAGE
        addLog(Status.INFO, "Post Auth Page Validation");
        String actualHeading = postAuthPage.getPostAuthHeading();
        String expectedHeading = customerConfig.getPostAuthHeadingFromExcel();
        Assert.assertTrue(actualHeading.contains(expectedHeading), "Post Auth Page heading mismatch!");
        addLog(Status.PASS, "Post Auth Page heading validated successfully");

        postAuthPage.clickContinue();
        Thread.sleep(5000);

        // ---- BENEFIT CARD PAGE
        addLog(Status.INFO, "Benefit Card Page Validation");
        Assert.assertEquals(benefitCardPage.getBenefitCardHeading(), customerConfig.getBenefitCardHeadingFromExcel(), "Benefit Card Page heading mismatch!");
        addLog(Status.PASS, "Benefit Card Page heading validated successfully");

        benefitCardPage.clickFirstCard();
        Thread.sleep(5000);

        // ---- SIMPLE MSK PAGE
        simpleMSKPage.clickHome();
        Thread.sleep(2000);

        // ---- DASHBOARD PAGE
        String expectedName = customerConfig.getExpectedDisplayName();
        addLog(Status.INFO, "Expected name from Excel: " + expectedName);
        Assert.assertTrue(dashboardPage.isUserNameCorrectOnSaraCard(expectedName), "Sara Card name validation failed!");
        addLog(Status.PASS, "Sara Card name validated successfully");

        dashboardPage.clickProfileIcon();

        // ---- PROFILE PAGE
        addLog(Status.INFO, "Opening Profile Details page");
        dashboardPage.clickProfileDetailsOption();
        Thread.sleep(2000);

        String expectedEmail = excel.getCellData("OTP_Data", "Email", 2).trim();
        addLog(Status.INFO, "Expected Email from Excel: " + expectedEmail);
        String actualEmail = profileDetailsPage.getEmail();
        addLog(Status.INFO, "Actual Email from Profile UI: " + actualEmail);
        Assert.assertEquals(actualEmail, expectedEmail, "Profile email does not match registered email!");
        addLog(Status.PASS, "Profile email matches registered email successfully");
    }
}

