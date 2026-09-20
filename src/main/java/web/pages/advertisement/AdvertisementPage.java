package web.pages.advertisement;

import com.google.inject.Inject;
import lombok.Getter;
import core.annotations.TestScoped;
import web.pages.basepage.BasePage;
import core.config.Waits;
import core.config.UiText;
import core.driver.IDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
@TestScoped
public class AdvertisementPage {
    private final BasePage basePage;
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
            BasePage basePage,
            Waits waitUtils,
            UserInfoComponent userInfo,
            CategoryDropdownComponent categoryDropdown,
            BrandDropdownComponent brandDropdown,
            TitleComponent titleComponent) {

        this.basePage = basePage;
        this.waitUtils = waitUtils;
        this.userInfo = userInfo;
        this.categoryDropdown = categoryDropdown;
        this.brandDropdown = brandDropdown;
        this.titleComponent = titleComponent;
        PageFactory.initElements(driver.getDriver(), this);
    }

    public void clickAdvertisementBtn() {
        basePage.getWaitHelper().waitElementToBeClickable(waitUtils.getWait(), advertisementBtn);
        basePage.click(advertisementBtn);
    }


    public String getMainTitle() {
        basePage.getWaitHelper().waitElementToBeVisible(waitUtils.getWait(), mainTitle);
        return mainTitle.getText();
    }


    public void waitString(WebElement element) {
        waitUtils.getTextWait().until(ExpectedConditions.not(
                ExpectedConditions.textToBePresentInElement(element, UiText.CHOOSE_CATEGORY.get())
        ));
    }
}