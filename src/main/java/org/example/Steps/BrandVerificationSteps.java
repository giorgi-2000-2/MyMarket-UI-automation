package org.example.steps;

import org.example.pages.basepage.BasePage;
import org.example.dataprovider.CategoryTestCase;
import org.example.manager.BrandVerifier;
import org.example.manager.DataProvider;
import org.example.pages.advertisement.AdvertisementPage;
import org.example.utils.reporter.IReportNode;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.example.utils.reporter.NodeKey.*;

public class BrandVerificationSteps {
    private final AdvertisementPage advertisementPage;
    private final BasePage basePage;
    private final DataProvider dataProvider;
    private final BrandVerifier brandManager;
    private final IReportNode report;

    public BrandVerificationSteps(AdvertisementPage advertisementPage, BasePage basePage, DataProvider dataProvider, BrandVerifier brandManager, IReportNode report) {
        this.advertisementPage = advertisementPage;
        this.basePage = basePage;
        this.dataProvider = dataProvider;
        this.brandManager = brandManager;
        this.report = report;
    }

    public void verifyCategoryWithBrandData(SoftAssert softAssert, CategoryTestCase testCase) {
        String stepName = testCase.isCheckBrands() ? "კატეგორიების და ბრენდების შემოწმება" : "კატეგორიების ნახვა";
        report.createNamedNode(CATEGORY, stepName);
        report.createNamedNode(JSON_DATA, "კატეგორიების შედარება მონაცემებთან");

        basePage.getWaitHelper().waitString(advertisementPage.getTitleComponent().getTitleAfterChange());
        String titleText = advertisementPage.getTitleComponent().getTitleAfterChange().getText();

        boolean answer = dataProvider.itemToData(titleText, softAssert);
        if (answer) {
            List<String> brands = advertisementPage.getBrandDropdown().getAvailableBrands();
            brandManager.checkBrandsInDataTree(softAssert, titleText, JSON_DATA, brands);
        }
    }

    public void verifyCategoryOnlyData(SoftAssert softAssert, CategoryTestCase testCase) {
        String stepName = testCase.isCheckBrands() ? "კატეგორიების და ბრენდების შემოწმება" : "კატეგორიების ნახვა";
        report.createNamedNode(CATEGORY, stepName);

        basePage.getWaitHelper().waitString(advertisementPage.getTitleComponent().getTitleAfterChange());
        String titleText = advertisementPage.getTitleComponent().getTitleAfterChange().getText();

        dataProvider.itemToData(titleText, softAssert);
    }


}