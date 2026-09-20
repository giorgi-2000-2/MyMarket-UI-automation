package web.manager;
import com.google.inject.Inject;
import web.pages.advertisement.AdvertisementPage;
import web.pages.basepage.BasePage;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CategoryNavigator implements ICategoryNavigator {

    private final AdvertisementPage page;
    private final BasePage basePage;
    @Inject
    public CategoryNavigator(AdvertisementPage page, BasePage basePage) {
        this.page = page;
        this.basePage = basePage;
    }

    public void openDropdown() {
        page.getCategoryDropdown().clickDropdown();
    }

    public void clickOption(int index) {
        List<WebElement> options = page.getCategoryDropdown().getOptions();
        basePage.waitClick(options.get(index));
    }

    public void goBack() {
        page.getCategoryDropdown().clickBackIfPresent();
    }

    public boolean isLeaf() {
        return page.getCategoryDropdown().getMainElements().isEmpty();
    }

    public boolean isBackButtonPresent() {
        return page.getCategoryDropdown().isBackButtonPresent();
    }

    public int optionsCount() {
        return page.getCategoryDropdown().getOptions().size();
    }

    public int startIndex() {
        return isBackButtonPresent() ? 1 : 0;
    }
}