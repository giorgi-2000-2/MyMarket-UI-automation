package org.example.steps;
import com.google.inject.Inject;
import org.example.dataprovider.CategoryTestCase;
import org.example.utils.reporter.NodeKey;
import org.testng.asserts.SoftAssert;

public class DataStepsManager {
    private final CategorySteps categorySteps;
    private final BrandVerificationSteps brandVerificationSteps;
    private final CategoryNavigationSteps navigationSteps;
    @Inject
    public DataStepsManager(CategorySteps categorySteps, BrandVerificationSteps brandVerificationSteps, CategoryNavigationSteps navigationSteps) {

        this.categorySteps = categorySteps;
        this.brandVerificationSteps = brandVerificationSteps;
        this.navigationSteps = navigationSteps;
    }

    public void verifyTitleMatchesPreview(  NodeKey nodeName, CategoryTestCase testCase) {
        categorySteps.processEmptyCategory( nodeName, testCase);
    }

    public void verifyCategoryWithBrands( CategoryTestCase testCase) {
        brandVerificationSteps.verifyCategoryWithData( testCase);
    }


    public void verifyBackClickNavigation() {
        navigationSteps.verifyBackClickRestoresList();
    }
}