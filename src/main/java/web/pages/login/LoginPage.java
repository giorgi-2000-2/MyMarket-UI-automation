package web.pages.login;
import com.google.inject.Inject;
import lombok.Getter;
import core.annotations.TestScoped;
import uicommon.utils.Waits;
import uicommon.driver.IDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import web.pages.basepage.PageAction;

@TestScoped
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


    private final Waits waitUtils;
    private final PageAction pageAction;
    @Inject
    public LoginPage(IDriver driver , Waits waitUtils, PageAction pageAction) {
        this.waitUtils = waitUtils;
        this.pageAction = pageAction;
        PageFactory.initElements(driver.getDriver(), this);
    }

    public void clickLoginBtn() {
        pageAction.waitElementToBeVisible(waitUtils.getShortWait(), userLoginBtn);
        pageAction.click(userLoginBtn);
    }

    public void login(String userLogin, String passwordLogin) {
        clickLoginBtn();
        pageAction.sendKeys(userNameField, userLogin);
        pageAction.sendKeys(passwordField, passwordLogin);
        pageAction.click(loginBtn);
    }


}