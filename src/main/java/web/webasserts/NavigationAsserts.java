package web.webasserts;
import com.google.inject.Inject;
import core.annotations.TestScoped;
import core.asserts.SoftVerifier;
import core.reporter.texts.StepNames;
import web.pages.advertisement.AdvertisementPage;
import web.pages.basepage.BasePage;
import core.config.IPageConfig;
import core.config.IUrlConfig;
import core.config.IUserConfig;
import core.reporter.IReportNode;

import static core.reporter.NodeKey.NAVIGATION_AD_PAGE;
@TestScoped
public class NavigationAsserts {

    private final AdvertisementPage advertisementPage;
    private final BasePage basePage;
    private final IUrlConfig urlConfig;
    private final IUserConfig userConfig;
    private final SoftVerifier assertManager;
    private final IReportNode reportNode;
    private final IPageConfig pageConfig;
    @Inject
    public NavigationAsserts(AdvertisementPage advertisementPage,
                             BasePage basePage,
                             IUrlConfig urlConfig,
                             IUserConfig userConfig,
                             SoftVerifier assertManager,
                             IReportNode reportNode,
                             IPageConfig pageConfig) {
        this.advertisementPage = advertisementPage;
        this.basePage = basePage;
        this.urlConfig = urlConfig;
        this.userConfig = userConfig;
        this.assertManager = assertManager;
        this.reportNode = reportNode;
        this.pageConfig = pageConfig;
    }

    public void assertAfterNavigatingToAdvertisementPage() {
        reportNode.createNamedNode(NAVIGATION_AD_PAGE, StepNames.AFTER_NAV_TO_AD.get());

        assertManager.check(NAVIGATION_AD_PAGE, StepNames.CHECK_URL.get())
                .expected(urlConfig.baseUrl())
                .actual(basePage.getCurrentURL());

        assertManager.check(NAVIGATION_AD_PAGE, StepNames.CHECK_MAIN_TITLE.get())
                .expected(pageConfig.pageMainTitle())
                .actual(advertisementPage.getMainTitle());

        assertManager.check(NAVIGATION_AD_PAGE, StepNames.CHECK_USER_NAME.get())
                .expected(userConfig.expectedUserName())
                .actual(advertisementPage.getUserInfo().getPageUserName());

        assertManager.check(NAVIGATION_AD_PAGE, StepNames.CHECK_USER_ID.get())
                .expected(userConfig.userId())
                .actual(advertisementPage.getUserInfo().getUserNameID().getText());
    }

}