package com.simpleTherapy.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyBenefitsPage extends BaseClass {

    /* ---------- LOCATORS ---------- */

    @FindBy(xpath = "//span[contains(text(),'My Benefits')]")
    WebElement myBenefitsTab;

    @FindBy(id = "msk_benefit_card")
    WebElement simpleMSKCard;

    @FindBy(id = "sbh_benefit_card")
    WebElement simpleBehavioralCard;

    @FindBy(id = "eap_benefit_card")
    WebElement simpleEAPCard;

    @FindBy(id = "wellbeing_benefit_card")
    WebElement simpleWellbeingCard;


    /* ---------- CONSTRUCTOR ---------- */

    public MyBenefitsPage() {
        PageFactory.initElements(driver, this);
    }


    /* ---------- CLICK METHODS ---------- */

    public void clickMyBenefits() {
        waitForElementVisibility(myBenefitsTab);
        click(myBenefitsTab);
    }

    public void clickBehavioralCard() {
        waitForElementVisibility(simpleBehavioralCard);
        click(simpleBehavioralCard);
    }

    public void clickEAPCard() {
        waitForElementVisibility(simpleEAPCard);
        click(simpleEAPCard);
    }

    public void clickWellbeingCard() {
        waitForElementVisibility(simpleWellbeingCard);
        click(simpleWellbeingCard);
    }


    /* ---------- COLOR METHODS ---------- */

    public String getMSKCardColor() {
        return getColor(simpleMSKCard);
    }

    public String getBehavioralCardColor() {
        return getColor(simpleBehavioralCard);
    }

    public String getEAPCardColor() {
        return getColor(simpleEAPCard);
    }

    public String getWellbeingCardColor() {
        return getColor(simpleWellbeingCard);
    }


    /* ---------- COMMON COLOR METHOD ---------- */

    private String getColor(WebElement element) {

        waitForElementVisibility(element);

        String backgroundColor = element.getCssValue("background-color");

        String hexColor = Color.fromString(backgroundColor).asHex();

        System.out.println("Card color: " + hexColor);

        return hexColor;
    }
}