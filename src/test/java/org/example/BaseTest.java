package org.example;
import com.google.inject.Inject;
import com.google.inject.Provider;
import core.modules.*;
import core.reporter.*;
import core.reporter.texts.AssertMessages;
import core.reporter.texts.ErrorMessages;
import core.testdata.ITestDataPrepare;
import core.utils.TestAttributes;
import web.dimoduleweb.WebModule;
import web.pages.login.DialogContent;
import web.steps.WebBusinessSteps;
import core.config.IUrlConfig;
import core.driver.IDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;
import java.lang.reflect.Method;

@Guice(modules = {CoreModule.class, WebModule.class})
@Listeners({TestListener.class, SoftAssertListener.class})
public class BaseTest {
    @Inject private TestScope TEST_SCOPE;
    @Inject private Provider<IDriver> driver;
    @Inject private IReporter reporter;
    @Inject private IUrlConfig config;
    @Inject private Provider<ITestDataPrepare> dataPreparer;
    @Inject private Provider<SoftAssert> soft;
    @Inject protected Provider<WebBusinessSteps> steps;
    @Inject private Provider<DialogContent> content;

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method, Object[] args, ITestResult result) {
        TEST_SCOPE.enter();
        result.setAttribute(TestAttributes.REPORTER.key(), reporter);
        result.setAttribute(TestAttributes.DRIVER.key(), driver.get().getDriver());
        result.setAttribute(TestAttributes.SOFT_ASSERT.key(), soft.get());
        reporter.createTest(method.getName());
        reporter.info(AssertMessages.TEST_STARTED.format(method.getName()));
        driver.get().getDriver().manage().window().maximize();
        driver.get().getDriver().get(config.baseUrl());
        content.get().closePopUp();
        content.get().closeDialogContent();
        dataPreparer.get().prepare(method, args);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        try {
            driver.get().quit();
        } catch (Throwable e) {
            reporter.log(ReportStatus.WARNING,
                    ErrorMessages.BROWSER_CLOSE_FAILED.format(e.getMessage()));
        } finally {
            try {
                TEST_SCOPE.exit();
            } finally {
                reporter.flush();
                reporter.unload();
            }
        }
    }
}