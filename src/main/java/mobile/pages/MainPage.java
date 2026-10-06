package mobile.pages;

import com.google.inject.Inject;
import core.annotations.TestScoped;
import uicommon.utils.Waits;
import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@TestScoped
@Getter
public class MainPage extends BasePageMobile {
private final AppiumDriver driver;
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.ImageView[3]")
    WebElement myOfficeBtn;

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.ImageView[5]")
    WebElement myProfileBtn;

    @FindBy(xpath = "//*[contains(@content-desc, 'დაამატე განცხადება')]")
    WebElement addAnnouncementBtn;

    @FindBy(xpath = "//android.view.View[@content-desc=\"გიორგი მიქელაძე\"]")
    WebElement profileUserName;

    @FindBy(xpath = "//android.view.View[@content-desc=\"ID 9060160\"]")
    WebElement userId;

    @Inject
    public MainPage(AppiumDriver driver,Waits waits) {
        super( waits);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getProfileUserNameText() {
        visible(profileUserName);
        return profileUserName.getAttribute("content-desc");
    }

    public String getUserIdText() {
        visible(userId);
        return userId.getAttribute("content-desc");
    }

    public void openProfile() {
        visible(myProfileBtn);
        myProfileBtn.click();
    }

    public void openAddAnnouncement() {
        visible(myOfficeBtn);
        myOfficeBtn.click();
        visible(addAnnouncementBtn);
        addAnnouncementBtn.click();
    }
}