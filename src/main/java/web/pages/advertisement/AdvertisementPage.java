package web.pages.advertisement;
import com.google.inject.Inject;
import core.reporter.texts.UiText;
import lombok.Getter;
import core.annotations.TestScoped;
import uicommon.utils.Waits;
import uicommon.driver.IDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import web.pages.basepage.PageAction;

@TestScoped
public class AdvertisementPage {
    private final PageAction pageAction;
    private final Waits waitUtils;

    @Getter private final UserInfoComponent userInfo;
    @Getter private final CategoryDropdownComponent categoryDropdown;
    @Getter private final BrandDropdownComponent brandDropdown;
    @Getter private final TitleComponent titleComponent;

    @FindBy(xpath = "//*[@data-testid='add-product-button']")
    private WebElement advertisementBtn;

    @FindBy(xpath = "(//h1[contains(text(),'განცხადების დამატება')])[1]")
    private WebElement mainTitle;

    @Inject
    public AdvertisementPage(
            IDriver driver,
            PageAction pageAction,
            Waits waitUtils,
            UserInfoComponent userInfo,
            CategoryDropdownComponent categoryDropdown,
            BrandDropdownComponent brandDropdown,
            TitleComponent titleComponent) {

        this.pageAction = pageAction;
        this.waitUtils = waitUtils;
        this.userInfo = userInfo;
        this.categoryDropdown = categoryDropdown;
        this.brandDropdown = brandDropdown;
        this.titleComponent = titleComponent;
        PageFactory.initElements(driver.getDriver(), this);
    }

    public void clickAdvertisementBtn() {
        pageAction.waitElementToBeClickable(waitUtils.getWait(), advertisementBtn);
        pageAction.click(advertisementBtn);
    }


    public String getMainTitle() {
        pageAction.waitElementToBeVisible(waitUtils.getWait(), mainTitle);
        return mainTitle.getText();
    }


    public void waitString(WebElement element) {
        waitUtils.getTextWait().until(ExpectedConditions.not(
                ExpectedConditions.textToBePresentInElement(element, UiText.CHOOSE_CATEGORY.get())
        ));
    }
}