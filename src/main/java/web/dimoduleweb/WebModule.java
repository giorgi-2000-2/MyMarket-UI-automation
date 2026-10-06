package web.dimoduleweb;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import core.config.IAppTree;
import core.steps.*;
import core.annotations.TestScoped;
import web.driver.DriverManager;
import uicommon.driver.IDriver;
import web.manager.CategoryNavigator;
import web.manager.CategoryWalker;
import web.manager.ICategoryNavigator;
import web.manager.ICategoryWalker;
import web.config.ISectionUrls;
import web.config.SectionUrlProperties;
import web.steps.PageNavigator;
import web.steps.WebBusinessSteps;

public class WebModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IDriver.class).to(DriverManager.class).in(TestScoped.class);
        bind(ICategoryNavigator.class).to(CategoryNavigator.class).in(TestScoped.class);
        bind(ICategoryWalker.class).to(CategoryWalker.class).in(TestScoped.class);
        bind(ICategoryCheckSteps.class).to(WebBusinessSteps.class);
        bind(ITitleCheckSteps.class).to(WebBusinessSteps.class);
        bind(IBackNavigationSteps.class).to(WebBusinessSteps.class);
        bind(IAdvertisementBusinessFlow.class).to(PageNavigator.class);
        bind(ISectionUrls.class).to(SectionUrlProperties.class);

    }


    @Provides
    @TestScoped
    @Named("crawlStateDir")
    String crawlStateDir(IAppTree config) {
        return config.stateDirWeb();
    }
}
