package web.webasserts;
import com.google.inject.Inject;
import core.annotations.TestScoped;
import core.asserts.SoftVerifier;
import core.reporter.texts.StepNames;
import core.config.IUrlConfig;
import core.reporter.IReportNode;
import web.pages.basepage.PageAction;

import static core.reporter.NodeKey.NAVIGATION_AD_PAGE;
@TestScoped
public class NavigationAsserts {
    private final PageAction pageAction;
    private final IUrlConfig urlConfig;
    private final TitleAsserts titleAsserts;
    private final SoftVerifier assertManager;
    private final IReportNode reportNode;
  private final UserInfoAsserts userInfoAsserts;
    @Inject
    public NavigationAsserts(PageAction pageAction,
                             IUrlConfig urlConfig, TitleAsserts titleAsserts,
                             SoftVerifier assertManager,
                             IReportNode reportNode, UserInfoAsserts userInfoAsserts) {
        this.pageAction = pageAction;
        this.urlConfig = urlConfig;
        this.titleAsserts = titleAsserts;
        this.assertManager = assertManager;
        this.reportNode = reportNode;
        this.userInfoAsserts = userInfoAsserts;
    }

    public void assertAfterNavigatingToAdvertisementPage() {
        reportNode.createNamedNode(NAVIGATION_AD_PAGE, StepNames.AFTER_NAV_TO_AD.get());

        assertManager.check(NAVIGATION_AD_PAGE, StepNames.CHECK_URL.get())
                .expected(urlConfig.baseUrl())
                .actual(pageAction.getCurrentURL());

        titleAsserts.assertMainTitle(NAVIGATION_AD_PAGE);
        userInfoAsserts.assertUserNameAndId(NAVIGATION_AD_PAGE);

    }

}