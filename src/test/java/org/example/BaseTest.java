package org.example;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.example.asserts.ISoftVerifier;
import org.example.di.modules.FrameworkModule;
import org.example.di.modules.SoftAssertListener;
import org.example.di.modules.TestScope;
import org.example.pages.advertisement.IAdvertisementPage;
import org.example.pages.basepage.IBasePage;
import org.example.pages.login.LoginPage;
import org.example.steps.BrandVerificationSteps;
import org.example.steps.CategoryNavigationSteps;
import org.example.steps.CategorySteps;
import org.example.steps.IBusinessSteps;
import org.example.utils.config.IUrlConfig;
import org.example.utils.driver.IDriver;
import org.example.utils.reporter.ReportStatus;
import org.example.utils.reporter.TestListener;
import org.example.utils.reporter.TestReporterContext;
import org.example.utils.reporter.IReporter;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;
import java.lang.reflect.Method;

@Guice(modules = FrameworkModule.class)
@Listeners({TestListener.class, SoftAssertListener.class})
public class BaseTest {
    @Inject private TestScope TEST_SCOPE;
    @Inject private   Provider<IDriver>   driver ;
    @Inject private IReporter reporter;
    @Inject private IUrlConfig config;
    @Inject private   Provider<TestDataPreparer>     dataPreparer;
    @Inject private Provider<SoftAssert> soft;
    @Inject protected Provider<ISoftVerifier>        verifier;
    @Inject protected Provider<IBusinessSteps>       steps;
     @Inject protected Provider<CategorySteps>      categorySteps;
     @Inject protected Provider<BrandVerificationSteps> brandSteps;
     @Inject protected Provider<CategoryNavigationSteps> navSteps;
    @Inject protected Provider<IAdvertisementPage> advertisementPage;
    @Inject private Provider<LoginPage>            loginPage;
    @Inject protected Provider<IBasePage>            basePage;

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method, Object[] args,ITestResult result) {
        TEST_SCOPE.enter();
        TestReporterContext.set(reporter);
        result.setAttribute("driver", driver.get().getDriver());
        result.setAttribute("softAssert", soft.get());
        TestReporterContext.lifecycle().createTest(method.getName());
        TestReporterContext.report().info("ტესტი დაიწყო: " + method.getName() );
        driver.get().getDriver().manage().window().maximize();
        driver.get().getDriver().get(config.baseUrl());
       loginPage.get().closePopUp();
       loginPage.get().closeDialogContent();
        dataPreparer.get().prepare(method,args);
    }



    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        try {
            driver.get().getDriver().quit();
        } catch (Throwable e) {
            TestReporterContext.report().log(ReportStatus.WARNING,
                    "ბრაუზერის დახურვა ვერ მოხერხდა: " + e.getMessage());
        } finally {
            try {
                TEST_SCOPE.exit();
            } finally {
                TestReporterContext.lifecycle().flush();
                TestReporterContext.lifecycle().unload();
                TestReporterContext.remove();
            }
        }
    }
        }



















