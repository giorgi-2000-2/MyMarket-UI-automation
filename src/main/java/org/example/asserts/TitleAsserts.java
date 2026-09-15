package org.example.asserts;
import com.google.inject.Inject;
import org.example.pages.advertisement.IAdvertisementPage;
import org.example.utils.config.IPageConfig;
import org.example.utils.reporter.NodeKey;

public class TitleAsserts {
    private final IAdvertisementPage advertisementPage;
    private final ISoftVerifier assertManager;
    private final IPageConfig config;
    @Inject
    public TitleAsserts(IAdvertisementPage advertisementPage, ISoftVerifier assertManager, IPageConfig config) {
        this.advertisementPage = advertisementPage;
        this.assertManager = assertManager;
        this.config = config;
    }

    public void assertMainTitle(NodeKey nodeKey) {
        assertManager.check(nodeKey,"\"მთავარი სათაური\"")
                .expected(config.pageMainTitle())
                .actual(advertisementPage.getMainTitle().getText());
    }
}