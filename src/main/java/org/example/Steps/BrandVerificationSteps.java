package org.example.steps;

import com.google.inject.Inject;
import org.example.dataprovider.CategoryTestCase;
import org.example.manager.BrandVerifier;
import org.example.jsonmanager.CategoryDataService;
import org.example.manager.IBrandVerifier;
import org.example.pages.advertisement.IAdvertisementPage;
import org.example.utils.reporter.IReportNode;

import java.util.List;

import static org.example.utils.reporter.NodeKey.CATEGORY;
import static org.example.utils.reporter.NodeKey.JSON_DATA;

public class BrandVerificationSteps {

    private final IAdvertisementPage adPage;
    private final CategoryDataService categoryDataService;
    private final IBrandVerifier brandVerifier;
    private final IReportNode reporter;
    @Inject
    public BrandVerificationSteps(IAdvertisementPage adPage,
                                  CategoryDataService categoryDataService,
                                  IBrandVerifier brandVerifier,
                                  IReportNode reporter) {
        this.adPage = adPage;
        this.categoryDataService = categoryDataService;
        this.brandVerifier = brandVerifier;
        this.reporter = reporter;
    }

    public void verifyCategoryWithData(CategoryTestCase testCase) {
        String stepName = testCase.isCheckBrands()
                ? "კატეგორიების და ბრენდების შემოწმება"
                : "კატეგორიების ნახვა";

        reporter.createNamedNode(CATEGORY, stepName);
        reporter.createNamedNode(JSON_DATA, "კატეგორიების შედარება მონაცემებთან");

        adPage.waitString(adPage.getTitleComponent().getTitleAfterChange());
        String titleText = adPage.getTitleComponent().getTitleAfterChange().getText();

        brandVerifier.assertCategoryExists( titleText);

        if (categoryDataService.exists(titleText)&&testCase.isCheckBrands()) {
            List<String> brands = adPage.getBrandDropdown().getAvailableBrands();
            brandVerifier.checkBrandsInDataTree( titleText, JSON_DATA, brands);
        }
    }






}