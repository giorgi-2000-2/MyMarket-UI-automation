package org.example.manager;
import org.example.utils.reporter.ITestReporter;
import org.example.utils.reporter.NodeKey;
import org.example.utils.reporter.ReportStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class NewBrandManager {

    private final PageObjectManager pom;
    private final ITestReporter reporter;

    public NewBrandManager(PageObjectManager pom, ITestReporter reporter) {
        this.pom = pom;
        this.reporter = reporter;
    }

    public List<String> brandDropdownFind(NodeKey nodeKey) {
        List<String> brandNameList = new ArrayList<>();
        boolean hasBrand;
        try {

            hasBrand = pom.getAdPage().getBrandDropdown().isDisplayed();

            if (hasBrand) {
                pom.getBasePage().scroll(pom.getAdPage().FindDropdownBrand());
                pom.getAdPage().waitClick(pom.getAdPage().FindDropdownBrand());
                List<WebElement> brandList = pom.getAdPage().createBrandList();

                try {
                    for (int i = 0; i < brandList.size(); i++) {
                        String text = brandList.get(i).getText().trim();
                        if (i == 0 && text.equals("-")) {
                            continue;
                        }
                        pom.getBasePage().scroll(brandList.get(i));
                        brandNameList.add(text);
                    }
                } catch (Exception e) {
                   reporter.logToNode(nodeKey, ReportStatus.INFO,"ნაკლები ბრენდია ");
                }

                pom.getAdPage().waitClick(pom.getAdPage().FindDropdownBrand());
            }
        } catch (Exception e) {
            reporter.logToNode(nodeKey, ReportStatus.INFO,"არ აქვს ბრენდი/ბრენდი არ გამოჩნდა ");
        }

        return brandNameList;
    }


}