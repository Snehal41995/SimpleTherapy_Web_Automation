package com.SimpleTherapy.web.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;                  // Keeps track of how many times the test has retried
    private static final int maxRetryCount = 1;  // Maximum number of retry attempts

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;   // Increment retry counter
            System.out.println("Retrying test " + result.getName() + " again | Attempt " + (retryCount + 1));
            return true;    // Return true → TestNG will re-run the test
        }
        return false;       // Return false → No more retries
    }
}
