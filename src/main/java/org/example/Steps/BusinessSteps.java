package org.example.Steps;
import org.example.DataProvider.DataDto;
import org.example.DataProvider.IDto;
import org.example.manager.PageObjectManager;
import org.example.utils.ConfigReader;
import org.example.utils.reporter.ITestReporter;
import org.example.utils.reporter.ReportStatus;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static org.example.utils.reporter.NodeKey.*;

public class BusinessSteps {
   private final PageObjectManager pom;
    private final ITestReporter reporter;
    public BusinessSteps(PageObjectManager pom, ITestReporter reporter){
        this.pom=pom;
        this.reporter = reporter;
    }
    public void loginAndNavigate(SoftAssert softAssert) {
        reporter.info("დალოგინება");
        pom.getLoginPage().Login(ConfigReader.get("login.mail"), ConfigReader.get("login.password"));
        reporter.info("ნავიგაცია განცხადების დამატების გვერდზე");
        pom.getAdPage().navigateToAdvertisementPage(softAssert);
    }

    public void checkMainAsserts(SoftAssert softAssert, IDto testCase) {
        pom.getAdPage().clickCheckBtn(testCase.getName());
        reporter.createNamedNode(CLICK_BTN_CHECK,testCase.getName()+ " ღილაკზე დაჭერის შემდეგომი შემოწმება");
        String actualUrl =  pom.getBasePage().getCurrentURL().replace("www.", "");;
        String expectedUrl = testCase.getUrl().replace("www.", "");
        pom.getAssert().assertTrueWithLog(softAssert,CLICK_BTN_CHECK, actualUrl, expectedUrl, " მისამართის შემოწმება ");
        pom.getAssert().assertWithLog(softAssert,CLICK_BTN_CHECK,pom.getAdPage().getUserNamecheck(),pom.getAdPage().usernameCheck(),"username წარმატებით შემოწმება");

        pom.getAssert().assertWithLog(softAssert,CLICK_BTN_CHECK,pom.getAdPage().getMainTitle().getText(),"განცხადების დამატება","- მთავარი სათაური ");

        pom.getAssert().assertWithLog(softAssert,CLICK_BTN_CHECK,pom.getAdPage().usernameCheck(),"გიორგი მიქელაძე", "username - გიორგი მიქელაძე ");

        pom.getAssert().assertWithLog(softAssert,CLICK_BTN_CHECK,pom.getAdPage().getUsernameID().getText(), ConfigReader.get("user.id"),"ID - "+ ConfigReader.get("user.id"));


    }




    public void checkAllCategoryItems(SoftAssert softAssert, String name) {
        pom.getAdPage().clickDropdownCategory();
      reporter.createNamedNode(CATEGORY, name+"-ის ნახვა");
        for (int i = 0; i <pom.getAdPage().createList().size(); i++) {
            pom.getAdPage().scroll(pom.getAdPage().createList().get(i));
            pom.getAdPage().waitClick(pom.getAdPage().createList().get(i));
            pom.getHFunctions().recursiveThree2(softAssert,CATEGORY);

            if (pom.getAdPage().createList().get(0).getText().contains("უკან დაბრუნება")) {
                pom.getAdPage().backClick();
            }
        }
        pom.getAdPage().backClick();
    }


    public void checkAllCategoryItemsData(SoftAssert softAssert, DataDto testCase) {
        pom.getAdPage().clickDropdownCategory();
        String stepName = testCase.isCheck() ? "კატეგორიების და ბრენდების შემოწმება" : "კატეგორიების ნახვა";

        reporter.createNamedNode(CATEGORY, stepName);
        reporter.createNamedNode(JSON_DATA, "კატეგორიების შედარება მონაცემებთან");
        for (int i = 0; i <pom.getAdPage().createList().size(); i++) {
            WebElement option = pom.getAdPage().createList().get(i);
            pom.getAdPage().scroll(option);
            pom.getAdPage().waitClick(option);
         pom.getHFunctions().recursiveThree(softAssert,testCase);

            if (!pom.getAdPage().createList().isEmpty() && pom.getAdPage().createList().get(0).getText().contains("უკან დაბრუნება")) {
                pom.getAdPage().backClick();
            }
        }
        pom.getAdPage().backClick();
    }



    public void checkBackClickInCategories(SoftAssert softAssert) {
        pom.getAdPage().clickDropdownCategory();
       reporter.createNamedNode(DROPDOWN,"დროპდაუნ მენიუს კატეგორიები ხილვადია");
        List<String> OldListCheck = new ArrayList<>();
        for (int i = 0; i < pom.getAdPage().createList().size(); i++) {
            WebElement option = pom.getAdPage().createList().get(i);
            OldListCheck.add(option.getText());
            pom.getAdPage().scroll(option);
            pom.getAdPage().click(option);
            for (int j = 1; j < pom.getAdPage().createList().size(); j++) {
                reporter.logToNode(DROPDOWN, ReportStatus.INFO,"შესადარებელი კატეგორიები " + OldListCheck.get(i) + " არ უდრის  "+ pom.getAdPage().createList().get(j).getText()+" –ს ");
                pom.getAssert().assertTrueWithLog(softAssert, DROPDOWN,!OldListCheck.get(i).equals(pom.getAdPage().createList().get(j).getText()), " ნავიგაცია ");
            }

            pom.getAdPage().backClick();
        }
    }




}
