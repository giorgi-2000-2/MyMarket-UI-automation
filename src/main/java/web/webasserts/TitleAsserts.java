package web.webasserts;
import com.google.inject.Inject;
import core.annotations.TestScoped;
import core.asserts.SoftVerifier;
import web.pages.advertisement.AdvertisementPage;
import core.config.IPageConfig;
import core.reporter.NodeKey;
import core.reporter.ReportMessages;
@TestScoped
public class TitleAsserts {
    private final AdvertisementPage advertisementPage;
    private final SoftVerifier assertManager;
    private final IPageConfig config;
    @Inject
    public TitleAsserts(AdvertisementPage advertisementPage, SoftVerifier assertManager, IPageConfig config) {
        this.advertisementPage = advertisementPage;
        this.assertManager = assertManager;
        this.config = config;
    }

    public void assertMainTitle(NodeKey nodeKey) {
        assertManager.check(nodeKey, ReportMessages.CHECK_MAIN_TITLE.get())
                .expected(config.pageMainTitle())
                .actual(advertisementPage.getMainTitle());
    }
}