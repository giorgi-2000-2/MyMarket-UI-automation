package core.modules;

import com.google.inject.AbstractModule;
import core.config.*;
import core.reporter.IReportLifecycle;
import core.reporter.IReportNode;
import core.reporter.IReportTree;
import core.reporter.IReporter;
import core.reporter.extentreport.ExtentTestReporter;
import org.testng.asserts.SoftAssert;
import core.annotations.TestScoped;

public class CoreModule extends AbstractModule {
    private final TestScope testScope = new TestScope();

    @Override
    protected void configure() {
        bindScope(TestScoped.class, testScope);
        bind(TestScope.class).toInstance(testScope);
        bind(SoftAssert.class).in(TestScoped.class);

        bind(IWait.class).to(PropertiesConfig.class);
        bind(IUrlConfig.class).to(PropertiesConfig.class);
        bind(IUserConfig.class).to(PropertiesConfig.class);
        bind(IPageConfig.class).to(PropertiesConfig.class);
        bind(IBtnUrl.class).to(PropertiesConfig.class);
        bind(IWaitSettings.class).to(PropertiesConfig.class);
        bind(IScrollConfig.class).to(PropertiesConfig.class);
        bind(ICategoryLabels.class).to(PropertiesConfig.class);
        bind(IPatternConfig.class).to(PropertiesConfig.class);
        bind(IReporter.class).to(ExtentTestReporter.class);
        bind(IReportTree.class).to(ExtentTestReporter.class);
        bind(IReportNode.class).to(ExtentTestReporter.class);
        bind(IReportLifecycle.class).to(ExtentTestReporter.class);
    }
}