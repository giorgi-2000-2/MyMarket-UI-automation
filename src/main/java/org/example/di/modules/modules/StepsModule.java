package org.example.di.modules.modules;
import com.google.inject.AbstractModule;
import org.example.asserts.AssertSteps;
import org.example.di.modules.TestScope;
import org.example.steps.*;
import org.testng.asserts.SoftAssert;

public class StepsModule extends AbstractModule {
    public final TestScope TEST_SCOPE;

    public StepsModule(TestScope testScope){
        TEST_SCOPE = testScope;
    }

    @Override
    protected void configure() {
        bind(SoftAssert.class).in(TEST_SCOPE);

        bind(AssertSteps.class).in(TEST_SCOPE);
        bind(CategorySteps.class).in(TEST_SCOPE);
        bind(BrandVerificationSteps.class).in(TEST_SCOPE);
        bind(CategoryNavigationSteps.class).in(TEST_SCOPE);
        bind(DataStepsManager.class).in(TEST_SCOPE);
        bind(IBusinessSteps.class).to(BusinessSteps.class).in(TEST_SCOPE);
    }




}
