package org.example.pages.advertisement;

import org.example.pages.basepage.BasePage;
import org.example.utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserInfoComponent  {
    private final Waits waitUtils;
    private final BasePage basePage;

    @FindBy(xpath = "(//div[@class='font-bold font-size-16 text-truncate user-name'])[2]")
    private WebElement pageUserName;

    @FindBy(xpath = "(//div[contains(@class,'d-flex align-items-center')])[4]")
    private WebElement usernameBtn;

    @FindBy(xpath = "//*[@id=\"root\"]/header/div[1]/div/div/div[1]/ul/div[3]/div[4]/div/div/div/div/div/div/div[2]/div[1]")
    private WebElement usernameBtnName;

    @FindBy(xpath = "(//div[contains(text(),'ID ')])[2]")
    private WebElement userNameID;

    public UserInfoComponent(WebDriver driver, Waits waitUtils , BasePage basePage) {
        this.waitUtils = waitUtils;
        this.basePage = basePage;
        PageFactory.initElements(driver, this);
    }

    public String getPageUserName() {
        basePage.getWaitHelper().waitElementToBeVisible(waitUtils.getShortWait(), pageUserName);
        return pageUserName.getText();
    }

    public String getUserNameFromDropdown() {
        basePage.getWaitHelper().waitElementToBeVisible(waitUtils.getWait(), usernameBtn);
        basePage.scroll(usernameBtn);
        basePage.click(usernameBtn);
        return usernameBtnName.getText();
    }

    public WebElement getUserNameID() {
        basePage.getWaitHelper().waitElementToBeVisible(waitUtils.getShortWait(), userNameID);
        return userNameID;
    }
}