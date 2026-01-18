package com.simpleTherapy.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BenefitCardPage extends BaseClass {
    @FindBy(xpath = "//h1[text()='What brings you here today?']")
    WebElement benefitCardHeading;

    @FindBy(xpath = "//a[.//text()[contains(.,'Start virtual physical therapy')]]")
    WebElement firstCard;

    public BenefitCardPage() {
        PageFactory.initElements(driver, this);
    }

    public String getBenefitCardHeading() {
        return benefitCardHeading.getText();
    }

    public void clickFirstCard() {
        click(firstCard);
    }
}
