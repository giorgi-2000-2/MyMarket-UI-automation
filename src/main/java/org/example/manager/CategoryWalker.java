package org.example.manager;
import org.example.pages.basepage.BasePage;
import org.example.pages.advertisement.AdvertisementPage;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CategoryWalker {

    private final AdvertisementPage page;
private final BasePage basePage;
    public CategoryWalker(AdvertisementPage page, BasePage basePage) {
        this.page = page;
        this.basePage = basePage;
    }

    public void walk(IAction leafAction)   {
        page.getCategoryDropdown().clickDropdown();
        walkRecursive(leafAction);
    }

    private void walkRecursive(IAction action)   {
        List<WebElement> options = page.getCategoryDropdown().getOptions();
        int startIndex = page.getCategoryDropdown().isBackButtonPresent() ? 1 : 0;
        for (int i = startIndex; i < options.size(); i++) {
            basePage.waitClick(page.getCategoryDropdown().getOptions().get(i));
            if (isLeaf()) {
                action.execute();
                page.getCategoryDropdown().clickDropdown();
            } else {
                walkRecursive(action);
            }
        }
        page.getCategoryDropdown().clickBackIfPresent();
    }




//    private void walkRecursives(IAction action)   {
//        List<WebElement> options = page.getCategoryDropdown().getOptions();
//        for (int i = 1; i < options.size(); i++) {
//            basePage.waitClick(page.getCategoryDropdown().getOptions().get(i));
//            if (isLeaf()) {
//                action.execute();
//                page.getCategoryDropdown().clickDropdown();
//
//            } else {
//                walkRecursives(action);
//            }
//        }
//        page.getCategoryDropdown().clickBackIfPresent();
//    }


    private boolean isLeaf() {
        return page.getCategoryDropdown().getMainElements().isEmpty();
    }
}