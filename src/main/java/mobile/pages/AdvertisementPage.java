package mobile.pages;
import com.google.inject.Inject;
import core.config.Waits;
import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import core.annotations.TestScoped;


import java.util.*;
@TestScoped
public class AdvertisementPage {
    private Waits wait;

    @FindBy(xpath = "//*[contains(@content-desc, 'სწორი კატეგორიის მონიშვნით')]")
    public WebElement categoryDropDown;

    @FindBy(xpath = "//android.view.View[starts-with(@content-desc, 'კატეგორია')]/android.widget.ImageView[1]")
    public WebElement edit;

    @FindBy(xpath = "//android.view.View[starts-with(@content-desc, 'კატეგორია *')]")
    public WebElement title;
    @Getter
@FindBy(xpath ="//*[contains(@content-desc,'სწორი კატეგორიის მონიშვნით')]" )
WebElement categoryField;
@Getter
// ერთიანი, გასუფთავებული სტრინგი
@FindBy(xpath = "//*[starts-with(@content-desc,'კატეგორია') and @content-desc!='კატეგორიები']/android.widget.ImageView[1]")
    WebElement editBtn;




@Inject
    public AdvertisementPage(AppiumDriver driver, Waits wait) {
    this.wait=wait;
    PageFactory.initElements(driver, this);
    }

    public String getTitleAfterChange() {
return title.getAttribute("content-desc");
    }

    public void waitToBevisible(WebElement element) {
        wait.getShortWait().until(ExpectedConditions.visibilityOf(element));
    }

}