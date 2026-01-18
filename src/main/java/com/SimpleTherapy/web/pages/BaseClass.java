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
    private static WebDriverWait wait;
    public static String browserName;

    public static long EXPLICIT_WAIT = 20;
    public static long IMPLICIT_WAIT = 20;

    /* ------------------------------------------------------
                      DRIVER + SETUP
    ------------------------------------------------------ */

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

        String browserName = prop.getProperty("browser");
        String notificationsSetting = prop.getProperty("notifications");

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            // Notification control
            if (notificationsSetting.equalsIgnoreCase("block")) {
                options.addArguments("--disable-notifications");

            } else if (notificationsSetting.equalsIgnoreCase("allow")) {
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.default_content_setting_values.notifications", 1);
                options.setExperimentalOption("prefs", prefs);
            }

            driver = new ChromeDriver(options);
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LONG_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);

        driver.get(prop.getProperty("BaseUrl"));

        ExtentReportListener.setDriver(driver);

        wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
    }


    /* ------------------------------------------------------
                      WAIT METHODS
    ------------------------------------------------------ */

    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /* NEW: Wait for page to fully load */
    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete")
        );
    }


    /* ------------------------------------------------------
                      CLICK METHODS
    ------------------------------------------------------ */

    public void waitForButtonToBeEnabled(WebElement button) {
        wait.until(driver -> button.getAttribute("class") != null &&
                !button.getAttribute("class").contains("cursor-not-allowed"));
    }

    public void click(WebElement element) {
        try {

            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, document.body.scrollHeight);");

            Thread.sleep(400);

            waitForButtonToBeEnabled(element);
            waitForClickable(element);
            element.click();

        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public void clickOnMe(WebElement element) {
        try {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView({block:'center'});", element);

            Thread.sleep(300);

            element.click();

        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }


    /* ------------------------------------------------------
                      SEND KEYS
    ------------------------------------------------------ */

    public void sendKeys(WebElement element, String text) {
        WebElement el = waitForVisibility(element);
        el.clear();
        el.sendKeys(text);
    }


    /* ------------------------------------------------------
                     SCROLLING METHODS
    ------------------------------------------------------ */

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    public void scrollDown(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, arguments[0]);", pixels);
    }


    /* ------------------------------------------------------
                     GET TEXT
    ------------------------------------------------------ */

    public String getText(WebElement element) {
        return waitForVisibility(element).getText().trim();
    }


    /* ------------------------------------------------------
                   DROPDOWN (Select Class)
    ------------------------------------------------------ */

    public void selectDropdownByVisibleText(WebElement element, String text) {
        new Select(element).selectByVisibleText(text);
    }


    /* ------------------------------------------------------
                   IFRAME METHODS
    ------------------------------------------------------ */

    public void switchToFrame(WebElement frame) {
        driver.switchTo().frame(frame);
    }

    public void switchToDefault() {
        driver.switchTo().defaultContent();
    }


    /* ------------------------------------------------------
                     CLIENT-SIDE ERROR HANDLING
    ------------------------------------------------------ */

    /* Check if React crashed */
    public boolean isClientErrorDisplayed() {
        try {
            WebElement errorMsg = driver.findElement(
                    By.xpath("//*[contains(text(),'client-side exception')]")
            );
            return errorMsg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /* Auto-handle the error */
    public void handleClientError() {
        if (isClientErrorDisplayed()) {
            addLog(Status.WARNING, "⚠ Client-side exception detected → Refreshing the page");
            driver.navigate().refresh();
            waitForPageLoad();
        }
    }


    /* ------------------------------------------------------
                   EXTENT LOG HELPERS
    ------------------------------------------------------ */

    public void addLog(Status status, String message) {
        ExtentTest test = ExtentReportListener.getTest();
        if (test != null) {
            test.log(status, message);
        } else {
            System.out.println("ExtentTest is not initialized: " + message);
        }
    }
}
