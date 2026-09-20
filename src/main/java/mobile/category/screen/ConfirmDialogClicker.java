package mobile.category.screen;

import com.google.inject.Inject;
import core.config.IWaitSettings;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import mobile.category.driver.WaitFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;



public class ConfirmDialogClicker {
    private final AppiumDriver driver;
    private final WaitFactory waitFactory;
    private final IWaitSettings waitSettings;

    @Inject
    public ConfirmDialogClicker(AppiumDriver driver, WaitFactory waitFactory, IWaitSettings waitSettings) {
        this.driver = driver;
        this.waitFactory = waitFactory;
        this.waitSettings = waitSettings;
    }


    public boolean confirm(String confirmButton) {
        By button = AppiumBy.accessibilityId(confirmButton);
        List<WebElement> btn = driver.findElements(button);
        if (btn.isEmpty()) return false;
        btn.get(0).click();
        try {
            waitFactory.newWait(waitSettings.transitionTimeoutMs())
                    .until(ExpectedConditions.invisibilityOfElementLocated(button));
        } catch (TimeoutException ignored) {

        }
        return true;
    }
}
