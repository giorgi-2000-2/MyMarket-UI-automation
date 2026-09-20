package web;

import com.google.inject.Inject;
import core.annotations.NavigationToAdvertisementPage;
import core.annotations.TestScoped;
import core.testdata.CategoryTestCase;
import core.testdata.ITestDataPrepare;
import web.steps.WebBusinessSteps;


import java.lang.reflect.Method;
@TestScoped
public class TestDataPreparer implements ITestDataPrepare {
    private final WebBusinessSteps steps;

    @Inject
    public TestDataPreparer(WebBusinessSteps steps) {
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