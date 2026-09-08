package org.example.pages.login;
import lombok.Getter;
import org.example.pages.basepage.BasePage;
import org.example.utils.Waits;
import org.example.utils.reporter.IReportTree;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage  {
    @Getter
    @FindBy(xpath = "//*[@id=\"cookiescript_accept\"]")
    WebElement closeCookie;

    @FindBy(xpath = "(//span[@class='font-tbcx-medium text-sm ml-2'])[1]")
    WebElement userLoginBtn;

    @FindBy(id = "_r_m_")
    WebElement userNameField;

    @FindBy(id = "_r_n_")
    WebElement passwordField;

    @FindBy(xpath = "(//button[contains(text(),'შესვლა')])[1]")
    WebElement loginBtn;
    private final IReportTree reporter;
    private final Waits waitUtils;
    private final WebDriver driver;
private final BasePage basePage;
    public LoginPage(WebDriver driver, IReportTree reporter, Waits waitUtils, BasePage basePage) {
        this.reporter = reporter;
        this.waitUtils = waitUtils;
        this.driver = driver;

        this.basePage = basePage;
        PageFactory.initElements(driver, this);
    }


    public void clickLoginBtn() {
        basePage.getWaitHelper().waitElementToBeVisible( waitUtils.getShortWait(), userLoginBtn);
        basePage.click(userLoginBtn);
    }

    public void login(String userLogin, String passwordLogin) {
        clickLoginBtn();
        basePage.sendKeys(userNameField, userLogin);
        basePage.sendKeys(passwordField, passwordLogin);
        basePage.click(loginBtn);
    }

    public void closeDialogContent() {
        By cookie = By.xpath("//*[@id=\"cookiescript_accept\"]");
        waitUtils.getWait().until(ExpectedConditions.presenceOfElementLocated(cookie));
        basePage.click(closeCookie);
    }

    public void closePopUp(){
        try {
            By dialogLocator = By.tagName("dialog");
            waitUtils.getWait().until(ExpectedConditions.presenceOfElementLocated(dialogLocator));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "var dialogs = document.querySelectorAll('dialog');" +
                            "dialogs.forEach(function(dialog) {" +
                            "   if (typeof dialog.close === 'function') { dialog.close(); }" +
                            "   dialog.remove();" +
                            "});"
            );

            waitUtils.getWait().until(ExpectedConditions.invisibilityOfElementLocated(dialogLocator));

            reporter.info("რეკლამის დიალოგის ფანჯარა (dialog) წარმატებით დაიხურა.");
        } catch (Exception e) {
            reporter.info("რეკლამის ფანჯარა არ გამოჩენილა.");
        }



        }







}




