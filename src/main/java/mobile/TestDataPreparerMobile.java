package mobile;

import com.google.inject.Inject;
import core.annotations.NavigationToAdvertisementPage;
import core.annotations.TestScoped;
import core.testdata.ITestDataPrepare;
import mobile.steps.BusinessStepsMobile;
import core.testdata.CategoryTestCase;

import java.lang.reflect.Method;

@TestScoped
public class TestDataPreparerMobile implements ITestDataPrepare {
    private final BusinessStepsMobile steps;

    @Inject
    public TestDataPreparerMobile(BusinessStepsMobile steps) {
        this.steps = steps;
    }

    public void prepare(Method method, Object[] args) {
        if (!method.isAnnotationPresent(NavigationToAdvertisementPage.class)) return;

        steps.navigateToAdvertisementPage();

        if (args != null && args.length > 0 && args[0] instanceof CategoryTestCase testCase) {
            steps.navigationChecks(testCase);
        }
    }
}
