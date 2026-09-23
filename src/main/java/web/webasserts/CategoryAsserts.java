package web.webasserts;
import com.google.inject.Inject;
import core.annotations.TestScoped;
import core.asserts.SoftVerifier;
import core.config.IUrlConfig;
import core.reporter.texts.StepNames;
import core.testdata.CategoryTestCase;
import web.pages.basepage.BasePage;
import core.reporter.IReportNode;

import static core.reporter.NodeKey.CLICK_BTN_CHECK;
@TestScoped
public class CategoryAsserts {
    private final BasePage basePage;
    private final SoftVerifier assertManager;
    private final IReportNode reportNode;
    private final IUrlConfig btnUrlConfig;
    @Inject
    public CategoryAsserts(BasePage basePage,
                           SoftVerifier assertManager,
                           IReportNode reportNode,
                           IUrlConfig btnUrlConfig) {
        this.basePage = basePage;
        this.assertManager = assertManager;
        this.reportNode = reportNode;
        this.btnUrlConfig = btnUrlConfig;
    }

    public void assertAfterClickingSection(CategoryTestCase testCase) {
        reportNode.createNamedNode(CLICK_BTN_CHECK, StepNames.AFTER_SECTION_CLICK.format(testCase.getSection()));

        String actualUrl = basePage.getCurrentURL().replace("www.", "");
        String expectedUrl = btnUrlConfig.btnUrl(testCase.getSection()).replace("www.", "");

        assertManager.check(CLICK_BTN_CHECK, StepNames.CHECK_URL.get())
                .expected(expectedUrl)
                .actual(actualUrl);
    }
}