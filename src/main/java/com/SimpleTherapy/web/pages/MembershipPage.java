package com.simpleTherapy.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MembershipPage extends BaseClass {

    public MembershipPage() {
        PageFactory.initElements(driver, this);
    }

    // ---------------- Profile Image ----------------
    @FindBy(xpath = "//img[@alt='Sagar Pangale']")
    private WebElement profileImage;

    public String getProfileImageSrc() {
        return profileImage.getAttribute("src");
    }

    public boolean isProfileImageLoaded() {
        String src = getProfileImageSrc();
        return src != null && !src.isEmpty();
    }

    // ---------------- Membership Details ----------------
    @FindBy(xpath = "//span[@data-testid='employer-name']")
    private WebElement employerName;

    @FindBy(xpath = "//span[@data-testid='membership-date']")
    private WebElement membershipDate;

    public String getEmployerName() {
        return employerName.getText().trim();
    }

    public String getMembershipDate() {
        return membershipDate.getText().trim();
    }
}
