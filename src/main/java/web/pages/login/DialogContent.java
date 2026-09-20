package web.pages.login;

import com.google.inject.Inject;
import core.annotations.TestScoped;
import web.pages.basepage.BasePage;
import core.config.Waits;
import core.driver.IDriver;
import core.reporter.IReportTree;
import core.reporter.ReportMessages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
@TestScoped
public class DialogContent {

private final LoginPage loginPage;
    private final IDriver driver;
    private final Waits waitUtils;
    private final IReportTree reporter;
    private final BasePage basePage;
    @Inject
    public DialogContent(IDriver driver, LoginPage loginPage, Waits waitUtils, IReportTree reporter, BasePage basePage) {
        this.loginPage = loginPage;
        this.driver = driver;
        this.waitUtils = waitUtils;
        this.reporter = reporter;
        this.basePage = basePage;
    }


    public void closeDialogContent() {
        By cookie = By.xpath("//*[@id=\"cookiescript_accept\"]");
        waitUtils.getWait().until(ExpectedConditions.presenceOfElementLocated(cookie));
        basePage.click(loginPage.closeCookie);
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
            reporter.info(ReportMessages.AD_DIALOG_CLOSED.get());
        } catch (TimeoutException | NoSuchElementException e) {
            reporter.info( ReportMessages.DIALOG_NOT_SHOWN.format(e.getClass().getSimpleName()));
        }
    }

    public void closeDialogWindow() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver.getDriver();
            js.executeScript(
                    "var dialog = document.querySelector('dialog');" +
                            "if (dialog && typeof dialog.close === 'function') { dialog.close(); }"
            );
            reporter.info(ReportMessages.DIALOG_CLOSED.get());
        } catch (JavascriptException | TimeoutException | NoSuchElementException e) {
            reporter.info( ReportMessages.DIALOG_NOT_SHOWN.format(e.getClass().getSimpleName()));

        }
    }



}
