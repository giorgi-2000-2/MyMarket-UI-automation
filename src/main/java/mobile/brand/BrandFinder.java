package mobile.brand;

import com.google.inject.Inject;
import core.reporter.IReportTree;
import core.reporter.texts.ErrorMessages;
import mobile.category.screen.IScreenReader;
import mobile.category.scroll.IPageScroller;
import org.openqa.selenium.WebElement;
import core.annotations.TestScoped;

@TestScoped
public class BrandFinder {

    private final IPageScroller IPageScroller;
    private final IScreenReader IScreenReader;
    private final BrandNavigator brandNavigator;
    private final IReportTree reportTree;

    @Inject
    public BrandFinder(IPageScroller IPageScroller,
                       IScreenReader IScreenReader,
                       BrandNavigator brandNavigator, IReportTree reportTree) {
        this.IPageScroller = IPageScroller;
        this.IScreenReader = IScreenReader;
        this.brandNavigator = brandNavigator;
        this.reportTree = reportTree;
    }

    public boolean findBrandDropdown() {
        boolean found = false;
        boolean characteristicsOpened = false;

        try {
            WebElement Characteristic = IPageScroller.scrollToField("მახასიათებლები *");
            if (Characteristic != null) {
                IScreenReader.read();
                Characteristic.click();
                characteristicsOpened = true;

                WebElement brandDropdown = IPageScroller.scrollToField("ბრენდი *");
                if (brandDropdown != null && brandDropdown.isDisplayed()) {
                    found = true;
                } else {
                    reportTree.info(ErrorMessages.BRAND_FIELD_NOT_FOUND_RETURNING.get());
                }
            } else {
                reportTree.info(ErrorMessages.CHARACTERISTICS_NOT_FOUND_RETURNING.get());
            }
        } catch (Exception e) {
            reportTree.info(ErrorMessages.BRAND_SEARCH_ERROR.format(e.getMessage()));
        }

        if (!found) {
            brandNavigator.returnToForm(characteristicsOpened);
        }
        return found;
    }
}