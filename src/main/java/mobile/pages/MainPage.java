package mobile.pages;
import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import core.annotations.TestScoped;

@TestScoped
public class MainPage {
    @FindBy(xpath ="//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.ImageView[3]" )
    public WebElement MyOfficeBtn;

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.ImageView[5]")
    public WebElement myProfileBtn;

    @FindBy(xpath = "//*[contains(@content-desc, 'დაამატე განცხადება')]")
    public WebElement addAnnouncementBtn;

    @FindBy(xpath = "//android.view.View[@content-desc=\"გიორგი მიქელაძე\"]")
    public WebElement profileUserName;

    @FindBy(xpath = "//android.view.View[@content-desc=\"ID 9060160\"]")
    public WebElement userId;


    @Inject
    public MainPage(AppiumDriver driver) {
        PageFactory.initElements(driver, this);
    }
    public String getProfileUserNameText() {
        return profileUserName.getAttribute("content-desc");
    }

    public String getUserIdText() {

        return userId.getAttribute("content-desc");
    }
}
