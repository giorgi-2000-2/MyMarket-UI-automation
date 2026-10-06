package core.testdata;

import com.google.inject.Inject;
import core.annotations.NavigationToAdvertisementPage;
import core.annotations.TestScoped;
import core.steps.IAdvertisementBusinessFlow;

import java.lang.reflect.Method;

@TestScoped
public class TestDataPreparer implements ITestDataPrepare {
    private final IAdvertisementBusinessFlow steps;

    @Inject
    public TestDataPreparer(IAdvertisementBusinessFlow steps) {
        this.steps = steps;
    }

    public void prepare(Method method, Object[] args) {
        if (!method.isAnnotationPresent(NavigationToAdvertisementPage.class)) return;

        steps.navigateToAdvertisementPage();

        if (args != null && args.length > 0 && args[0] instanceof core.testdata.CategoryTestCase testCase) {
            steps.navigationChecks(testCase);
        }
    }
}
