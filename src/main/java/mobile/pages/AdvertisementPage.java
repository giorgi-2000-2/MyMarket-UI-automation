package mobile.pages;

import com.google.inject.Inject;
import core.annotations.TestScoped;
import mobile.category.navigation.ICategoryFieldActions;
import uicommon.utils.Waits;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@TestScoped
public class AdvertisementPage extends BasePageMobile implements ICategoryFieldActions {

    @FindBy(xpath = "//*[contains(@content-desc, 'სწორი კატეგორიის მონიშვნით')]")
    WebElement categoryDropDown;

    @FindBy(xpath = "//android.view.View[starts-with(@content-desc, 'კატეგორია')]/android.widget.ImageView[1]")
    WebElement edit;

    @FindBy(xpath = "//android.view.View[starts-with(@content-desc, 'კატეგორია *')]")
    WebElement title;

    @FindBy(xpath = "//*[contains(@content-desc,'სწორი კატეგორიის მონიშვნით')]")
    WebElement categoryField;

    @FindBy(xpath = "//*[starts-with(@content-desc,'კატეგორია') and @content-desc!='კატეგორიები']/android.widget.ImageView[1]")
    WebElement editBtn;

    @Inject
    public AdvertisementPage(AppiumDriver driver, Waits waits) {
        super(waits);
        PageFactory.initElements(driver, this);
    }

    public String getTitleAfterChange() {
        return title.getAttribute("content-desc");
    }


    @Override
    public void openCategoryField() {
        categoryField.click();
    }

    @Override
    public void editSelectedCategory() {
        editBtn.click();
    }

}