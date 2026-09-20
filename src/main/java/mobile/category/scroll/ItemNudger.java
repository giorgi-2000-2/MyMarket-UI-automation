package mobile.category.scroll;

import com.google.inject.Inject;
import core.config.IScrollConfig;
import mobile.category.driver.ScreenGeometry;
import mobile.category.driver.Swiper;
import mobile.category.model.Item;
import org.openqa.selenium.Dimension;


public class ItemNudger {
    private final ScreenGeometry screenGeometry;
    private final Swiper swiper;
    private final IScrollConfig scrollConfig;

    @Inject
    public ItemNudger(ScreenGeometry screenGeometry, Swiper swiper, IScrollConfig scrollConfig) {
        this.screenGeometry = screenGeometry;
        this.swiper = swiper;
        this.scrollConfig = scrollConfig;
    }

    public void nudgeTowardsCenter(Item item) {
        Dimension screen = screenGeometry.size();
        int centerY = screen.getHeight() / 2;
        int deltaY = item.centerY() - centerY;
        if (Math.abs(deltaY) <= scrollConfig.centerTolerance()) return;

        int startX = screen.getWidth() / 2;
        int startY = centerY;
        int endY = startY - (int) (deltaY * scrollConfig.nudgeFactor());

        int minY = (int) (screen.getHeight() * scrollConfig.minY());
        int maxY = (int) (screen.getHeight() * scrollConfig.maxY());
        endY = Math.max(minY, Math.min(endY, maxY));

        swiper.swipe(startX, startY, endY);
    }
}
