package org.example.asserts;

import com.google.inject.Inject;
import org.example.pages.advertisement.IAdvertisementPage;
import org.example.utils.config.IUserConfig;
import org.example.utils.reporter.NodeKey;

public class UserInfoAsserts {
    private final IAdvertisementPage advertisementPage;
    private final IUserConfig userConfig;
    private final ISoftVerifier assertManager;
    @Inject
    public UserInfoAsserts(IAdvertisementPage advertisementPage,
                           IUserConfig userConfig,
                           ISoftVerifier assertManager) {
        this.advertisementPage = advertisementPage;
        this.userConfig = userConfig;
        this.assertManager = assertManager;
    }

    public void assertUserNameAndId(NodeKey nodeKey) {
        assertManager.check(nodeKey, "მომხმარებლის სახელი")
                .expected(userConfig.expectedUserName())
                .actual(advertisementPage.getUserInfo().getPageUserName());

        assertManager.check(nodeKey, "მომხმარებლის ID")
                .expected(userConfig.userId())
                .actual(advertisementPage.getUserInfo().getUserNameID().getText());
    }

    public void assertDropdownUserNameMatchesPage(NodeKey nodeKey) {
        assertManager.check(nodeKey, "Dropdown-ის სახელი ემთხვევა გვერდის სახელს")
                .expected(advertisementPage.getUserInfo().getPageUserName())
                .actual(advertisementPage.getUserInfo().getUserNameFromDropdown());
    }
}