package org.example.di.modules.modules;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import org.example.utils.config.*;

public class ConfigModule extends AbstractModule {

public ConfigModule(){

}
    @Override
    protected void configure() {
        bind(PropertiesConfig.class).in(Singleton.class);
        bind(IWait.class).to(PropertiesConfig.class);
        bind(IUrlConfig.class).to(PropertiesConfig.class);
        bind(IUserConfig.class).to(PropertiesConfig.class);
        bind(IPageConfig.class).to(PropertiesConfig.class);
        bind(IBtnUrl.class).to(PropertiesConfig.class);


    }




}
