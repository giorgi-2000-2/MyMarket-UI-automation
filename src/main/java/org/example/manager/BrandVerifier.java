package org.example.manager;
import org.example.utils.reporter.IReportNode;
import org.example.utils.reporter.NodeKey;
import org.example.utils.reporter.ReportStatus;
import org.testng.asserts.SoftAssert;
import java.util.List;


public class BrandVerifier {
    private final IReportNode reporter;
private final DataProvider dataProvider;
    public BrandVerifier(IReportNode reporter , DataProvider dataProvider) {
        this.reporter = reporter;

        this.dataProvider = dataProvider;
    }

    public void checkBrandsInDataTree(SoftAssert softAssert, String titleText, NodeKey parentKey, List<String>brands ) {
            reporter.createChildNode(parentKey, NodeKey.BRAND_ITEM, titleText);
            try {
                if (brands.isEmpty()) {
                    reporter.logToNode(NodeKey.BRAND_ITEM, ReportStatus.INFO, " — ბრენდის dropdown არ არის, გამოტოვება");
                } else {
                    reporter.createChildNode(NodeKey.BRAND_ITEM, NodeKey.BRANDS, "ბრენდები");
                    for (String brand : brands) {
                        dataProvider.itemToDataBrands(NodeKey.BRANDS, titleText, brand, softAssert);
                    }
                }
            } catch (Exception e) {
                reporter.logToNode(NodeKey.BRAND_ITEM, ReportStatus.INFO, titleText + " — ბრენდი არ აქვს: ");
            }


    }




}