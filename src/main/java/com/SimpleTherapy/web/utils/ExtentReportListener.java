package com.simpleTherapy.web.utils;

import com.simpleTherapy.web.pages.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * ExtentReportListener - Generates Extent Reports with Pass/Fail/Skip Status
 */
public class ExtentReportListener extends BaseClass implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static WebDriver driver;
    private static String reportpath;

    // Link driver from BaseClass
    public static void setDriver(WebDriver driverRef) {
        driver = driverRef;
    }

    public static synchronized ExtentTest getTest() {
        return (ExtentTest) test.get();
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test suite Started!");
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        reportpath = System.getProperty("user.dir") + "/Reports/ExtentReport_" + timestamp + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportpath);
        spark.config().setDocumentTitle("Automation Test Report");
        spark.config().setReportName("SimpleTherapy Test Execution Report");
        spark.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Project Name", "SimpleTherapy");
        extent.setSystemInfo("User Name",System.getProperty("user.name"));
        extent.setSystemInfo("Browser", BaseClass.browserName);
        extent.setSystemInfo("Tester", "Snehal Kadam");
        extent.setSystemInfo("Environment", "QA");
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();
        System.out.println(testName + " test Started!");
        if (description != null && !description.isEmpty()) {
            System.out.println("Description: " + description);
        }
        ExtentTest extentTest = extent.createTest(testName,description)
                .assignCategory(result.getTestClass().getRealClass().getSimpleName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "✅ Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "❌ Test Failed: " + result.getMethod().getMethodName());
        test.get().log(Status.FAIL, result.getThrowable());

        if (driver != null) {
            String screenshotPath = captureScreenshot(result.getMethod().getMethodName());
            test.get().addScreenCaptureFromPath(screenshotPath);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "⚠️ Test Skipped: " + result.getMethod().getMethodName());
        if (result.getThrowable() != null) {
            test.get().log(Status.SKIP, result.getThrowable());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();

        int total_tests = context.getAllTestMethods().length;
        int passed = context.getPassedTests().size();
        int failed = context.getFailedTests().size();
        int skipped = context.getSkippedTests().size();

        System.out.println("Total Tests Execution : " + total_tests);
        System.out.println("Passed Tests : " + passed);
        System.out.println("Failed Tests : " + failed);
        System.out.println("Skipped Tests : " + skipped);

        // Collect failed test details
        StringBuilder failedDetails = new StringBuilder();
        if (failed > 0) {
            failedDetails.append("<h4>Failed Test Details:</h4><ul>");
            context.getFailedTests().getAllResults().forEach(result -> {
                String testName = result.getMethod().getMethodName();
                Throwable throwable = result.getThrowable();
                failedDetails.append("<li><b>")
                        .append(testName)
                        .append(":</b> ")
                        .append(throwable != null ? throwable.getMessage() : "No error message available")
                        .append("</li>");
            });
            failedDetails.append("</ul>");
        }
        // Send email with detailed info
        sendEmail(total_tests, passed, failed, skipped, failedDetails.toString());
    }

    private void sendEmail(int total, int passed, int failed, int skipped, String failedDetails) {
        final String username = "testmobiuso151@gmail.com";
        final String password = "wxwrwmcygsesvyjl";

        String to = "snehal@mobiuso.com";
        String cc = "akshay@mobiuso.com, sagar@mobiuso.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.connection timeout", "10000");
        props.put("mail.smtp.timeout", "10000");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
            message.setSubject("SimpleTherapy Automation Test Report");

            // ✅ Email Body
            String body = "<h3>Test Execution Summary</h3>"
                    + "<p>Total Tests: " + total + "</p>"
                    + "<p style='color:green;'>Passed: " + passed + "</p>"
                    + "<p style='color:red;'>Failed: " + failed + "</p>"
                    + "<p style='color:orange;'>Skipped: " + skipped + "</p>"
                    + failedDetails
                    + "<p>Please find the attached Extent Report for more details.</p>";

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(body, "text/html");

            MimeBodyPart attachment = new MimeBodyPart();
            attachment.attachFile(reportpath);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            multipart.addBodyPart(attachment);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println(" Email sent successfully with failed test details!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // -------------------------
    // Utility: Capture screenshot
    // -------------------------
    private String captureScreenshot(String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String destPath = System.getProperty("user.dir") + "/Screenshots/" + testName + "_" + timestamp + ".png";

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(destPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destPath;
    }
}
