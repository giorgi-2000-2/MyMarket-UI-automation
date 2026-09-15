package org.example.di.modules.modules;
import com.google.inject.AbstractModule;
import org.example.di.modules.TestScope;
import org.example.manager.*;
import org.example.steps.IPageNavigator;
import org.example.steps.PageNavigator;


public class NavigatorModule extends AbstractModule {
    public final TestScope TEST_SCOPE;

    public NavigatorModule(TestScope testScope){

        TEST_SCOPE = testScope;
    }

    @Override
    protected void configure() {


        bind(ICategoryNavigator.class).to(CategoryNavigator.class).in(TEST_SCOPE);
        bind(ICategoryWalker.class).to(CategoryWalker.class).in(TEST_SCOPE);
        bind(IBrandVerifier.class).to(BrandVerifier.class).in(TEST_SCOPE);
        bind(IPageNavigator.class).to(PageNavigator.class).in(TEST_SCOPE);


    }




}
