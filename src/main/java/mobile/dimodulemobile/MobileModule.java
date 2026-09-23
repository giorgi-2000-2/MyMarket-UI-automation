package mobile.dimodulemobile;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import core.driver.IDriver;
import core.testdata.ITestDataPrepare;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import mobile.TestDataPreparerMobile;
import mobile.crawler.handler.CategoryExistenceReporter;
import mobile.crawler.handler.DataServiceExistenceReporter;
import mobile.drivermanager.DriverManagerMobile;
import core.annotations.TestScoped;

public class MobileModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new CategoryPickerModule());
        bind(CategoryExistenceReporter.class).to(DataServiceExistenceReporter.class);
        bind(IDriver.class).to(DriverManagerMobile.class);
        bind(ITestDataPrepare.class).to(TestDataPreparerMobile.class);
    }


    @Provides
    @TestScoped
    AndroidDriver androidDriver(DriverManagerMobile manager) { return manager.getDriver(); }

    @Provides @TestScoped
    AppiumDriver appiumDriver(AndroidDriver driver) { return driver; }

}