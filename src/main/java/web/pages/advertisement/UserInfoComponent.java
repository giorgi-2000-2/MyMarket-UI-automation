package web.pages.advertisement;
import com.google.inject.Inject;
import core.annotations.TestScoped;
import uicommon.utils.Waits;
import uicommon.driver.IDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import web.pages.basepage.PageAction;
import web.pages.basepage.JavaScriptHelper;

@TestScoped
public class UserInfoComponent  {
    private final Waits waitUtils;
    private final PageAction pageAction;
    private final JavaScriptHelper javaScriptHelper;

    @FindBy(xpath = "(//div[@class='font-bold font-size-16 text-truncate user-name'])[2]")
    private WebElement pageUserName;

    @FindBy(xpath = "(//div[contains(@class,'d-flex align-items-center')])[4]")
    private WebElement usernameBtn;

    @FindBy(xpath = "//*[@id=\"root\"]/header/div[1]/div/div/div[1]/ul/div[3]/div[4]/div/div/div/div/div/div/div[2]/div[1]")
    private WebElement usernameBtnName;

    @FindBy(xpath = "(//div[contains(text(),'ID ')])[2]")
    private WebElement userNameID;
    @Inject
    public UserInfoComponent(IDriver driver, Waits waitUtils , PageAction pageAction, JavaScriptHelper javaScriptHelper) {
        this.waitUtils = waitUtils;
        this.pageAction = pageAction;
        this.javaScriptHelper = javaScriptHelper;
        PageFactory.initElements(driver.getDriver(), this);
    }

    public String getPageUserName() {
        pageAction.waitElementToBeVisible(waitUtils.getShortWait(), pageUserName);
        return pageUserName.getText();
    }

    public String getUserNameFromDropdown() {
        pageAction.waitElementToBeVisible(waitUtils.getWait(), usernameBtn);
        javaScriptHelper.scroll(usernameBtn);
        pageAction.click(usernameBtn);
        return usernameBtnName.getText();
    }

    public WebElement getUserNameID() {
        pageAction.waitElementToBeVisible(waitUtils.getShortWait(), userNameID);
        return userNameID;
    }
}