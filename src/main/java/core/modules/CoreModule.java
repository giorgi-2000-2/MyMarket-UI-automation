package core.modules;

import com.google.inject.AbstractModule;
import core.catalog.ICatalog;
import core.config.*;
import core.jsonmanager.JsonFinder;
import core.config.properties.*;
import core.reporter.IReportLifecycle;
import core.reporter.IReportNode;
import core.reporter.IReportTree;
import core.reporter.IReporter;
import core.reporter.allure.AllureTestReporter;
import core.reporter.extentreport.ExtentTestReporter;
import core.testdata.ITestDataPrepare;
import core.testdata.TestDataPreparer;
import org.testng.asserts.SoftAssert;
import core.annotations.TestScoped;

public class CoreModule extends AbstractModule {
    private final TestScope testScope = new TestScope();
    String engine = readReportEngine();
    @Override
    protected void configure() {
        bindScope(TestScoped.class, testScope);
        bind(TestScope.class).toInstance(testScope);
        bind(SoftAssert.class).in(TestScoped.class);

        bind(ITimeoutConfig.class).to(WaitProperties.class);
        bind(IAppTree.class).to(RunProperties.class);
        bind(IWait.class).to(WaitProperties.class);
        bind(IUrlConfig.class).to(PageProperties.class);
        bind(IUserConfig.class).to(UserProperties.class);
        bind(IPageConfig.class).to(PageProperties.class);
        bind(IScrollConfig.class).to(ScrollProperties.class);
        bind(ICategoryLabels.class).to(MobileParsingProperties.class);
        bind(IPatternConfig.class).to(MobileParsingProperties.class);
        bind(IRetryConfig.class).to(RunProperties.class);
        bind(ICatalog.class).to(JsonFinder.class);
        bind(ICatalogConfig.class).to(CatalogProperties.class);
        bind(ITestDataPrepare.class).to(TestDataPreparer.class);
        if ("allure".equalsIgnoreCase(engine)) {
            bind(IReporter.class).to(AllureTestReporter.class);
            bind(IReportTree.class).to(AllureTestReporter.class);
            bind(IReportNode.class).to(AllureTestReporter.class);
            bind(IReportLifecycle.class).to(AllureTestReporter.class);
        } else {
            bind(IReporter.class).to(ExtentTestReporter.class);
            bind(IReportTree.class).to(ExtentTestReporter.class);
            bind(IReportNode.class).to(ExtentTestReporter.class);
            bind(IReportLifecycle.class).to(ExtentTestReporter.class);
        }

    }



    private String readReportEngine() {
        String fromSys = System.getProperty("report.engine");
        if (fromSys != null && !fromSys.isBlank()) {
            return fromSys.trim();
        }

        try (var in = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (in != null) {
                var p = new java.util.Properties();
                p.load(in);
                String v = p.getProperty("report.engine", "extent");
                return v == null ? "extent" : v.trim();
            }
        } catch (Exception ignored) {}
        return "extent";
    }



}