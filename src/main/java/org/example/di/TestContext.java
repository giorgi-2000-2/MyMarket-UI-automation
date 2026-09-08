package org.example.di;
import org.example.steps.*;
import org.example.pages.basepage.BasePage;
import org.example.asserts.AssertManager;
import org.example.asserts.AssertSteps;
import org.example.asserts.IAssertManager;
import org.example.manager.BrandVerifier;
import org.example.manager.CategoryWalker;
import org.example.manager.DataProvider;
import org.example.pages.PageNavigator;
import org.example.pages.advertisement.AdvertisementPage;
import org.example.pages.login.LoginPage;
import org.example.utils.Waits;
import org.example.utils.reporter.IReportNode;
import org.example.utils.reporter.IReportTree;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public final class TestContext {

    private final LoginPage login;
    private final BusinessSteps steps;
    private final CategorySteps categorySteps;
    private final BrandVerificationSteps brandVerificationSteps;
    private final AssertSteps assertSteps;
    private final SoftAssert softAssert;

    public <R extends IReportTree & IReportNode> TestContext(WebDriver driver, R reporter ) {


        Waits waits = new Waits(driver, SuiteContext.waitConfig());
        BasePage ui = new BasePage(driver, waits, SuiteContext.splitter());

        AdvertisementPage adPage = new AdvertisementPage(driver, ui, reporter, waits);
        this.login = new LoginPage(driver, reporter, waits, ui);

        IAssertManager asserter = new AssertManager(reporter);
        DataProvider data = new DataProvider(asserter, SuiteContext.splitter(), SuiteContext.finder());
        BrandVerifier brands = new BrandVerifier(reporter, data);
        this.categorySteps = new CategorySteps(adPage,ui,asserter,reporter);
        this.brandVerificationSteps = new BrandVerificationSteps(adPage,ui,data,brands,reporter);
        CategoryWalker walker = new CategoryWalker(adPage, ui);
        CategoryNavigationSteps navigationSteps = new CategoryNavigationSteps(adPage,ui,asserter,reporter);
        DataStepsManager dataSteps = new DataStepsManager(categorySteps,brandVerificationSteps,navigationSteps);
        PageNavigator    navigator = new PageNavigator(driver, login, adPage, reporter, SuiteContext.user(), SuiteContext.url());

        this.steps = new BusinessSteps(walker, dataSteps, navigator);
        this.assertSteps = new AssertSteps(SuiteContext.user(), adPage, ui, asserter, reporter);
        this.softAssert  = new SoftAssert();
    }

    public LoginPage      login()       { return login; }
    public BusinessSteps steps()       { return steps; }
    public AssertSteps    assertSteps() { return assertSteps; }
    public SoftAssert     softAssert()  { return softAssert; }
}