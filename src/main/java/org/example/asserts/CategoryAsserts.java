package org.example.asserts;

import com.google.inject.Inject;
import org.example.dataprovider.CategoryTestCase;
import org.example.pages.basepage.IBasePage;
import org.example.utils.config.IBtnUrl;
import org.example.utils.reporter.IReportNode;

import static org.example.utils.reporter.NodeKey.CLICK_BTN_CHECK;

public class CategoryAsserts {
    private final IBasePage basePage;
    private final ISoftVerifier assertManager;
    private final IReportNode reportNode;
    private final IBtnUrl btnUrlConfig;
    @Inject
    public CategoryAsserts(IBasePage basePage,
                           ISoftVerifier assertManager,
                           IReportNode reportNode,
                           IBtnUrl btnUrlConfig) {
        this.basePage = basePage;
        this.assertManager = assertManager;
        this.reportNode = reportNode;
        this.btnUrlConfig = btnUrlConfig;
    }

    public void assertAfterClickingSection(CategoryTestCase testCase) {
        reportNode.createNamedNode(CLICK_BTN_CHECK,
                testCase.getSection() + " ღილაკზე დაჭერის შემდეგ შემოწმება");

        String actualUrl = basePage.getCurrentURL().replace("www.", "");
        String expectedUrl = btnUrlConfig.btnUrl(testCase.getSection()).replace("www.", "");

        assertManager.check(CLICK_BTN_CHECK, "მისამართის შემოწმება")
                .expected(expectedUrl)
                .actual(actualUrl);
    }
}