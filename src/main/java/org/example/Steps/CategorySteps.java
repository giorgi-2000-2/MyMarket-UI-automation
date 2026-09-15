package org.example.steps;
import com.google.inject.Inject;
import org.example.asserts.ISoftVerifier;
import org.example.asserts.VerificationResult;
import org.example.pages.advertisement.IAdvertisementPage;
import org.example.dataprovider.CategoryTestCase;
import org.example.pages.basepage.IBasePage;
import org.example.utils.reporter.IReportNode;
import org.example.utils.reporter.NodeKey;
import org.openqa.selenium.WebElement;

import static org.example.utils.reporter.NodeKey.CATEGORY;

public class CategorySteps {
    private final IAdvertisementPage advertisementPage;
    private final IBasePage basePage;
    private final ISoftVerifier assertManager;
    private final IReportNode report;
    @Inject
    public CategorySteps(IAdvertisementPage advertisementPage, IBasePage basePage, ISoftVerifier assertManager, IReportNode report) {
        this.advertisementPage = advertisementPage;
        this.basePage = basePage;
        this.assertManager = assertManager;
        this.report = report;
    }

    public void processEmptyCategory( NodeKey nodeName, CategoryTestCase testCase) {
        report.createNamedNode(CATEGORY, testCase.getSection() + "-ის ნახვა");
        WebElement title = advertisementPage.getCategoryDropdown().getDropdownTitleText();

        assertManager.check(nodeName,"\" შედარება \"")
                        .expected(advertisementPage.getTitleComponent().getPreviewTitleAfterChange())
                                .actual(basePage.titleText(title));

        basePage.waitClick(advertisementPage.getCategoryDropdown().getDropDownCategory());
    }
}