package web.steps;
import com.google.inject.Inject;
import core.annotations.TestScoped;
import core.reporter.texts.StepNames;
import core.testdata.CategoryTestCase;
import core.jsonmanager.CategoryDataService;
import core.asserts.BrandVerifier;
import web.pages.advertisement.AdvertisementPage;
import core.reporter.IReportNode;

import java.util.List;

import static core.reporter.NodeKey.CATEGORY;
import static core.reporter.NodeKey.JSON_DATA;
@TestScoped
public class BrandVerificationSteps {

    private final AdvertisementPage adPage;
    private final CategoryDataService categoryDataService;
    private final BrandVerifier brandVerifier;
    private final IReportNode reporter;
    @Inject
    public BrandVerificationSteps(AdvertisementPage adPage,
                                  CategoryDataService categoryDataService,
                                  BrandVerifier brandVerifier,
                                  IReportNode reporter) {
        this.adPage = adPage;
        this.categoryDataService = categoryDataService;
        this.brandVerifier = brandVerifier;
        this.reporter = reporter;
    }

    public void verifyCategoryWithData(CategoryTestCase testCase) {
        String stepName = testCase.isCheckBrands()
                ? StepNames.CHECK_CATEGORIES_AND_BRANDS.get()
                : StepNames.CHECK_CATEGORIES.get();

        reporter.createNamedNode(CATEGORY, stepName);
        reporter.createNamedNode(JSON_DATA, StepNames.COMPARE_CATEGORIES_WITH_DATA.get());

        adPage.waitString(adPage.getTitleComponent().getTitleAfterChange());
        String titleText = adPage.getTitleComponent().getTitleAfterChange().getText();

        brandVerifier.assertCategoryExists( titleText);

        if (categoryDataService.exists(titleText)&&testCase.isCheckBrands()) {
            List<String> brands = adPage.getBrandDropdown().getAvailableBrands();
            brandVerifier.checkBrandsInDataTree( titleText, JSON_DATA, brands);
        }
    }






}