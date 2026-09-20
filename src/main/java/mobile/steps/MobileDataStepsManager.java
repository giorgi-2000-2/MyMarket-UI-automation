package mobile.steps;
import com.google.inject.Inject;
import core.annotations.TestScoped;
import core.testdata.CategoryTestCase;
@TestScoped
public class MobileDataStepsManager {
   private final MobileBrandVerificationSteps brandVerificationSteps;
@Inject
    public MobileDataStepsManager(MobileBrandVerificationSteps brandVerificationSteps) {
    this.brandVerificationSteps = brandVerificationSteps;
}


    public void verifyCategoryWithBrands(String child, CategoryTestCase testCase) {
        brandVerificationSteps.verifyCategoryWithData(child,testCase);
    }
}