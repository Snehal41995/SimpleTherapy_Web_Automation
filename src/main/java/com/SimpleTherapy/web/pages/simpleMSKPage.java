package com.simpleTherapy.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SimpleMSKPage extends BaseClass {
    @FindBy(xpath = "//a[@href='/dashboard']")
    WebElement homeLink;

    public SimpleMSKPage() {
        PageFactory.initElements(driver, this);
    }

    public void clickHome() {
        click(homeLink);
    }
}
