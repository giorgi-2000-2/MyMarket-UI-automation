package core.config.properties;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import core.config.ITimeoutConfig;
import core.config.IWait;
import core.config.TypedPropertiesHelper;
@Singleton
public class WaitProperties implements IWait, ITimeoutConfig {
    private final TypedPropertiesHelper helper;
@Inject
    public WaitProperties(TypedPropertiesHelper helper) {
        this.helper = helper;
    }

    @Override
    public int longWait() {
        return helper.requireInt("long.wait");
    }
    @Override
    public int shortWait() {
        return helper.requireInt("short.wait");
    }
    @Override
    public int textWait() {
        return helper.requireInt("text.wait");
    }

    @Override
    public long openTimeoutMs() {
        return helper.requireLong("wait.open.timeout.ms");
    }

    @Override
    public long pollMs() {
        return helper.requireLong("wait.poll.ms");
    }
    @Override
    public long transitionTimeoutMs() {
        return helper.requireLong("wait.transition.timeout.ms");
    }

}
