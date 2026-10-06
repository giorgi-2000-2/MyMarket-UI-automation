package web.steps;
import com.google.inject.Inject;
import core.asserts.SoftVerifier;
import core.annotations.TestScoped;
import core.reporter.texts.AssertMessages;
import core.reporter.texts.StepNames;
import web.pages.advertisement.AdvertisementPage;
import web.pages.advertisement.CategoryDropdownComponent;
import core.reporter.IReportNode;
import core.reporter.ReportStatus;
import web.pages.basepage.JavaScriptHelper;
import web.pages.basepage.PageAction;

import java.util.List;

import static core.reporter.NodeKey.DROPDOWN;
@TestScoped
public class CategoryNavigationSteps {

    private final AdvertisementPage advertisementPage;
    private final PageAction pageAction;
    private final SoftVerifier assertManager;
    private final IReportNode report;
    @Inject
    public CategoryNavigationSteps(AdvertisementPage advertisementPage, PageAction pageAction,
                                   SoftVerifier assertManager, IReportNode report ) {
        this.advertisementPage = advertisementPage;
        this.pageAction = pageAction;
        this.assertManager = assertManager;
        this.report = report;
    }

    public void verifyBackClickRestoresList() {
        report.createNamedNode(DROPDOWN, StepNames.BACK_NAVIGATION.get());
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
            report.logToNode(DROPDOWN, ReportStatus.INFO, AssertMessages.FINAL_CATEGORY_NO_BACK.format(category));
            dropdown().clickDropdown();
            return;
        }

        List<String> restored = goBack();

        assertManager.check(DROPDOWN, AssertMessages.BACK_RESTORE_CHECK.format(category))
                .expected(join(levelLabels))
                .actual(join(restored));


    }


    private void openCategory(int index) {
        pageAction.waitClick(dropdown().getOptions().get(index));
    }

    private List<String> goBack() {
        dropdown().clickBackIfPresent();
        return dropdown().getOptionLabels();
    }

    private String join(List<String> labels) { return String.join(" | ", labels); }

    private CategoryDropdownComponent dropdown() { return advertisementPage.getCategoryDropdown(); }
}