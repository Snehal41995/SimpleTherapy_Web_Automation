package com.simpleTherapy.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MembershipPage extends BaseClass {

    @FindBy(xpath = "//a[@href='/dashboard/profile/membership']")
    WebElement membershipOption;

    @FindBy(xpath = "//p[contains(@class,'typo-p2-regular') and contains(text(),'Employee ID')]")
    WebElement employerNameLabel;

    public MembershipPage() {
        PageFactory.initElements(driver, this);
    }

    public void clickMembership() {
        click(membershipOption);
    }

    public String getEmployerName() {
        return employerNameLabel.getText().trim();
    }
}

