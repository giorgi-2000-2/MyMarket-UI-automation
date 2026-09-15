package org.example;

import com.google.inject.Inject;
import org.example.annotations.NavigationToAdvertisementPage;
import org.example.dataprovider.CategoryTestCase;
import org.example.di.modules.TestContext;
import org.example.steps.IBusinessSteps;

import java.lang.reflect.Method;

public class TestDataPreparer {
    private final IBusinessSteps steps;
    private final TestContext context;

    @Inject
    public TestDataPreparer(IBusinessSteps steps, TestContext context) {
        this.steps = steps;
        this.context = context;
    }

    public void prepare(Method method, Object[] args) {
        if (!method.isAnnotationPresent(NavigationToAdvertisementPage.class)) return;

        steps.NavigateToAdvertisementPage();

        if (args != null && args.length > 0 && args[0] instanceof CategoryTestCase testCase) {
            context.setTestCase(testCase);
            steps.navigationChecks(context.getTestCase());
        }
    }
}