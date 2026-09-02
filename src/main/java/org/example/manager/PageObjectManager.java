package org.example.manager;
import org.example.BasePage;
import org.example.DataProvider.DataProvider;
import org.example.Steps.BusinessSteps;
import org.example.pages.AdvertisementPage;
import org.example.pages.LoginPage;
import org.example.jsonmanager.HelperFunctions;
import org.example.utils.reporter.ITestReporter;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class PageObjectManager {
    private final WebDriver driver;
    private final ITestReporter reporter;
    private HelperFunctions helperFunctions;
    private LoginPage loginPage;
    private AdvertisementPage advertisementPage;
    private AssertManager assertHelper;
    private NewBrandManager brandManager;
    private NewDataManager dataManager;
    private DataProvider getDataProvider;
    private BusinessSteps getSteps;
    private SoftAssert getSoftAssert;
    private BasePage basePage;
    public PageObjectManager(WebDriver driver, ITestReporter reporter) {
        this.driver = driver;
        this.reporter = reporter;
    }

public DataProvider getDataProvider(){
        if(getDataProvider==null){
            getDataProvider = new DataProvider(this,reporter);
        }
        return getDataProvider;
}
 public SoftAssert getSoftAssert(){
        if(getSoftAssert==null){
            getSoftAssert=new SoftAssert();

        }
        return getSoftAssert;
 }
public BasePage getBasePage(){
        if(basePage==null){
            basePage=new BasePage(driver);
        }
        return basePage;

}
public BusinessSteps getSteps(){
    if(getSteps==null){
        getSteps = new BusinessSteps(this,reporter);
    }
    return getSteps;
}

    public NewDataManager getDataManager() {
        if (dataManager == null) {
            dataManager = new NewDataManager( this,reporter);
        }
        return dataManager;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
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
            brandManager = new NewBrandManager(this,reporter);
        }
        return brandManager;
    }

    public HelperFunctions getHFunctions() {
        if (helperFunctions == null) {
            helperFunctions = new HelperFunctions( this,reporter);
        }
        return helperFunctions;
    }
}