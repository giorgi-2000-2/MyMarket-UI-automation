package core;

import com.google.inject.Inject;
import core.annotations.NavigationToAdvertisementPage;
import core.annotations.TestScoped;
import core.steps.IAdvertisementBusinessFlow;
import core.testdata.ITestDataPrepare;
import mobile.steps.BusinessStepsMobile;
import core.testdata.CategoryTestCase;

import java.lang.reflect.Method;

@TestScoped
public class TestDataPreparer implements ITestDataPrepare {
    private final IAdvertisementBusinessFlow steps;

    @Inject
    public TestDataPreparer(BusinessStepsMobile steps) {
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
