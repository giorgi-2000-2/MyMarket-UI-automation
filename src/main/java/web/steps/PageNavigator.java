package web.steps;
import com.google.inject.Inject;
import core.annotations.TestScoped;
import core.reporter.texts.StepNames;
import core.steps.IAdvertisementBusinessFlow;
import core.testdata.CategoryTestCase;
import web.pages.advertisement.AdvertisementPage;
import web.pages.login.DialogContent;
import web.pages.login.LoginPage;
import core.config.IUserConfig;
import core.reporter.IReportTree;

@TestScoped
public class PageNavigator implements IAdvertisementBusinessFlow {
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
    @Override
    public void navigationChecks(CategoryTestCase testCase){
        reporter.info(StepNames.CLICK_SECTION_BUTTON.format(testCase.getSection().label()));
        advertisementPage.getCategoryDropdown().clickCategory(testCase.getSection());
        assertSteps.checkMainAsserts(testCase);
    }

    private void verifyAdPageAndCloseDialog()  {
        assertSteps.navigationToAdvertisementPageAsserts();
        content.closeDialogWindow();

    }
    @Override
    public void navigateToAdvertisementPage( ) {
        reporter.info(StepNames.LOGIN.get());
        loginPage.login(userConfig.loginMail(), userConfig.loginPassword());
        reporter.info(StepNames.NAVIGATE_TO_AD_PAGE.get());
        advertisementPage.clickAdvertisementBtn();
        verifyAdPageAndCloseDialog();
    }

}