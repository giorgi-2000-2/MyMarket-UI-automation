package web.webasserts;
import com.google.inject.Inject;
import core.annotations.TestScoped;
import core.asserts.SoftVerifier;
import core.reporter.texts.StepNames;
import core.testdata.CategoryTestCase;
import core.reporter.IReportNode;
import web.config.ISectionUrls;
import web.pages.basepage.PageAction;

import static core.reporter.NodeKey.CLICK_BTN_CHECK;
@TestScoped
public class CategoryAsserts {
    private final PageAction pageAction;
    private final SoftVerifier assertManager;
    private final IReportNode reportNode;
    private final ISectionUrls sectionUrls;
    @Inject
    public CategoryAsserts(PageAction pageAction,
                           SoftVerifier assertManager,
                           IReportNode reportNode,
                           ISectionUrls sectionUrls) {
        this.pageAction = pageAction;
        this.assertManager = assertManager;
        this.reportNode = reportNode;
        this.sectionUrls = sectionUrls;
    }

    public void assertAfterClickingSection(CategoryTestCase testCase) {
        reportNode.createNamedNode(CLICK_BTN_CHECK, StepNames.AFTER_SECTION_CLICK.format(testCase.getSection().label()));

        String actualUrl = pageAction.getCurrentURL().replace("www.", "");
        String expectedUrl = sectionUrls.urlOf(testCase.getSection()).replace("www.", "");

        assertManager.check(CLICK_BTN_CHECK, StepNames.CHECK_URL.get())
                .expected(expectedUrl)
                .actual(actualUrl);
    }
}