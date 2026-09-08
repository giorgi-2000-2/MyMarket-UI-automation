package org.example.steps;
import org.example.asserts.IAssertManager;
import org.example.pages.basepage.BasePage;
import org.example.pages.advertisement.AdvertisementPage;
import org.example.pages.advertisement.CategoryDropdownComponent;
import org.example.utils.reporter.IReportNode;
import org.example.utils.reporter.ReportStatus;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.example.utils.reporter.NodeKey.DROPDOWN;

public class CategoryNavigationSteps {

    private final AdvertisementPage advertisementPage;
    private final BasePage basePage;
    private final IAssertManager assertManager;
    private final IReportNode report;

    public CategoryNavigationSteps(AdvertisementPage advertisementPage, BasePage basePage,
                                   IAssertManager assertManager, IReportNode report) {
        this.advertisementPage = advertisementPage;
        this.basePage = basePage;
        this.assertManager = assertManager;
        this.report = report;
    }

    public void verifyBackClickRestoresList(SoftAssert softAssert) {
        report.createNamedNode(DROPDOWN, "უკან დაბრუნების ნავიგაცია");
        dropdown().clickDropdown();

        List<String> levelLabels = dropdown().getOptionLabels();
        int startIndex = dropdown().isBackButtonPresent() ? 1 : 0;

        for (int i = startIndex; i < levelLabels.size(); i++) {
            verifyOneCategory(softAssert, i, levelLabels);
        }
    }


    private void verifyOneCategory(SoftAssert softAssert, int index, List<String> levelLabels) {
        String category = levelLabels.get(index);
        openCategory(index);

        if (!dropdown().isOpen()) {
            report.logToNode(DROPDOWN, ReportStatus.INFO, category + " — საბოლოო კატეგორიაა, დასაბრუნებელი არაფერია");
            dropdown().clickDropdown();
            return;
        }

        List<String> restored = goBack();

        assertManager.assertWithLog(softAssert, DROPDOWN,
                join(restored), join(levelLabels),
                category + " — უკან დაბრუნება");
    }


    private void openCategory(int index) {
        basePage.waitClick(dropdown().getOptions().get(index));
    }

    private List<String> goBack() {
        dropdown().clickBackIfPresent();
        return dropdown().getOptionLabels();
    }

    private String join(List<String> labels) { return String.join(" | ", labels); }

    private CategoryDropdownComponent dropdown() { return advertisementPage.getCategoryDropdown(); }
}