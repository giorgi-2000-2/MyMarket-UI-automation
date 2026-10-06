package mobile.dimodulemobile;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import core.config.IAppTree;
import core.steps.IAdvertisementBusinessFlow;
import mobile.steps.MobilePageNavigator;
import uicommon.driver.IDriver;
import core.steps.ICategoryCheckSteps;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import mobile.crawler.handler.CategoryExistenceReporter;
import mobile.crawler.handler.DataServiceExistenceReporter;
import mobile.drivermanager.DriverManagerMobile;
import core.annotations.TestScoped;
import mobile.steps.BusinessStepsMobile;

public class MobileModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new CategoryPickerModule());
        bind(CategoryExistenceReporter.class).to(DataServiceExistenceReporter.class);
        bind(IDriver.class).to(DriverManagerMobile.class);
        bind(ICategoryCheckSteps.class).to(BusinessStepsMobile.class);
        bind(IAdvertisementBusinessFlow.class).to(MobilePageNavigator.class);
    }

    @Provides
    @TestScoped
    @Named("crawlStateDir")
    String crawlStateDir(IAppTree config) {
        return config.stateDirMobile();
    }

    @Provides
    @TestScoped
    AndroidDriver androidDriver(DriverManagerMobile manager) { return manager.getDriver(); }

    @Provides @TestScoped
    AppiumDriver appiumDriver(AndroidDriver driver) { return driver; }

}