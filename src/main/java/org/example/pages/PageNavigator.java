package org.example.pages;
import org.example.pages.advertisement.AdvertisementPage;
import org.example.pages.login.LoginPage;
import org.example.utils.config.IUrlConfig;
import org.example.utils.config.IUserConfig;
import org.example.utils.reporter.IReportTree;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;


public class PageNavigator  {
    private final  IUserConfig userConfig;
    private final IUrlConfig urlConfig;
    private final WebDriver driver;
    private final LoginPage loginPage;
    private final AdvertisementPage advertisementPage;
    private final IReportTree reporter;
    public PageNavigator(WebDriver driver, LoginPage loginPage, AdvertisementPage advertisementPage, IReportTree reporter, IUserConfig userConfig, IUrlConfig urlConfig) {
        this.driver = driver;
        this.loginPage = loginPage;
        this.advertisementPage = advertisementPage;
        this.reporter = reporter;
        this.userConfig = userConfig;
        this.urlConfig = urlConfig;
    }

    public void navigateToAdvertisementPage(SoftAssert softAssert)  {
        advertisementPage.clickAdvertisementBtn();
        softAssert.assertEquals(driver.getCurrentUrl(), urlConfig.visitUrl());
        softAssert.assertEquals(advertisementPage.getMainTitle().getText(),"განცხადების დამატება");
        softAssert.assertEquals(advertisementPage.getUserInfo().getPageUserName(),"გიორგი მიქელაძე");
        softAssert.assertEquals(advertisementPage.getUserInfo().getUserNameID().getText(), userConfig.userId());
        try{
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.querySelector('dialog').close();");} catch (Exception e) {
            reporter.info("დიალოგის ფანჯარა არ გამოჩნდა");
        }
    }
    public void loginAndNavigateToAdvertisementPage(SoftAssert softAssert) {
        reporter.info("დალოგინება");
        loginPage.login(userConfig.loginMail(), userConfig.loginPassword());
        reporter.info("ნავიგაცია განცხადების დამატების გვერდზე");
        navigateToAdvertisementPage(softAssert);
    }
}
