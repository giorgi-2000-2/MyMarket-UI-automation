package web.steps;
import com.google.inject.Inject;
import core.steps.IPageNavigator;
import core.testdata.CategoryTestCase;
import web.pages.advertisement.AdvertisementPage;
import web.pages.login.DialogContent;
import web.pages.login.LoginPage;
import core.config.IUserConfig;
import core.reporter.IReportTree;
import core.reporter.ReportMessages;


public class PageNavigator  implements IPageNavigator {
    private final IUserConfig userConfig;
    private final WebAssertSteps assertSteps;
    private final DialogContent content;
    private final AdvertisementPage advertisementPage;
    private final IReportTree reporter;
    private final LoginPage loginPage;
    @Inject
    public PageNavigator(DialogContent content, IReportTree reporter, IUserConfig userConfig, WebAssertSteps assertSteps, AdvertisementPage advertisementPage, LoginPage loginPage) {
        this.assertSteps = assertSteps;
        this.content = content;
        this.reporter = reporter;
        this.userConfig = userConfig;
        this.advertisementPage = advertisementPage;
        this.loginPage = loginPage;
    }

    public void navigationMainCheck(CategoryTestCase testCase){
        reporter.info(ReportMessages.CLICK_SECTION_BUTTON.format(testCase.getSection()));
        advertisementPage.getCategoryDropdown().clickCategory(testCase.getSection());
        assertSteps.checkMainAsserts(testCase);
    }

    public void navigateToAdvertisementPage()  {
        assertSteps.navigationToAdvertisementPageAsserts();
        content.closeDialogWindow();

    }

    public void loginAndNavigateToAdvertisementPage( ) {
        reporter.info(ReportMessages.LOGIN.get());
        loginPage.login(userConfig.loginMail(), userConfig.loginPassword());
        reporter.info(ReportMessages.NAVIGATE_TO_AD_PAGE.get());
        advertisementPage.clickAdvertisementBtn();
        navigateToAdvertisementPage();
    }
}
