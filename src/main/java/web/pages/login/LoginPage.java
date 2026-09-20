package web.pages.login;

import com.google.inject.Inject;
import lombok.Getter;
import core.annotations.TestScoped;
import web.pages.basepage.BasePage;
import core.config.Waits;
import core.driver.IDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
    private final BasePage basePage;
    @Inject
    public LoginPage(IDriver driver , Waits waitUtils, BasePage basePage) {
        this.waitUtils = waitUtils;
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


}