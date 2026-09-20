package mobile.mobileasserts;

import com.google.inject.Inject;
import core.asserts.SoftVerifier;
import core.config.IUserConfig;
import core.reporter.NodeKey;
import core.reporter.ReportMessages;
import mobile.pages.MainPage;
import core.annotations.TestScoped;


@TestScoped
public class MobileUserInfoAsserts {
    private final MainPage mainPage;
    private final IUserConfig userConfig;
    private final SoftVerifier assertManager;

    @Inject
    public MobileUserInfoAsserts(MainPage mainPage, IUserConfig userConfig, SoftVerifier assertManager) {
        this.mainPage = mainPage;
        this.userConfig = userConfig;
        this.assertManager = assertManager;
    }

    public void assertUserNameAndId(NodeKey nodeKey) {
        assertManager.check(nodeKey, ReportMessages.CHECK_USER_NAME.get())
                .expected(userConfig.expectedUserName())
                .actual(mainPage.getProfileUserNameText());

        assertManager.check(nodeKey, ReportMessages.CHECK_USER_ID.get())
                .expected(userConfig.userId())
                .actual(mainPage.getUserIdText());
    }
}
