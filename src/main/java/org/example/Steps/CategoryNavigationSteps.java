package org.example.steps;
import com.google.inject.Inject;
import org.example.asserts.ISoftVerifier;
import org.example.asserts.VerificationResult;
import org.example.pages.advertisement.IAdvertisementPage;
import org.example.pages.advertisement.CategoryDropdownComponent;
import org.example.pages.basepage.IBasePage;
import org.example.utils.reporter.IReportNode;
import org.example.utils.reporter.ReportStatus;

import java.util.List;

import static org.example.utils.reporter.NodeKey.DROPDOWN;

public class CategoryNavigationSteps {

    private final IAdvertisementPage advertisementPage;
    private final IBasePage basePage;
    private final ISoftVerifier assertManager;
    private final IReportNode report;
    @Inject
    public CategoryNavigationSteps(IAdvertisementPage advertisementPage, IBasePage basePage,
                                   ISoftVerifier assertManager, IReportNode report) {
        this.advertisementPage = advertisementPage;
        this.basePage = basePage;
        this.assertManager = assertManager;
        this.report = report;
    }

    public void verifyBackClickRestoresList() {
        report.createNamedNode(DROPDOWN, "უკან დაბრუნების ნავიგაცია");
        dropdown().clickDropdown();

        List<String> levelLabels = dropdown().getOptionLabels();
        int startIndex = dropdown().isBackButtonPresent() ? 1 : 0;

        for (int i = startIndex; i < levelLabels.size(); i++) {
            verifyOneCategory( i, levelLabels);
        }
    }


    private void verifyOneCategory(int index, List<String> levelLabels) {
        String category = levelLabels.get(index);
        openCategory(index);

        if (!dropdown().isOpen()) {
            report.logToNode(DROPDOWN, ReportStatus.INFO, category + " — საბოლოო კატეგორიაა, დასაბრუნებელი არაფერია");
            dropdown().clickDropdown();
            return;
        }

        List<String> restored = goBack();

        assertManager.check(DROPDOWN,  category + " — უკან დაბრუნება")
                .expected(join(levelLabels))
                .actual(join(restored));


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