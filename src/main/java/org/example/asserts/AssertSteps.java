package org.example.asserts;
import org.example.pages.basepage.BasePage;
import org.example.dataprovider.CategoryTestCase;
import org.example.pages.advertisement.AdvertisementPage;
import org.example.utils.config.IUserConfig;
import org.example.utils.reporter.IReportNode;
import org.testng.asserts.SoftAssert;

import static org.example.utils.reporter.NodeKey.CLICK_BTN_CHECK;

public class AssertSteps {
private final IUserConfig userConfig;
private final AdvertisementPage advertisementPage;
private final BasePage basePage;
private final IReportNode reportNode;
private final IAssertManager assertManager;
    public AssertSteps(IUserConfig userConfig, AdvertisementPage advertisementPage, BasePage basePage, IAssertManager assertManager, IReportNode reportNode) {
        this.userConfig = userConfig;

        this.advertisementPage = advertisementPage;
        this.basePage = basePage;
        this.assertManager = assertManager;
        this.reportNode = reportNode;
    }


    public void checkMainAsserts(SoftAssert softAssert, CategoryTestCase testCase) {
        advertisementPage.getCategoryDropdown().clickCategory(testCase.getSection());
        reportNode.createNamedNode(CLICK_BTN_CHECK,testCase.getSection()+ " ღილაკზე დაჭერის შემდეგომი შემოწმება");
        String actualUrl =  basePage.getCurrentURL().replace("www.", "");;
        String expectedUrl = testCase.getExpectedUrl().replace("www.", "");

        assertManager.assertWithLog(softAssert,CLICK_BTN_CHECK, actualUrl, expectedUrl, " მისამართის შემოწმება ");

        assertManager.assertWithLog(softAssert,CLICK_BTN_CHECK,advertisementPage.getUserInfo().getUserNameFromDropdown(),advertisementPage.getUserInfo().getPageUserName(),"username წარმატებით შემოწმება");

        assertManager.assertWithLog(softAssert,CLICK_BTN_CHECK,advertisementPage.getMainTitle().getText(),"განცხადების დამატება","მთავარი სათაური – "+"განცხადების დამატება");

        assertManager.assertWithLog(softAssert,CLICK_BTN_CHECK,advertisementPage.getUserInfo().getPageUserName(),"გიორგი მიქელაძე", "username - "+"გიორგი მიქელაძე");

        assertManager.assertWithLog(softAssert,CLICK_BTN_CHECK,advertisementPage.getUserInfo().getUserNameID().getText(), userConfig.userId(),"ID - "+ userConfig.userId());


    }


}
