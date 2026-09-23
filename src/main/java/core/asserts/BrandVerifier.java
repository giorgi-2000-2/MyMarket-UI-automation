package core.asserts;
import com.google.inject.Inject;
import core.jsonmanager.CategoryDataService;
import core.reporter.IReportNode;
import core.reporter.NodeKey;
import core.reporter.ReportStatus;
import core.reporter.texts.AssertMessages;
import core.reporter.texts.ErrorMessages;
import core.reporter.texts.StepNames;
import org.json.JSONException;
import core.annotations.TestScoped;

import java.util.List;

import static core.reporter.NodeKey.JSON_DATA;
@TestScoped
public class BrandVerifier {

    private final IReportNode reporter;
    private final CategoryDataService categoryDataService;
    private final SoftVerifier assertManager;
    @Inject
    public BrandVerifier(IReportNode reporter,
                         CategoryDataService categoryDataService,
                         SoftVerifier assertManager) {
        this.reporter = reporter;
        this.categoryDataService = categoryDataService;
        this.assertManager = assertManager;
    }


    public void checkBrandsInDataTree(String titleText, NodeKey parentKey, List<String> brands) {

        reporter.createChildNode(parentKey, NodeKey.BRAND_ITEM, titleText);

        try {
            if (brands == null || brands.isEmpty()) {
                reporter.logToNode(NodeKey.BRAND_ITEM, ReportStatus.INFO,
                        AssertMessages.BRAND_DROPDOWN_MISSING.get());
                return;
            }

            reporter.createChildNode(NodeKey.BRAND_ITEM, NodeKey.BRANDS,  StepNames.BRANDS_NODE.get());

            for (String brand : brands) {
                assertBrandExists(NodeKey.BRANDS, titleText, brand);
            }

        } catch (JSONException e) {
            reporter.logToNode(NodeKey.BRAND_ITEM, ReportStatus.INFO,
                    titleText +  ErrorMessages.BRAND_CHECK_ERROR.format(titleText, e.getMessage()));
        }
    }


    public void assertCategoryExists(String name) {
        boolean found = categoryDataService.exists(name);
        assertManager.condition(JSON_DATA, AssertMessages.CATEGORY_FOUND.format(name), found);
    }

    public void assertBrandExists(NodeKey nodeKey, String categoryName, String brand) {
        boolean found = categoryDataService.brandExists(categoryName, brand);
        assertManager.condition(nodeKey,  AssertMessages.CATEGORY_CONTAINS_BRAND.format(categoryName,brand), found);
    }
}