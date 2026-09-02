package org.example;
import org.example.Annotations.NavigationToAdvertisementPage;
import org.example.manager.PageObjectManager;
import org.example.utils.DriverManager;
import org.example.utils.config.ConfigReader;
import org.example.utils.reporter.ExtentTestReporter;
import org.example.utils.reporter.ITestReporter;
import org.example.utils.reporter.TestReporterContext;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import java.lang.reflect.Method;


public class BaseTest {
    private static final ThreadLocal<PageObjectManager> pomThreadLocal = new ThreadLocal<>();
    protected static final ThreadLocal<SoftAssert> softassert =new ThreadLocal<>();
     private final ITestReporter reporter = new ExtentTestReporter();
    @BeforeMethod
    public void setUp(Method method) {
        TestReporterContext.set(reporter);
        WebDriver driver = DriverManager.getDriver();
        pomThreadLocal.set(new PageObjectManager(driver,reporter));
        softassert.set(getPom().getSoftAssert());
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("base.url"));
        getPom().getLoginPage().closePopUp();
        getPom().getLoginPage().closeDialogContent();

        if(method.isAnnotationPresent(NavigationToAdvertisementPage.class)){
getPom().getSteps().loginAndNavigate(softassert.get());
        }

    }


    protected PageObjectManager getPom() {
        return pomThreadLocal.get();
    }

    @AfterMethod
    public void tearDown() {
        TestReporterContext.get().flush();
        TestReporterContext.get().unload();
        TestReporterContext.remove();
        DriverManager.quit();
        pomThreadLocal.remove();
    }




















}