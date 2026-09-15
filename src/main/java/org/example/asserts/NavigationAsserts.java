package org.example.asserts;

import com.google.inject.Inject;
import org.example.pages.advertisement.IAdvertisementPage;
import org.example.pages.basepage.IBasePage;
import org.example.utils.config.IPageConfig;
import org.example.utils.config.IUrlConfig;
import org.example.utils.config.IUserConfig;
import org.example.utils.reporter.IReportNode;

import static org.example.utils.reporter.NodeKey.NAVIGATION_AD_PAGE;

public class NavigationAsserts {

    private final IAdvertisementPage advertisementPage;
    private final IBasePage basePage;
    private final IUrlConfig urlConfig;
    private final IUserConfig userConfig;
    private final ISoftVerifier assertManager;
    private final IReportNode reportNode;
    private final IPageConfig pageConfig;
    @Inject
    public NavigationAsserts(IAdvertisementPage advertisementPage,
                             IBasePage basePage,
                             IUrlConfig urlConfig,
                             IUserConfig userConfig,
                             ISoftVerifier assertManager,
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
        reportNode.createNamedNode(NAVIGATION_AD_PAGE, "განცხადების გვერდზე გადასვლის შემდეგ შემოწმება");

        assertManager.check(NAVIGATION_AD_PAGE, "URL შემოწმება")
                .expected(urlConfig.baseUrl())
                .actual(basePage.getCurrentURL());

        assertManager.check(NAVIGATION_AD_PAGE, "მთავარი სათაური")
                .expected(pageConfig.pageMainTitle())
                .actual(advertisementPage.getMainTitle().getText());

        assertManager.check(NAVIGATION_AD_PAGE, "მომხმარებლის სახელი")
                .expected(userConfig.expectedUserName())
                .actual(advertisementPage.getUserInfo().getPageUserName());

        assertManager.check(NAVIGATION_AD_PAGE, "მომხმარებლის ID")
                .expected(userConfig.userId())
                .actual(advertisementPage.getUserInfo().getUserNameID().getText());
    }
}