package mobile.pages;

import com.google.inject.Inject;
import core.annotations.TestScoped;
import core.testdata.Section;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import uicommon.utils.Waits;

@TestScoped
public class MobileAdvertisementPage extends BasePageMobile {

    private final AdvertisementPage advertisementPage;

    @Inject
    public MobileAdvertisementPage(AppiumDriver driver, AdvertisementPage advertisementPage, Waits waits) {
        super(waits);
        PageFactory.initElements(driver, this);
        this.advertisementPage = advertisementPage;
    }

    private By sectionButton(Section section) {
        return By.xpath("//android.view.View[@content-desc='" + section.label() + "']");
    }

    public void selectSection(Section section) {
        WebElement btn = waits.getWait()
                .until(ExpectedConditions.visibilityOfElementLocated(sectionButton(section)));
        btn.click();
        visible(advertisementPage.categoryDropDown);
    }
}
