package mobile.pages;

import com.google.inject.Inject;
import core.reporter.ReportMessages;
import io.appium.java_client.AppiumDriver;
import core.config.properties.CategoryNameBtn;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import core.annotations.TestScoped;

@TestScoped
public class MobileAdvertisementPage {
    private final AppiumDriver driver;

    @FindBy(xpath = "//android.view.View[@content-desc='გაყიდვა']")
    public WebElement sellBtn;

    @FindBy(xpath = "//android.view.View[@content-desc='შეძენა']")
    public WebElement buyBtn;

    @FindBy(xpath = "//android.view.View[@content-desc='გაქირავება']")
    public WebElement rentBtn;

    @FindBy(xpath = "//android.view.View[@content-desc='მომსახურება']")
    public WebElement serviceBtn;
    @Inject
    public MobileAdvertisementPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement sectionButton(CategoryNameBtn section) {
        switch (section) {
            case SELLBTN:    return sellBtn;
            case BUYBTN:     return buyBtn;
            case RENTBTN:    return rentBtn;
            case SERVICEBTN: return serviceBtn;
            default: throw new IllegalArgumentException(ReportMessages.UNKNOWN_MOBILE_SECTION.format(section));
        }
    }

    public void clickSection(CategoryNameBtn section) {
        sectionButton(section).click();
    }

}