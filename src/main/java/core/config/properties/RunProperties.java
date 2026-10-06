package core.config.properties;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import core.config.*;

import java.util.Set;


@Singleton
public class SystemPropertiesConfig implements IAppTree,IRetryConfig {

   private final TypedPropertiesHelper helper;
    @Inject
    public SystemPropertiesConfig(TypedPropertiesHelper helper) {

        this.helper = helper;
    }

    @Override
    public String stateDirWeb() {
        return helper.optional("app.tree.state.dir.web");
    }

    @Override
    public String stateDirMobile() {
        return helper.optional("app.tree.state.dir.mobile");
    }

    @Override
    public int maxAttempts() {return helper.requireInt("max.attempts");}


}

