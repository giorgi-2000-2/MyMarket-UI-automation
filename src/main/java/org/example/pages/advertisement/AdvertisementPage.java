package org.example.pages.advertisement;
import lombok.Getter;
import org.example.pages.basepage.BasePage;
import org.example.utils.Waits;
import org.example.utils.reporter.IReportTree;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdvertisementPage  {
private final BasePage basePage;
    private final Waits waitUtils;

    @Getter
    private final UserInfoComponent userInfo;

    @Getter
    private final CategoryDropdownComponent categoryDropdown;

    @Getter
    private final BrandDropdownComponent brandDropdown;

    @Getter
    private final TitleComponent titleComponent;

    @FindBy(xpath = "//*[@data-testid='add-product-button']")
    private WebElement advertisementBtn;

    @FindBy(xpath = "(//h1[contains(text(),'განცხადების დამატება')])[1]")
    private WebElement mainTitle;

    public AdvertisementPage(WebDriver driver,BasePage basePage , IReportTree reporter, Waits waitUtils) {
        this.basePage = basePage;
        this.waitUtils = waitUtils;

        this.userInfo = new UserInfoComponent(driver,waitUtils,basePage);
        this.categoryDropdown = new CategoryDropdownComponent(driver,basePage,waitUtils, reporter);
        this.brandDropdown = new BrandDropdownComponent( driver,waitUtils,basePage);
        this.titleComponent = new TitleComponent( driver,waitUtils,basePage);
        PageFactory.initElements(driver, this);
    }

    public void clickAdvertisementBtn() {
        basePage.getWaitHelper().waitElementToBeClickable(waitUtils.getWait(), advertisementBtn);
        basePage.click(advertisementBtn);
    }

    public WebElement getMainTitle() {
        basePage.getWaitHelper().waitElementToBeVisible(waitUtils.getWait(), mainTitle);
        return mainTitle;
    }
}