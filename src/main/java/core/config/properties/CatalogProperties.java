package core.config.properties;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import core.config.ICatalogConfig;
import core.config.TypedPropertiesHelper;
@Singleton
public class CatalogProperties implements ICatalogConfig {
    private final TypedPropertiesHelper helper;
@Inject
    public CatalogProperties(TypedPropertiesHelper helper) {
        this.helper = helper;
    }

    @Override
    public String catalogResource() {
        return helper.require("catalog.json.resource");
    }
}
