package org.example.steps;
import org.example.dataprovider.CategoryTestCase;
import org.example.utils.reporter.NodeKey;
import org.testng.asserts.SoftAssert;

public class DataStepsManager {
    private final CategorySteps categorySteps;
    private final BrandVerificationSteps brandVerificationSteps;
    private final CategoryNavigationSteps navigationSteps;

    public DataStepsManager(CategorySteps categorySteps, BrandVerificationSteps brandVerificationSteps, CategoryNavigationSteps navigationSteps) {

        this.categorySteps = categorySteps;
        this.brandVerificationSteps = brandVerificationSteps;
        this.navigationSteps = navigationSteps;
    }

    public void verifyTitleMatchesPreview(SoftAssert softAssert, NodeKey nodeName, CategoryTestCase testCase) {
        categorySteps.processEmptyCategory(softAssert, nodeName, testCase);
    }

    public void verifyCategoryWithBrands(SoftAssert softAssert, CategoryTestCase testCase) {
        brandVerificationSteps.verifyCategoryWithBrandData(softAssert, testCase);
    }

    public void verifyCategoryData(SoftAssert softAssert, CategoryTestCase testCase) {
        brandVerificationSteps.verifyCategoryOnlyData(softAssert, testCase);
    }

    public void verifyBackClickNavigation(SoftAssert softAssert) {
        navigationSteps.verifyBackClickRestoresList(softAssert);
    }
}