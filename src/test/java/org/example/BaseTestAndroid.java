package org.example;
import com.google.inject.Inject;
import com.google.inject.Provider;
import core.driver.IDriver;
import core.modules.CoreModule;
import core.modules.SoftAssertListener;
import core.modules.TestScope;
import core.reporter.IReporter;
import core.reporter.TestListener;
import core.testdata.ITestDataPrepare;
import core.utils.TestAttributes;
import mobile.dimodulemobile.MobileModule;
import mobile.steps.BusinessStepsMobile;
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
    public void setup(Method method, Object[] args, ITestResult result) {
        TEST_SCOPE.enter();
        result.setAttribute(TestAttributes.REPORTER.key(), reporter);
        result.setAttribute(TestAttributes.DRIVER.key(), driverManager.get().getDriver());
        result.setAttribute(TestAttributes.SOFT_ASSERT.key(), soft.get());
        reporter.createTest(method.getName());
        dataPreparer.get().prepare(method, args);
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        try {
            driverManager.get().quit();
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