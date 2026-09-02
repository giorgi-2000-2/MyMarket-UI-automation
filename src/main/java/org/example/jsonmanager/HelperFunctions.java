package org.example.jsonmanager;
import org.example.DataProvider.DataDto;
import org.example.manager.PageObjectManager;
import org.example.utils.reporter.ITestReporter;
import org.example.utils.reporter.NodeKey;
import org.example.utils.reporter.ReportStatus;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class HelperFunctions {
private final PageObjectManager pom;
    private final ITestReporter reporter;
    public HelperFunctions(PageObjectManager pom, ITestReporter reporter) {
        this.pom=pom;
        this.reporter = reporter;
    }



    public void recursiveThree(SoftAssert softAssert, DataDto testCase){

        for (int j = 1; j <pom.getAdPage().createList().size(); j++) {
            WebElement sub = pom.getAdPage().createList().get(j);
            pom.getAdPage().scroll(sub);
            pom.getAdPage().waitClick(sub);
            pom.getDataManager().EmptyWithDataCheck(softAssert,testCase );
        }
    }

    public void recursiveThree2(SoftAssert softAssert, NodeKey nodeName){
        for (int j = 1; j <pom.getAdPage().createList().size(); j++) {
            WebElement sub = pom.getAdPage().createList().get(j);
            pom.getAdPage().scroll(sub);
            pom.getAdPage().waitClick(sub);

            pom.getDataManager().ifEmpty(softAssert, nodeName);
        }
    }

    public void checkBrandsInDataThree(SoftAssert softAssert, String titleText, NodeKey parentKey, boolean checkBrands){
        if (checkBrands) {
            reporter.createChildNode(parentKey,NodeKey.BRAND_ITEM,titleText);
            try {
                List<String> brands = pom.getBrandManager().brandDropdownFind(NodeKey.BRAND_ITEM);
                if (brands.isEmpty()) {
                    reporter.logToNode(NodeKey.BRAND_ITEM, ReportStatus.INFO," — ბრენდის dropdown არ არის, გამოტოვება");
                } else {
                    reporter.createChildNode(NodeKey.BRAND_ITEM,NodeKey.BRANDS,"ბრენდები");
                    for (String brand : brands) {
                       System.out.println("ბრენდი :  "+brand);
                        pom.getDataProvider().itemToDataBrands(NodeKey.BRANDS, titleText, brand, softAssert);
                    }
                }
            } catch (Exception e) {
                reporter.logToNode(NodeKey.BRAND_ITEM,ReportStatus.INFO,titleText + " — ბრენდი არ აქვს: ");
            }
        }

    }




}
