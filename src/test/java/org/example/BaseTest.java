package org.example;
import org.example.di.TestContext;
import org.example.utils.reporter.driver.DriverManager;
import org.example.utils.reporter.ExtentTestReporter;
import org.example.utils.reporter.TestReporterContext;
import org.example.utils.reporter.driver.IDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import java.lang.reflect.Method;

import static org.example.di.SuiteContext.url;


public class BaseTest {
    private final IDriver driver = new DriverManager();
    private final ThreadLocal<TestContext> testContextThreadLocal = new ThreadLocal<>();
    protected final ThreadLocal<SoftAssert> softassert =new ThreadLocal<>();
    private final ExtentTestReporter REPORTER = new ExtentTestReporter();
    private final ThreadLocal<TestDataPreparer> dataPreparer = new ThreadLocal<>();
    @BeforeMethod
    public void setUp(Method method) {
        TestReporterContext.set(REPORTER);
        TestReporterContext.lifecycle().createTest(method.getName());
        testContextThreadLocal.set(new TestContext(driver.getDriver(),REPORTER));
        softassert.set(getPom().softAssert());
        driver.getDriver().manage().window().maximize();
        driver.getDriver().get(url().baseUrl());
        getPom().login().closePopUp();
        getPom().login().closeDialogContent();
        dataPreparer.set(new TestDataPreparer(getPom().steps(),softassert.get()));
        dataPreparer.get().prepare(method);

    }

    protected TestContext getPom() {
        return testContextThreadLocal.get();
    }

    @AfterMethod
    public void tearDown() {
        TestReporterContext.lifecycle().flush();
        TestReporterContext.lifecycle().unload();
        TestReporterContext.remove();
        driver.quit();
        testContextThreadLocal.remove();
        softassert.remove();
        dataPreparer.remove();
    }




















}