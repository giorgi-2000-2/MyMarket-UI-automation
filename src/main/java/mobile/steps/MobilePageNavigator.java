package mobile.steps;
import com.google.inject.Inject;
import core.reporter.IReportTree;
import core.reporter.texts.StepNames;
import core.steps.IAdvertisementBusinessFlow;
import mobile.mobileasserts.MobileAssertSteps;
import mobile.pages.MainPage;
import mobile.pages.MobileAdvertisementPage;
import core.annotations.TestScoped;
import core.testdata.CategoryTestCase;
import core.testdata.Section;

@TestScoped
public class MobilePageNavigator implements IAdvertisementBusinessFlow {
    private final MainPage mainPage;
    private final MobileAdvertisementPage mobileAdvertisementPage;
    private final MobileAssertSteps assertSteps;
    private final IReportTree reporter;


    @Inject
    public MobilePageNavigator(MainPage mainPage,
                               MobileAdvertisementPage mobileAdvertisementPage,
                               MobileAssertSteps assertSteps,
                               IReportTree reporter) {
        this.mainPage = mainPage;

        this.mobileAdvertisementPage = mobileAdvertisementPage;
        this.assertSteps = assertSteps;
        this.reporter = reporter;
    }
    @Override
    public void navigateToAdvertisementPage() {
        openProfileAndCheckUser();
        openAddAnnouncement();
    }


    @Override
    public void navigationChecks(CategoryTestCase testCase) {
        Section section = testCase.getSection();
        reporter.info(StepNames.CLICK_SECTION_BUTTON.format(section.label()));
        mobileAdvertisementPage.selectSection(section);
    }

    public void openProfileAndCheckUser() {
        mainPage.openProfile();
        assertSteps.profileAsserts();
    }

    public void openAddAnnouncement() {
        reporter.info(StepNames.NAVIGATE_TO_AD_PAGE.get());
        mainPage.openAddAnnouncement();
    }

}
