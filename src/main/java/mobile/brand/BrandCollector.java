package mobile.brand;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import mobile.category.scroll.PageScroller;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import core.annotations.TestScoped;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@TestScoped
public class BrandCollector {

    private final AppiumDriver driver;
    private final PageScroller pageScroller;

    @Inject
    public BrandCollector(AppiumDriver driver, PageScroller pageScroller) {
        this.driver = driver;
        this.pageScroller = pageScroller;
    }

    public List<String> collectBrandsFromDropdown() {
        Set<String> collectedBrands = new LinkedHashSet<>();
        int stagnant = 0;

        while (stagnant < 2) {
            int initialSize = collectedBrands.size();
            List<WebElement> brandElements = driver.findElements(By.xpath("//android.view.View[@content-desc]"));

            for (WebElement el : brandElements) {
                String desc = el.getAttribute("content-desc");
                if (desc != null) {
                    desc = desc.trim();

                    if (!desc.isEmpty() && !desc.equals("ბრენდები") && !desc.equals("-")) {
                        collectedBrands.add(desc);
                    }
                }
            }

            if (collectedBrands.size() == initialSize) {
                stagnant++;
            } else {
                stagnant = 0;
            }

            if (stagnant < 2) {
                pageScroller.scrollDown();
            }
        }

        return new ArrayList<>(collectedBrands);
    }
}