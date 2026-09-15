package org.example.manager;
import com.google.inject.Inject;
import org.example.pages.advertisement.IAdvertisementPage;
import org.example.pages.basepage.IBasePage;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CategoryNavigator implements ICategoryNavigator {

    private final IAdvertisementPage page;
    private final IBasePage basePage;
    @Inject
    public CategoryNavigator(IAdvertisementPage page, IBasePage basePage) {
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