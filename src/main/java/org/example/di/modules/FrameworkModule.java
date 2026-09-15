package org.example.di.modules;

import com.google.inject.AbstractModule;
import org.example.annotations.TestScoped;
import org.example.di.modules.modules.*;

public class FrameworkModule extends AbstractModule {
    private final TestScope TEST_SCOPE = new TestScope();

    @Override
    protected void configure() {
        bindScope(TestScoped.class, TEST_SCOPE);
        bind(TestScope.class).toInstance(TEST_SCOPE);
        install(new ConfigModule());
        install(new ReportModule());
        install(new DriverModule(TEST_SCOPE));
        install(new PageModule(TEST_SCOPE));
        install(new StepsModule(TEST_SCOPE));
        install(new DataModule(TEST_SCOPE));
        install(new AssertModule(TEST_SCOPE));
        install(new NavigatorModule(TEST_SCOPE));
        install(new ComponentModule(TEST_SCOPE));
        install(new WaitHelperModule(TEST_SCOPE));
    }
}