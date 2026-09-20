package mobile.steps;
import com.google.inject.Inject;
import core.jsonmanager.CategoryDataService;
import mobile.brand.BrandFinder;
import mobile.brand.BrandManager;
import mobile.crawler.handler.LeafCategoryHandler;
import core.annotations.TestScoped;
import core.testdata.CategoryTestCase;
import core.asserts.BrandVerifier;
import core.reporter.IReportNode;
import core.reporter.ReportMessages;


import java.util.List;

import static core.reporter.NodeKey.CATEGORY;
import static core.reporter.NodeKey.JSON_DATA;
@TestScoped
public class MobileBrandVerificationSteps implements LeafCategoryHandler {
    private final BrandVerifier brandVerifier;
    private final IReportNode reporter;
    private final BrandManager brandManager;
    private final CategoryDataService categoryDataService;
private final BrandFinder brandFinder;
    @Inject
    public MobileBrandVerificationSteps(BrandVerifier brandVerifier,
                                        IReportNode reporter, BrandManager brandManager,
                                        CategoryDataService categoryDataService, BrandFinder brandFinder) {
        this.brandVerifier = brandVerifier;
        this.reporter = reporter;
        this.brandManager = brandManager;
        this.categoryDataService = categoryDataService;
        this.brandFinder = brandFinder;
    }


    public void verifyCategoryWithData(String child,CategoryTestCase testCase) {
        boolean checkBrands = testCase.isCheckBrands();
        String stepName = checkBrands
                ? ReportMessages.CHECK_CATEGORIES_AND_BRANDS.get()
                : ReportMessages.CHECK_CATEGORIES.get();

        reporter.createNamedNode(CATEGORY, stepName);
        reporter.createNamedNode(JSON_DATA, ReportMessages.COMPARE_CATEGORIES_WITH_DATA.get());

        brandVerifier.assertCategoryExists(child);

        if (categoryDataService.exists(child) && checkBrands) {
            boolean found = brandFinder.findBrandDropdown();
            List<String> brands = brandManager.clickBrand(found);
            brandVerifier.checkBrandsInDataTree(child, JSON_DATA, brands);
        }
    }

    @Override
    public void handle(List<String> fullPath) {

    }
}
