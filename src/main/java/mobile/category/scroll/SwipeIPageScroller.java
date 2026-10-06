package mobile.category.scroll;

import com.google.inject.Inject;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import mobile.category.driver.ScreenGeometry;
import mobile.category.driver.ISwiper;
import mobile.category.parsing.PageSourceParser;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.List;

public class SwipeIPageScroller implements IPageScroller {
    private final AppiumDriver driver;
    private final ScreenGeometry screenGeometry;
    private final PageSourceParser pageSourceParser;
    private final ISwiper ISwiper;

    @Inject
    public SwipeIPageScroller(AppiumDriver driver, ScreenGeometry screenGeometry,
                              PageSourceParser pageSourceParser, ISwiper ISwiper) {
        this.driver = driver;
        this.screenGeometry = screenGeometry;
        this.pageSourceParser = pageSourceParser;
        this.ISwiper = ISwiper;
    }

    @Override
    public void goBackToCategories() {
        Dimension screen = screenGeometry.size();
        int startX = screen.getWidth() / 2;
        int startY = (int) (screen.getHeight() * 0.3);
        int endY = (int) (screen.getHeight() * 0.8);

        for (int i = 0; i < 3; i++) {
            ISwiper.swipe(startX, startY, endY);
        }
    }

    @Override
    public WebElement scrollToField(String prefix) {
        Dimension screen = screenGeometry.size();
        int startX = screen.getWidth() / 2;
        int startY = (int) (screen.getHeight() * 0.7);
        int endY = (int) (screen.getHeight() * 0.3);
        for (int i = 0; i < 2; i++) {
            Document doc = pageSourceParser.parse();
            NodeList all = doc.getElementsByTagName("*");

            for (int j = 0; j < all.getLength(); j++) {
                Element el = (Element) all.item(j);
                String desc = el.getAttribute("content-desc");
                if (desc != null) {
                    desc = desc.trim();
                    if (desc.startsWith(prefix)) {
                        List<WebElement> found = driver.findElements(AppiumBy.accessibilityId(desc));
                        if (!found.isEmpty()) {
                            return found.get(0);
                        }
                    }
                }
            }
            ISwiper.swipe(startX, startY, endY);
        }
        return null;
    }

    @Override
    public void scrollDown() {
        Dimension screen = screenGeometry.size();
        int startX = screen.getWidth() / 2;
        int startY = (int) (screen.getHeight() * 0.7);
        int endY = (int) (screen.getHeight() * 0.3);
        ISwiper.swipe(startX, startY, endY);
    }
}
