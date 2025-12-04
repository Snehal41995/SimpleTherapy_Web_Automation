package com.SimpleTherapy.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProgramsInfoPage extends BaseClass {
    // ======================= Page Elements =======================
    @FindBy(xpath = "//h1[contains(text(), 'with hundreds of programs')]")
    WebElement programsInfoHeading;

    // Constructor
    public ProgramsInfoPage() {
        PageFactory.initElements(driver, this);
    }

    public String getProgramsInfoHeading() {
        return programsInfoHeading.getText();
    }

}
