package web.webasserts;

import com.google.inject.Inject;
import core.annotations.TestScoped;
import core.asserts.IUserInfoAssert;
import core.asserts.SoftVerifier;
import web.pages.advertisement.AdvertisementPage;
import core.config.IUserConfig;
import core.reporter.NodeKey;
import core.reporter.ReportMessages;
@TestScoped
public class UserInfoAsserts implements IUserInfoAssert {
    private final AdvertisementPage advertisementPage;
    private final IUserConfig userConfig;
    private final SoftVerifier assertManager;
    @Inject
    public UserInfoAsserts(AdvertisementPage advertisementPage,
                           IUserConfig userConfig,
                           SoftVerifier assertManager) {
        this.advertisementPage = advertisementPage;
        this.userConfig = userConfig;
        this.assertManager = assertManager;
    }

    public void assertUserNameAndId(NodeKey nodeKey) {
        assertManager.check(nodeKey, ReportMessages.CHECK_USER_NAME.get())
                .expected(userConfig.expectedUserName())
                .actual(advertisementPage.getUserInfo().getPageUserName());

        assertManager.check(nodeKey, ReportMessages.CHECK_USER_ID.get())
                .expected(userConfig.userId())
                .actual(advertisementPage.getUserInfo().getUserNameID().getText());
    }

    public void assertDropdownUserNameMatchesPage(NodeKey nodeKey) {
        assertManager.check(nodeKey, ReportMessages.DROPDOWN_NAME_MATCHES_PAGE.get())
                .expected(advertisementPage.getUserInfo().getPageUserName())
                .actual(advertisementPage.getUserInfo().getUserNameFromDropdown());
    }
}