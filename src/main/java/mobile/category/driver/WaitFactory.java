package mobile.category.driver;

import com.google.inject.Inject;
import core.config.IWaitSettings;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitFactory {
    private final AppiumDriver driver;
    private final IWaitSettings waitSettings;

    @Inject
    public WaitFactory(AppiumDriver driver, IWaitSettings waitSettings) {
        this.driver = driver;
        this.waitSettings = waitSettings;
    }

    public WebDriverWait newWait(long timeoutMs) {
        WebDriverWait w = new WebDriverWait(driver, Duration.ofMillis(timeoutMs));
        w.pollingEvery(Duration.ofMillis(waitSettings.pollMs()));
        w.ignoring(StaleElementReferenceException.class);
        return w;
    }
}
