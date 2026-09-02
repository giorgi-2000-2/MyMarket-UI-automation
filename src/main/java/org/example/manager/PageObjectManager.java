package org.example.manager;
import org.example.DataProvider.DataProvider;
import org.example.Steps.BusinessSteps;
import org.example.pages.AdvertisementPage;
import org.example.pages.LoginPage;
import org.example.utils.reporter.ITestReporter;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class PageObjectManager {
    private final WebDriver driver;
    private final ITestReporter reporter;
    private LoginPage loginPage;
    private AdvertisementPage advertisementPage;
    private AssertManager assertHelper;
    private NewBrandManager brandManager;
    private NewDataManager dataManager;
    private DataProvider getDataProvider;
    private BusinessSteps getSteps;
    private SoftAssert getSoftAssert;
    public PageObjectManager(WebDriver driver, ITestReporter reporter) {
        this.driver = driver;
        this.reporter = reporter;
    }

public DataProvider getDataProvider(){
        if(getDataProvider==null){
            getDataProvider = new DataProvider(getAssert(),reporter);
        }
        return getDataProvider;
}
 public SoftAssert getSoftAssert(){
        if(getSoftAssert==null){
            getSoftAssert=new SoftAssert();

        }
        return getSoftAssert;
 }

public BusinessSteps getSteps(){
    if(getSteps==null){
        getSteps = new BusinessSteps(getAdPage(),getAssert(),getLoginPage(),reporter,getDataManager());
    }
    return getSteps;
}

    public NewDataManager getDataManager() {
        if (dataManager == null) {
            dataManager = new NewDataManager( getAdPage(),getDataProvider(),getBrandManager(),getAssert(),reporter);
        }
        return dataManager;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver,reporter);
        }
        return loginPage;
    }

    public AdvertisementPage getAdPage() {
        if (advertisementPage == null) {
            advertisementPage = new AdvertisementPage(driver,reporter);
        }
        return advertisementPage;
    }

    public AssertManager getAssert() {
        if (assertHelper == null) {
            assertHelper = new AssertManager(reporter);
        }
        return assertHelper;
    }

    public NewBrandManager getBrandManager() {
        if (brandManager == null) {
            brandManager = new NewBrandManager(getAdPage(),reporter,getAssert());
        }
        return brandManager;
    }

}