package mobile.category.driver;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;

public class ScreenGeometry {
    private final AppiumDriver driver;

    @Inject
    public ScreenGeometry(AppiumDriver driver) {
        this.driver = driver;
    }

    public Dimension size() {
        return driver.manage().window().getSize();
    }

    public int centerY() {
        return size().getHeight() / 2;
    }
}
