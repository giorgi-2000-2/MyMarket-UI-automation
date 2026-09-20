package web.dimoduleweb;

import com.google.inject.AbstractModule;
import core.steps.IPageNavigator;
import core.testdata.ITestDataPrepare;
import web.TestDataPreparer;
import web.steps.PageNavigator;
import core.annotations.TestScoped;
import web.driver.DriverManager;
import core.driver.IDriver;
import web.manager.CategoryNavigator;
import web.manager.CategoryWalker;
import web.manager.ICategoryNavigator;
import web.manager.ICategoryWalker;

public class WebModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IDriver.class).to(DriverManager.class).in(TestScoped.class);
        bind(ICategoryNavigator.class).to(CategoryNavigator.class).in(TestScoped.class);
        bind(ICategoryWalker.class).to(CategoryWalker.class).in(TestScoped.class);
        bind(IPageNavigator.class).to(PageNavigator.class).in(TestScoped.class);
        bind(ITestDataPrepare.class).to(TestDataPreparer.class);
    }
}