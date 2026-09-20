package mobile.steps;

import com.google.inject.Inject;
import core.config.properties.CategoryNameBtn;
import core.reporter.IReportTree;
import core.reporter.ReportMessages;
import core.steps.IPageNavigator;
import mobile.mobileasserts.MobileAssertSteps;
import mobile.pages.AdvertisementPage;
import mobile.pages.MainPage;
import mobile.pages.MobileAdvertisementPage;
import org.openqa.selenium.WebElement;
import core.annotations.TestScoped;
import core.testdata.CategoryTestCase;

@TestScoped
public class MobilePageNavigator implements IPageNavigator {
    private final MainPage mainPage;
    private final AdvertisementPage advertisementPage;
    private final MobileAdvertisementPage mobileAdvertisementPage;
    private final MobileAssertSteps assertSteps;
    private final IReportTree reporter;

    @Inject
    public MobilePageNavigator(MainPage mainPage,
                               AdvertisementPage advertisementPage,
                               MobileAdvertisementPage mobileAdvertisementPage,
                               MobileAssertSteps assertSteps,
                               IReportTree reporter) {
        this.mainPage = mainPage;
        this.advertisementPage = advertisementPage;
        this.mobileAdvertisementPage = mobileAdvertisementPage;
        this.assertSteps = assertSteps;
        this.reporter = reporter;
    }

    public void navigateToAdvertisementPage() {
        openProfileAndCheckUser();
        openAddAnnouncement();
    }

    @Override
    public void loginAndNavigateToAdvertisementPage() {
        openProfileAndCheckUser();
    }


    public void openProfileAndCheckUser() {
        advertisementPage.waitToBevisible(mainPage.myProfileBtn);
        mainPage.myProfileBtn.click();
        assertSteps.profileAsserts();
    }

    public void openAddAnnouncement() {
        reporter.info(ReportMessages.NAVIGATE_TO_AD_PAGE.get());

        advertisementPage.waitToBevisible(mainPage.MyOfficeBtn);
        mainPage.MyOfficeBtn.click();

        advertisementPage.waitToBevisible(mainPage.addAnnouncementBtn);
        mainPage.addAnnouncementBtn.click();
    }

    public void navigationMainCheck(CategoryTestCase testCase) {
        CategoryNameBtn section = testCase.getSection();
        reporter.info(ReportMessages.CLICK_SECTION_BUTTON.format(section.getPath()));

        WebElement sectionButton = mobileAdvertisementPage.sectionButton(section);
        advertisementPage.waitToBevisible(sectionButton);
        sectionButton.click();

        advertisementPage.waitToBevisible(advertisementPage.categoryDropDown);
    }
}
