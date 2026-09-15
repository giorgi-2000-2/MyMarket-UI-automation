package org.example.di.modules.modules;
import com.google.inject.AbstractModule;
import org.example.di.modules.TestScope;
import org.example.pages.advertisement.AdvertisementPage;
import org.example.pages.advertisement.IAdvertisementPage;
import org.example.pages.basepage.BasePage;
import org.example.pages.basepage.IBasePage;
import org.example.pages.login.LoginPage;


public class PageModule extends AbstractModule {
    public final TestScope TEST_SCOPE;

    public PageModule(TestScope testScope){
        TEST_SCOPE = testScope;
    }

    @Override
    protected void configure() {

        bind(IBasePage.class).to(BasePage.class).in(TEST_SCOPE);
        bind(IAdvertisementPage.class).to(AdvertisementPage.class).in(TEST_SCOPE);
        bind(LoginPage.class).in(TEST_SCOPE);


    }




}
