package org.example.pages.advertisement;

import com.google.inject.Inject;
import lombok.Getter;
import org.example.pages.basepage.IBasePage;
import org.example.utils.Waits;
import org.example.utils.driver.IDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.example.utils.config.UiText.CHOOSE_CATEGORY;

public class AdvertisementPage implements IAdvertisementPage {
    private final IBasePage basePage;
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
            IBasePage basePage,
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

    @Override
    public void clickAdvertisementBtn() {
        basePage.getWaitHelper().waitElementToBeClickable(waitUtils.getWait(), advertisementBtn);
        basePage.click(advertisementBtn);
    }

    @Override
    public WebElement getMainTitle() {
        basePage.getWaitHelper().waitElementToBeVisible(waitUtils.getWait(), mainTitle);
        return mainTitle;
    }

    @Override
    public void waitString(WebElement element) {
        waitUtils.getTextWait().until(ExpectedConditions.not(
                ExpectedConditions.textToBePresentInElement(element, CHOOSE_CATEGORY.getPath())
        ));
    }
}