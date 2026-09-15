package org.example.steps;
import com.google.inject.Inject;
import org.example.asserts.AssertSteps;
import org.example.dataprovider.CategoryTestCase;
import org.example.di.modules.TestContext;
import org.example.pages.advertisement.IAdvertisementPage;
import org.example.pages.login.LoginPage;
import org.example.utils.config.IUserConfig;
import org.example.utils.reporter.IReportTree;


public class PageNavigator  implements IPageNavigator{
    private final IUserConfig userConfig;
    private final AssertSteps assertSteps;
    private final LoginPage loginPage;
    private final IAdvertisementPage advertisementPage;
    private final IReportTree reporter;
    @Inject private TestContext context;
    @Inject
    public PageNavigator(LoginPage loginPage, IReportTree reporter, IUserConfig userConfig, AssertSteps assertSteps, IAdvertisementPage advertisementPage) {
        this.assertSteps = assertSteps;
        this.loginPage = loginPage;
        this.reporter = reporter;
        this.userConfig = userConfig;
        this.advertisementPage = advertisementPage;
    }

    public void navigationMainCheck(CategoryTestCase testCase){
        advertisementPage.getCategoryDropdown().clickCategory(testCase.getSection());
        assertSteps.checkMainAsserts(testCase);
    }

    public void navigateToAdvertisementPage()  {
        assertSteps.navigationToAdvertisementPageAsserts();
        loginPage.closeDialogWindow();

    }

    public void loginAndNavigateToAdvertisementPage( ) {
        reporter.info("დალოგინება");
        loginPage.login(userConfig.loginMail(), userConfig.loginPassword());
        reporter.info("ნავიგაცია განცხადების დამატების გვერდზე");
        advertisementPage.clickAdvertisementBtn();
        navigateToAdvertisementPage();
    }
}
