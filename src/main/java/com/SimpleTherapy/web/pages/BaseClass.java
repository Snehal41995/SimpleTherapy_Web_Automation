package com.simpleTherapy.web.pages;

import com.simpleTherapy.web.utils.ExtentReportListener;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static WebDriver driver;
    public static Properties prop;
    public static JavascriptExecutor js;
    public static String browserName;
    private static WebDriverWait wait;

    // Constructor → Loads config.properties
    public BaseClass() {
        try {
            prop = new Properties();
            FileInputStream inputStream = new FileInputStream(Constants.ConfigFile_Path);
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialization() {

        browserName = prop.getProperty("browser");
        String notificationsSetting = prop.getProperty("notifications"); // read from config

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            // ===== Handle notifications based on config =====
            if (notificationsSetting.equalsIgnoreCase("block")) {
                options.addArguments("--disable-notifications");
            } else if (notificationsSetting.equalsIgnoreCase("allow")) {
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.default_content_setting_values.notifications", 1); // 1 = allow
                options.setExperimentalOption("prefs", prefs);
            }

            driver = new ChromeDriver(options); // Initialize driver with options
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LONG_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);

        ExtentReportListener.setDriver(driver);

        // Load Base URL from config.properties
        driver.get(prop.getProperty("BaseUrl"));

        wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT));
    }


    // Wait for visibility
    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Select dropdown option
    public void selectDropdownByVisibleText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    // Wait for element to be clickable
    public WebElement waitForClick(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Click
    public void click(WebElement element) {
        try {
            waitForClick(element).click();
        } catch (TimeoutException e) {
            throw new RuntimeException("Element not clickable: " + element);
        }
    }

    // SendKeys
    public void sendKeys(WebElement element, String text) {
        try {
            WebElement el = waitForVisibility(element);
            el.clear();
            el.sendKeys(text);
        } catch (TimeoutException e) {
            throw new RuntimeException("Unable to send keys: " + element);
        }
    }

    // Get text safely
    public String getText(WebElement element) {
        try {
            return waitForVisibility(element).getText().trim();
        } catch (TimeoutException e) {
            throw new RuntimeException("Unable to get text: " + element);
        }
    }

//    // Scroll
//    public static void scroll() {
//        js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0,200);");
//    }

    // Scroll down by pixels
    public static void scrollDown(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, arguments[0]);", pixels);
    }

    // Scroll up by pixels
    public static void scrollUp(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, -arguments[0]);", pixels);
    }

    // Scroll until element is visible
    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // iframe
    public void switchToFrame(WebElement frame) {
        driver.switchTo().frame(frame);
    }

    public void switchToDefault() {
        driver.switchTo().defaultContent();
    }

    public void addLog(Status status, String message) {
        ExtentTest test = ExtentReportListener.getTest();
        if (test != null) {
            test.log(status, message);
        } else {
            System.out.println("ExtentTest is not initialized: " + message);
        }
    }

}
