package org.example.pages.login;

import com.google.inject.Inject;
import lombok.Getter;
import org.example.pages.basepage.IBasePage;
import org.example.utils.Waits;
import org.example.utils.driver.IDriver;
import org.example.utils.reporter.IReportTree;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage {
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
    private final IDriver driver;
    private final IBasePage basePage;
    @Inject
    public LoginPage(IDriver driver, IReportTree reporter, Waits waitUtils, IBasePage basePage) {
        this.reporter = reporter;
        this.waitUtils = waitUtils;
        this.driver = driver;
        this.basePage = basePage;
        PageFactory.initElements(driver.getDriver(), this);
    }

    public void clickLoginBtn() {
        basePage.getWaitHelper().waitElementToBeVisible(waitUtils.getShortWait(), userLoginBtn);
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

    public void closePopUp() {
        try {
            By dialogLocator = By.tagName("dialog");
            waitUtils.getWait().until(ExpectedConditions.presenceOfElementLocated(dialogLocator));
            JavascriptExecutor js = (JavascriptExecutor) driver.getDriver();
            js.executeScript(
                    "var dialogs = document.querySelectorAll('dialog');" +
                            "dialogs.forEach(function(dialog) {" +
                            "   if (typeof dialog.close === 'function') { dialog.close(); }" +
                            "   dialog.remove();" +
                            "});"
            );

            waitUtils.getWait().until(ExpectedConditions.invisibilityOfElementLocated(dialogLocator));
            reporter.info("რეკლამის დიალოგის ფანჯარა (dialog) წარმატებით დაიხურა.");
        } catch (TimeoutException | NoSuchElementException e) {
            reporter.info("დიალოგი არ გამოჩნდა (" + e.getClass().getSimpleName() + ")");
        }
    }

    public void closeDialogWindow() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver.getDriver();
            js.executeScript(
                    "var dialog = document.querySelector('dialog');" +
                            "if (dialog && typeof dialog.close === 'function') { dialog.close(); }"
            );
            reporter.info("დიალოგის ფანჯარა წარმატებით დაიხურა.");
        } catch (JavascriptException | TimeoutException | NoSuchElementException e) {
            reporter.info("დიალოგი არ გამოჩნდა (" + e.getClass().getSimpleName() + ")");
        }
    }
}