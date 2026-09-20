package web.steps;
import com.google.inject.Inject;
import core.asserts.SoftVerifier;
import core.annotations.TestScoped;
import core.testdata.CategoryTestCase;
import web.pages.advertisement.AdvertisementPage;
import web.pages.basepage.BasePage;
import core.reporter.IReportNode;
import core.reporter.NodeKey;
import core.reporter.ReportMessages;
import org.openqa.selenium.WebElement;

import static core.reporter.NodeKey.CATEGORY;
@TestScoped
public class CategorySteps  {
    private final AdvertisementPage advertisementPage;
    private final BasePage basePage;
    private final SoftVerifier assertManager;
    private final IReportNode report;
    @Inject
    public CategorySteps(AdvertisementPage advertisementPage, BasePage basePage, SoftVerifier assertManager, IReportNode report) {
        this.advertisementPage = advertisementPage;
        this.basePage = basePage;
        this.assertManager = assertManager;
        this.report = report;
    }

    public void processEmptyCategory( NodeKey nodeName, CategoryTestCase testCase) {
        report.createNamedNode(CATEGORY, ReportMessages.SECTION_VIEW.format(testCase.getSection()));
        WebElement title = advertisementPage.getCategoryDropdown().getDropdownTitleText();

        assertManager.check(nodeName,ReportMessages.TITLE_COMPARE.get())
                        .expected(advertisementPage.getTitleComponent().getPreviewTitleAfterChange())
                                .actual(basePage.titleText(title));

        basePage.waitClick(advertisementPage.getCategoryDropdown().getDropDownCategory());
    }
}