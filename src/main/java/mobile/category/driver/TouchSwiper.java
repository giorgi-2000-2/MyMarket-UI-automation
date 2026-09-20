package mobile.category.driver;

import com.google.inject.Inject;
import core.config.IScrollConfig;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;


public class TouchSwiper implements Swiper {
    private final AppiumDriver driver;
    private final IScrollConfig scrollConfig;

    @Inject
    public TouchSwiper(AppiumDriver driver, IScrollConfig scrollConfig) {
        this.driver = driver;
        this.scrollConfig = scrollConfig;
    }

    @Override
    public void swipe(int startX, int startY, int endY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence s = new Sequence(finger, 1);
        s.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        s.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        s.addAction(finger.createPointerMove(Duration.ofMillis(scrollConfig.swipeMs()), PointerInput.Origin.viewport(), startX, endY));
        s.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(s));
    }

}
