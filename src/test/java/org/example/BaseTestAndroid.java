package org.example;
import com.google.inject.Inject;
import com.google.inject.Provider;
import core.driver.IDriver;
import core.modules.CoreModule;
import core.testdata.ITestDataPrepare;
import mobile.dimodulemobile.MobileModule;
import core.modules.SoftAssertListener;
import core.reporter.IReporter;
import core.reporter.TestListener;
import core.reporter.TestReporterContext;
import mobile.steps.BusinessStepsMobile;
import core.modules.TestScope;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;

@Guice(modules = {CoreModule.class, MobileModule.class})
@Listeners({TestListener.class, SoftAssertListener.class})
public class BaseTestAndroid {
    @Inject private TestScope TEST_SCOPE;
    @Inject private Provider<IDriver> driverManager;
    @Inject private IReporter reporter;
    @Inject private Provider<SoftAssert> soft;
    @Inject private Provider<ITestDataPrepare> dataPreparer;
    @Inject protected Provider<BusinessStepsMobile> steps;

    @BeforeMethod(alwaysRun = true)
    public void setup(Method method,Object[] args, ITestResult result) {
        TEST_SCOPE.enter();
        TestReporterContext.set(reporter);
        TestReporterContext.lifecycle().createTest(method.getName());
        result.setAttribute("driver", driverManager.get().getDriver());
        result.setAttribute("softAssert", soft.get());
        dataPreparer.get().prepare(method,args);
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        try {
            driverManager.get().quit();
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


