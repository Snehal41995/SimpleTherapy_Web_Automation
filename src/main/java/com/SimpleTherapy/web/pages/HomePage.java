//package com.SimpleTherapy.web.pages;
//
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//
//import java.util.List;
//
//public class HomePage extends BaseClass {
//    @FindBy(xpath = "//h1")
//    WebElement titleHeading;
//
//    @FindBy(xpath = "//a[@class='nav-link-2 w-nav-link']")
//    List<WebElement> navigationLinks;
//
//    @FindBy(xpath = "//h2[contains(text(),'Remote Learning')]")
//    WebElement remoteLearningHeader;
//
//    @FindBy(xpath = "//h2[contains(text(),'Get Skills Hub App')]")
//    WebElement getSkillsHubAppHeader;
//
//    @FindBy(xpath = "//h2[contains(text(),'Build your portfolio with the Skills Hub App')]")
//    WebElement buildPortfolioHeader;
//
//    @FindBy(xpath = "//h2[contains(text(),'BUZZ Secure Medical Messenger App')]")
//    WebElement buzzMessengerHeader;
//
//    @FindBy(xpath = "//h1[contains(text(),'Educators')]")
//    WebElement educatorsHeader;
//
//    public HomePage() {
//        PageFactory.initElements(driver, this);
//    }
//
//    public String getTitle(){
//        return titleHeading.getText();
//    }
//
//    public List<WebElement> getNavigationLinks() {
//        return navigationLinks;
//    }
//
//
//    public void clickOnNavLinks(int index) throws InterruptedException {
//       navigationLinks.get(index).click();
//       Thread.sleep(3000);
//    }
//
//    public String getRemoteLearningHeaderText() {
//
//        return remoteLearningHeader.getText();
//    }
//
//    public String getGetSkillsHubAppHeaderText() {
//
//        return getSkillsHubAppHeader.getText();
//    }
//
//    public String getBuildPortfolioHeaderText() {
//
//        return buildPortfolioHeader.getText();
//    }
//
//    public String getBuzzMessengerHeaderText() {
//
//        return buzzMessengerHeader.getText();
//    }
//
//    public String getEducatorsHeaderText() {
//
//        return educatorsHeader.getText();
//    }
//}
