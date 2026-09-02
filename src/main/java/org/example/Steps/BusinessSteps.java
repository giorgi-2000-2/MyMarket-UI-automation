package org.example.Steps;
import org.example.DataProvider.CategoryTestCase;
import org.example.manager.AssertManager;
import org.example.manager.NewDataManager;
import org.example.pages.AdvertisementPage;
import org.example.pages.LoginPage;
import org.example.utils.config.ConfigReader;
import org.example.utils.reporter.ITestReporter;
import org.example.utils.reporter.ReportStatus;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;
import java.util.List;

import static org.example.utils.reporter.NodeKey.*;

public class BusinessSteps {
   private final AdvertisementPage advertisementPage;
   private final AssertManager assertManager;
   private final LoginPage loginPage;
    private final ITestReporter reporter;
    private final NewDataManager dataManager;



    public BusinessSteps(AdvertisementPage advertisementPage, AssertManager assertManager, LoginPage loginPage, ITestReporter reporter, NewDataManager dataManager){
        this.advertisementPage = advertisementPage;
        this.assertManager = assertManager;
        this.loginPage = loginPage;
        this.reporter = reporter;
        this.dataManager = dataManager;
    }




    public void loginAndNavigate(SoftAssert softAssert) {
        reporter.info("დალოგინება");
        loginPage.login(ConfigReader.get("login.mail"), ConfigReader.get("login.password"));
        reporter.info("ნავიგაცია განცხადების დამატების გვერდზე");
        advertisementPage.navigateToAdvertisementPage(softAssert);
    }





    public void checkMainAsserts(SoftAssert softAssert, CategoryTestCase testCase) {
        advertisementPage.clickCheckBtn(testCase.getSection());
        reporter.createNamedNode(CLICK_BTN_CHECK,testCase.getSection()+ " ღილაკზე დაჭერის შემდეგომი შემოწმება");
        String actualUrl =  advertisementPage.getCurrentURL().replace("www.", "");;
        String expectedUrl = testCase.getExpectedUrl().replace("www.", "");
        assertManager.assertTrueWithLog(softAssert,CLICK_BTN_CHECK, actualUrl, expectedUrl, " მისამართის შემოწმება ");
        assertManager.assertWithLog(softAssert,CLICK_BTN_CHECK,advertisementPage.getUserNamecheck(),advertisementPage.usernameCheck(),"username წარმატებით შემოწმება");

        assertManager.assertWithLog(softAssert,CLICK_BTN_CHECK,advertisementPage.getMainTitle().getText(),"განცხადების დამატება","- მთავარი სათაური ");

        assertManager.assertWithLog(softAssert,CLICK_BTN_CHECK,advertisementPage.usernameCheck(),"გიორგი მიქელაძე", "username - გიორგი მიქელაძე ");

        assertManager.assertWithLog(softAssert,CLICK_BTN_CHECK,advertisementPage.getUsernameID().getText(), ConfigReader.get("user.id"),"ID - "+ ConfigReader.get("user.id"));


    }




    public void checkAllCategoryItems(SoftAssert softAssert, CategoryTestCase testCase) {
        advertisementPage.clickDropdownCategory();
      reporter.createNamedNode(CATEGORY, testCase.getSection()+"-ის ნახვა");
        for (int i = 0; i <advertisementPage.createList().size(); i++) {
            advertisementPage.scroll(advertisementPage.createList().get(i));
            advertisementPage.waitClick(advertisementPage.createList().get(i));
            dataManager.walkSubcategoriesTitles(softAssert,CATEGORY);

            if (advertisementPage.createList().get(0).getText().contains("უკან დაბრუნება")) {
                advertisementPage.backClick();
            }
        }
        advertisementPage.backClick();
    }


    public void checkAllCategoryItemsData(SoftAssert softAssert, CategoryTestCase testCase) {
        advertisementPage.clickDropdownCategory();
        String stepName = testCase.isCheckBrands() ? "კატეგორიების და ბრენდების შემოწმება" : "კატეგორიების ნახვა";

        reporter.createNamedNode(CATEGORY, stepName);
        reporter.createNamedNode(JSON_DATA, "კატეგორიების შედარება მონაცემებთან");
        for (int i = 0; i <advertisementPage.createList().size(); i++) {
            WebElement option =advertisementPage.createList().get(i);
            advertisementPage.scroll(option);
            advertisementPage.waitClick(option);
            dataManager.walkSubcategoriesWithDataCheck(softAssert,testCase);

            if (!advertisementPage.createList().isEmpty() && advertisementPage.createList().get(0).getText().contains("უკან დაბრუნება")) {
                advertisementPage.backClick();
            }
        }
        advertisementPage.backClick();
    }



    public void checkBackClickInCategories(SoftAssert softAssert) {
        advertisementPage.clickDropdownCategory();
       reporter.createNamedNode(DROPDOWN,"დროპდაუნ მენიუს კატეგორიები ხილვადია");
        List<String> OldListCheck = new ArrayList<>();
        for (int i = 0; i < advertisementPage.createList().size(); i++) {
            WebElement option = advertisementPage.createList().get(i);
            OldListCheck.add(option.getText());
            advertisementPage.scroll(option);
            advertisementPage.click(option);
            for (int j = 1; j < advertisementPage.createList().size(); j++) {
                reporter.logToNode(DROPDOWN, ReportStatus.INFO,"შესადარებელი კატეგორიები " + OldListCheck.get(i) + " არ უდრის  "+ advertisementPage.createList().get(j).getText()+" –ს ");
                assertManager.assertTrueWithLog(softAssert, DROPDOWN,!OldListCheck.get(i).equals(advertisementPage.createList().get(j).getText()), " ნავიგაცია ");
            }

            advertisementPage.backClick();
        }
    }




}
