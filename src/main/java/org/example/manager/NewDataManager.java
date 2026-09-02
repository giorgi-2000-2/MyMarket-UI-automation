package org.example.manager;
import org.example.DataProvider.CategoryTestCase;
import org.example.DataProvider.DataProvider;
import org.example.pages.AdvertisementPage;
import org.example.utils.reporter.ITestReporter;
import org.example.utils.reporter.NodeKey;
import org.example.utils.reporter.ReportStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import static org.example.utils.reporter.NodeKey.CATEGORY;
import static org.example.utils.reporter.NodeKey.JSON_DATA;

public class NewDataManager {
    private final AdvertisementPage advertisementPage;
    private final DataProvider dataProvider;
    private final NewBrandManager brandManager;
    private final AssertManager assertManager;

    private final ITestReporter reporter;

    public NewDataManager(AdvertisementPage advertisementPage, DataProvider dataProvider, NewBrandManager brandManager, AssertManager assertManager, ITestReporter reporter) {
        this.advertisementPage = advertisementPage;
        this.dataProvider = dataProvider;
        this.brandManager = brandManager;
        this.assertManager = assertManager;
        this.reporter = reporter;
    }

    public void ifEmpty(SoftAssert softAssert, NodeKey nodeName) {
        boolean bool = !advertisementPage.getMainElements().isEmpty();
        if (!bool) {
            WebElement title = advertisementPage.shortWait.until(ExpectedConditions.visibilityOf(advertisementPage.driver.findElement(By.xpath("//*[@id=\"CatID\"]/div/div/div/div[1]/div[1]"))));
            assertManager.assertWithLog( softAssert, nodeName,advertisementPage.titleText(title),(advertisementPage.getTextTitle())," შედარება ");
            advertisementPage.scroll(advertisementPage.getDropdownCategory());
            advertisementPage.waitClick(advertisementPage.getDropdownCategory());
        } else {
            walkSubcategoriesTitles(softAssert,nodeName);
            advertisementPage.backClick();
        }



    }

    public void emptyWithDataCheck(SoftAssert softAssert, CategoryTestCase testCase) {
        boolean bool = !advertisementPage.getMainElements().isEmpty();
        if (!bool) {
            advertisementPage.waitString(advertisementPage.getTitle());
            WebElement title = advertisementPage.shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"CatID\"]/div/div/div/div[1]/div[1]")));
            String titleText = advertisementPage.getTitle().getText();
          if (!testCase.isSkipTitleCheck()) {
             assertManager.assertWithLog(softAssert,CATEGORY, advertisementPage.titleText(title), advertisementPage.getTextTitle(), " შედარება ");
          }else {
              reporter.logToNode(CATEGORY, ReportStatus.INFO, "მომსახურების კატეგორიებს არ აქვს Main Title");
          }
            dataProvider.itemToData(titleText, softAssert);
            brandManager.checkBrandsInDataThree(softAssert,titleText,JSON_DATA, testCase.isCheckBrands());
            advertisementPage.scroll(advertisementPage.getDropdownCategory());
            advertisementPage.waitClick(advertisementPage.getDropdownCategory());
        } else {
            walkSubcategoriesWithDataCheck(softAssert, testCase);
            advertisementPage.backClick();
        }
    }
    public void walkSubcategoriesWithDataCheck(SoftAssert softAssert, CategoryTestCase testCase){

        for (int j = 1; j <advertisementPage.createList().size(); j++) {
            WebElement sub = advertisementPage.createList().get(j);
            advertisementPage.scroll(sub);
            advertisementPage.waitClick(sub);
            emptyWithDataCheck(softAssert,testCase );
        }
    }

    public void walkSubcategoriesTitles(SoftAssert softAssert, NodeKey nodeName){
        for (int j = 1; j <advertisementPage.createList().size(); j++) {
            WebElement sub = advertisementPage.createList().get(j);
            advertisementPage.scroll(sub);
            advertisementPage.waitClick(sub);

            ifEmpty(softAssert, nodeName);
        }
    }


}