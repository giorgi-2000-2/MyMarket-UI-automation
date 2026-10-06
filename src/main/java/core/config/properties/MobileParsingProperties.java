package core.config.properties;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import core.config.ICategoryLabels;
import core.config.IPatternConfig;
import core.config.TypedPropertiesHelper;

import java.util.Set;
@Singleton
public class MobileParsingProperties implements ICategoryLabels, IPatternConfig {

    private final TypedPropertiesHelper helper;
@Inject
    public MobileParsingProperties(TypedPropertiesHelper helper) {
        this.helper = helper;
    }

    @Override
    public Set<String> systemLabels() {
        return helper.requireSet("category.labels.system");
    }

    @Override
    public Set<String> autoConfirmLabels() {
        return helper.requireSet("category.labels.auto.confirm");
    }

    @Override
    public Set<String> autoCloseLabels() {
        return helper.requireSet("category.labels.auto.close");
    }

    @Override
    public String itemPattern() {return helper.require("pattern.item");}

    @Override
    public String boundsPattern() {return helper.require("pattern.bounds");}

}
