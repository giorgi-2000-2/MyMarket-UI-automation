package web.steps;
import com.google.inject.Inject;
import core.asserts.SoftVerifier;
import core.annotations.TestScoped;
import web.pages.advertisement.AdvertisementPage;
import web.pages.advertisement.CategoryDropdownComponent;
import web.pages.basepage.BasePage;
import core.reporter.IReportNode;
import core.reporter.ReportMessages;
import core.reporter.ReportStatus;

import java.util.List;

import static core.reporter.NodeKey.DROPDOWN;
@TestScoped
public class CategoryNavigationSteps {

    private final AdvertisementPage advertisementPage;
    private final BasePage basePage;
    private final SoftVerifier assertManager;
    private final IReportNode report;
    @Inject
    public CategoryNavigationSteps(AdvertisementPage advertisementPage, BasePage basePage,
                                   SoftVerifier assertManager, IReportNode report) {
        this.advertisementPage = advertisementPage;
        this.basePage = basePage;
        this.assertManager = assertManager;
        this.report = report;
    }

    public void verifyBackClickRestoresList() {
        report.createNamedNode(DROPDOWN, ReportMessages.BACK_NAVIGATION.get());
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
            report.logToNode(DROPDOWN, ReportStatus.INFO, ReportMessages.FINAL_CATEGORY_NO_BACK.format(category));
            dropdown().clickDropdown();
            return;
        }

        List<String> restored = goBack();

        assertManager.check(DROPDOWN, ReportMessages.BACK_RESTORE_CHECK.format(category))
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