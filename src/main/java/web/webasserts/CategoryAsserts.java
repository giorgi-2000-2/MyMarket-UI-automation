package web.webasserts;

import com.google.inject.Inject;
import core.annotations.TestScoped;
import core.asserts.SoftVerifier;
import core.testdata.CategoryTestCase;
import web.pages.basepage.BasePage;
import core.config.IBtnUrl;
import core.reporter.IReportNode;
import core.reporter.ReportMessages;

import static core.reporter.NodeKey.CLICK_BTN_CHECK;
@TestScoped
public class CategoryAsserts {
    private final BasePage basePage;
    private final SoftVerifier assertManager;
    private final IReportNode reportNode;
    private final IBtnUrl btnUrlConfig;
    @Inject
    public CategoryAsserts(BasePage basePage,
                           SoftVerifier assertManager,
                           IReportNode reportNode,
                           IBtnUrl btnUrlConfig) {
        this.basePage = basePage;
        this.assertManager = assertManager;
        this.reportNode = reportNode;
        this.btnUrlConfig = btnUrlConfig;
    }

    public void assertAfterClickingSection(CategoryTestCase testCase) {
        reportNode.createNamedNode(CLICK_BTN_CHECK, ReportMessages.AFTER_SECTION_CLICK.format(testCase.getSection()));

        String actualUrl = basePage.getCurrentURL().replace("www.", "");
        String expectedUrl = btnUrlConfig.btnUrl(testCase.getSection()).replace("www.", "");

        assertManager.check(CLICK_BTN_CHECK, ReportMessages.CHECK_URL.get())
                .expected(expectedUrl)
                .actual(actualUrl);
    }
}