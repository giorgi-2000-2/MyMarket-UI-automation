package web.manager;
import com.google.inject.Inject;
import web.pages.advertisement.AdvertisementPage;
import org.openqa.selenium.WebElement;
import web.pages.basepage.JavaScriptHelper;
import web.pages.basepage.PageAction;

import java.util.List;

public class CategoryNavigator implements ICategoryNavigator {

    private final AdvertisementPage page;
    private final PageAction pageAction;
    @Inject
    public CategoryNavigator(AdvertisementPage page , PageAction pageAction) {
        this.page = page;
        this.pageAction = pageAction;
    }

    public void openDropdown() {
        page.getCategoryDropdown().clickDropdown();
    }

    public void clickOption(int index) {
        List<WebElement> options = page.getCategoryDropdown().getOptions();
        pageAction.waitClick(options.get(index));
    }
    public String optionsCategory(int index) {
        List<WebElement> options = page.getCategoryDropdown().getOptions();
        return options.get(index).getText();
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