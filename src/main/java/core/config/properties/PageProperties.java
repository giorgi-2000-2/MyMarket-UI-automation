package core.config.properties;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import core.config.IPageConfig;
import core.config.IUrlConfig;
import core.config.TypedPropertiesHelper;


@Singleton
public class PageProperties implements IUrlConfig, IPageConfig {
    private final TypedPropertiesHelper helper;
    @Inject
    public PageProperties(TypedPropertiesHelper helper) {
        this.helper = helper;
    }


    @Override
    public String baseUrl() {
        return helper.require("base.url");
    }

    @Override
    public String loginUrl() {
        return helper.require("login.url");
    }

    @Override
    public String pageMainTitle() {
        return helper.require("ad.page.main.title");
    }



}
