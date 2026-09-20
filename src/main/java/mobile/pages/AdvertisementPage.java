package mobile.pages;
import com.google.inject.Inject;
import core.config.Waits;
import core.reporter.ReportMessages;
import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import core.annotations.TestScoped;

import java.time.Duration;
import java.util.*;
@TestScoped
public class AdvertisementPage {
    private AppiumDriver driver;
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






    private final Set<String> SYSTEM_ELEMENTS = new HashSet<>(Arrays.asList(
            "კატეგორიები", "ყიდვა", "გაყიდვა", "ძებნა", "ფილტრი", "უკან"
    ));
@Inject
    public AdvertisementPage(AppiumDriver driver, Waits wait) {
        this.driver = driver;
    this.wait=wait;
        PageFactory.initElements(driver, this);
    }


    public List<WebElement> getValidCategoryElements(Set<String> breadcrumbs) {
        try {
            wait.getShortWait().until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[@content-desc != '' and not(*)]")
            ));
        } catch (Exception e) {
            System.out.println(ReportMessages.ELEMENTS_NOT_FOUND_TIMEOUT.format(e));
            return new ArrayList<>();
        }

        List<WebElement> rawElements = driver.findElements(By.xpath("//*[@content-desc != '' and not(*)]"));
        List<WebElement> categoryElements = new ArrayList<>();

        for (WebElement el : rawElements) {
            String desc = el.getAttribute("content-desc");
            if (desc == null || desc.trim().isEmpty()) continue;

            String cleanName = cleanCategoryName(desc);

            if (SYSTEM_ELEMENTS.contains(cleanName)) continue;
            if (breadcrumbs != null && breadcrumbs.contains(cleanName)) continue;

            categoryElements.add(el);
        }

        return categoryElements;
    }


    public String cleanCategoryName(String rawName) {
        if (rawName == null) return "";
        return rawName.replaceAll("\\s*\\(\\d+\\)$", "").trim();
    }

    public String getTitleAfterChange() {
return title.getAttribute("content-desc");
    }




    public void waitToBevisible(WebElement element) {
        wait.getShortWait().until(ExpectedConditions.visibilityOf(element));
    }


    private void tryToCenterElement(AppiumDriver driver, WebElement element) {
        try {
            Point elementLocation = element.getLocation();
            Dimension elementSize = element.getSize();
            Dimension screenSize = driver.manage().window().getSize();

            int screenCenterY = screenSize.getHeight() / 2;
            int elementCenterY = elementLocation.getY() + (elementSize.getHeight() / 2);

            int deltaY = elementCenterY - screenCenterY;
            if (Math.abs(deltaY) <= 150) return; // უკვე ცენტრშია

            int startX = screenSize.getWidth() / 2;
            int startY = screenCenterY;
            int endY = startY - (int) (deltaY * 0.7);

            int minY = (int) (screenSize.getHeight() * 0.20);
            int maxY = (int) (screenSize.getHeight() * 0.80);
            endY = Math.max(minY, Math.min(endY, maxY));

            performSwipe(driver, startX, startY, endY);
        } catch (Exception e) {
            // თუ ელემენტი ეკრანიდან გაქრა სქროლისას
        }
    }

    private void performSwipe(AppiumDriver driver, int startX, int startY, int endY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence scroll = new Sequence(finger, 1);

        scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scroll.addAction(finger.createPointerMove(Duration.ofMillis(400), PointerInput.Origin.viewport(), startX, endY));
        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(scroll));
    }
}