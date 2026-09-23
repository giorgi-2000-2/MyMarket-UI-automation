package mobile.brand;

import com.google.inject.Inject;
import core.reporter.IReportTree;
import core.reporter.texts.ErrorMessages;
import mobile.category.screen.ScreenReader;
import mobile.category.scroll.PageScroller;
import org.openqa.selenium.WebElement;
import core.annotations.TestScoped;

@TestScoped
public class BrandFinder {

    private final PageScroller pageScroller;
    private final ScreenReader screenReader;
    private final BrandNavigator brandNavigator;
    private final IReportTree reportTree;

    @Inject
    public BrandFinder(PageScroller pageScroller,
                       ScreenReader screenReader,
                       BrandNavigator brandNavigator, IReportTree reportTree) {
        this.pageScroller = pageScroller;
        this.screenReader = screenReader;
        this.brandNavigator = brandNavigator;
        this.reportTree = reportTree;
    }

    public boolean findBrandDropdown() {
        boolean found = false;
        boolean characteristicsOpened = false;

        try {
            WebElement Characteristic = pageScroller.scrollToField("მახასიათებლები *");
            if (Characteristic != null) {
                screenReader.read();
                Characteristic.click();
                characteristicsOpened = true;

                WebElement brandDropdown = pageScroller.scrollToField("ბრენდი *");
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