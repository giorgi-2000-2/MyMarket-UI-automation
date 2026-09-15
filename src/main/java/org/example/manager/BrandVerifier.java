package org.example.manager;
import com.google.inject.Inject;
import org.example.asserts.ISoftVerifier;
import org.example.asserts.VerificationResult;
import org.example.jsonmanager.CategoryDataService;
import org.example.utils.reporter.IReportNode;
import org.example.utils.reporter.NodeKey;
import org.example.utils.reporter.ReportStatus;
import org.json.JSONException;

import java.util.List;

import static org.example.utils.reporter.NodeKey.JSON_DATA;

public class BrandVerifier implements IBrandVerifier{

    private final IReportNode reporter;
    private final CategoryDataService categoryDataService;
    private final ISoftVerifier assertManager;
    @Inject
    public BrandVerifier(IReportNode reporter,
                         CategoryDataService categoryDataService,
                         ISoftVerifier assertManager) {
        this.reporter = reporter;
        this.categoryDataService = categoryDataService;
        this.assertManager = assertManager;
    }


    public void checkBrandsInDataTree(String titleText, NodeKey parentKey, List<String> brands) {

        reporter.createChildNode(parentKey, NodeKey.BRAND_ITEM, titleText);

        try {
            if (brands == null || brands.isEmpty()) {
                reporter.logToNode(NodeKey.BRAND_ITEM, ReportStatus.INFO,
                        " — ბრენდის dropdown არ არის, გამოტოვება");
                return;
            }

            reporter.createChildNode(NodeKey.BRAND_ITEM, NodeKey.BRANDS, "ბრენდები");

            for (String brand : brands) {
                assertBrandExists(NodeKey.BRANDS, titleText, brand);
            }

        } catch (JSONException e) {
            reporter.logToNode(NodeKey.BRAND_ITEM, ReportStatus.INFO,
                    titleText + " — ბრენდის შემოწმებისას შეცდომა: " + e.getMessage());
        }
    }


    public void assertCategoryExists(String name) {
        boolean found = categoryDataService.exists(name);
        assertManager.condition(JSON_DATA, name + " მოიძებნა", found);
    }

    public void assertBrandExists(NodeKey nodeKey, String categoryName, String brand) {
        boolean found = categoryDataService.brandExists(categoryName, brand);
        assertManager.condition(nodeKey, categoryName + " შეიცავს " + brand, found);
    }
}