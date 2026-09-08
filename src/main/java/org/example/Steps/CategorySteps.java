package org.example.steps;

import org.example.pages.basepage.BasePage;
import org.example.dataprovider.CategoryTestCase;
import org.example.asserts.IAssertManager;
import org.example.pages.advertisement.AdvertisementPage;
import org.example.utils.reporter.IReportNode;
import org.example.utils.reporter.NodeKey;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import static org.example.utils.reporter.NodeKey.CATEGORY;

public class CategorySteps {
    private final AdvertisementPage advertisementPage;
    private final BasePage basePage;
    private final IAssertManager assertManager;
    private final IReportNode report;

    public CategorySteps(AdvertisementPage advertisementPage, BasePage basePage, IAssertManager assertManager, IReportNode report) {
        this.advertisementPage = advertisementPage;
        this.basePage = basePage;
        this.assertManager = assertManager;
        this.report = report;
    }

    public void processEmptyCategory(SoftAssert softAssert, NodeKey nodeName, CategoryTestCase testCase) {
        report.createNamedNode(CATEGORY, testCase.getSection() + "-ის ნახვა");
        WebElement title = advertisementPage.getCategoryDropdown().getDropdownTitleText();
        assertManager.assertWithLog(softAssert, nodeName, basePage.titleText(title),
                advertisementPage.getTitleComponent().getPreviewTitleAfterChange(), " შედარება ");
        basePage.waitClick(advertisementPage.getCategoryDropdown().getDropDownCategory());
    }
}