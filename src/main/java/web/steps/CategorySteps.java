package web.steps;
import com.google.inject.Inject;
import core.asserts.SoftVerifier;
import core.annotations.TestScoped;
import core.reporter.texts.StepNames;
import core.testdata.CategoryTestCase;
import web.pages.advertisement.AdvertisementPage;
import core.reporter.IReportNode;
import core.reporter.NodeKey;
import org.openqa.selenium.WebElement;
import web.pages.basepage.BaseTitleComponent;
import web.pages.basepage.PageAction;

import static core.reporter.NodeKey.CATEGORY;
@TestScoped
public class CategorySteps  {
    private final AdvertisementPage advertisementPage;
    private final BaseTitleComponent titleText;
    private final SoftVerifier assertManager;
    private final IReportNode report;
    private final PageAction pageAction;
    @Inject
    public CategorySteps(AdvertisementPage advertisementPage, BaseTitleComponent titleText, SoftVerifier assertManager, IReportNode report, PageAction pageAction) {
        this.advertisementPage = advertisementPage;
        this.titleText = titleText;
        this.assertManager = assertManager;
        this.report = report;
        this.pageAction = pageAction;
    }

    public void processEmptyCategory( NodeKey nodeName, CategoryTestCase testCase) {
        report.createNamedNode(CATEGORY, StepNames.SECTION_VIEW.format(testCase.getSection().label()));
        WebElement title = advertisementPage.getCategoryDropdown().getDropdownTitleText();

        assertManager.check(nodeName,StepNames.TITLE_COMPARE.get())
                .expected(advertisementPage.getTitleComponent().getPreviewTitleAfterChange())
                .actual(titleText.titleText(title));

        pageAction.waitClick(advertisementPage.getCategoryDropdown().getDropDownCategory());
    }
}