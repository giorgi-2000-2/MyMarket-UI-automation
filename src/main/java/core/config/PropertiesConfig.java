package core.config;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import core.config.properties.CategoryNameBtn;
import core.reporter.ReportMessages;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

@Singleton
public class PropertiesConfig implements IWait, IUrlConfig, IUserConfig, IPageConfig, IBtnUrl,
        ICategoryLabels, IScrollConfig, IWaitSettings,IPatternConfig {
    private final String FILE = "config.properties";
    private final Properties props;

    @Inject
    public PropertiesConfig() {
        Properties fromFile = ConfigSource.fromClasspath(FILE);
        this.props = fromFile;
    }

    PropertiesConfig(Properties props) {
        this.props = props;
    }

@Override
public int longWait() {
    return requireInt("long.wait");
}

    @Override
    public int shortWait() {
return requireInt("short.wait");
    }

    @Override
    public int textWait() {
        return requireInt("text.wait");
    }

    @Override
    public String baseUrl() {
        return require("base.url");
    }

    @Override
    public String visitUrl() {
        return require("visit.url");
    }

    @Override
    public String loginUrl() {
        return require("login.url");
    }

    @Override
    public String loginMail()     { return secret("MYMARKET_USER", "login.mail"); }

    @Override
    public String loginPassword() { return secret("MYMARKET_PASSWORD", "login.password"); }

    @Override
    public String userId() {
        return require("login.id");
    }

    @Override
    public String expectedUserName() {
        return require("name");
    }

    @Override
    public String pageMainTitle() {
        return require("ad.page.main.title");
    }


    @Override
    public Set<String> systemLabels() {
        return requireSet("category.labels.system");
    }

    @Override
    public Set<String> autoConfirmLabels() {
        return requireSet("category.labels.auto.confirm");
    }

    @Override
    public Set<String> autoCloseLabels() {
        return requireSet("category.labels.auto.close");
    }


    @Override
    public double nudgeFactor() {
        return requireDouble("scroll.nudge.factor");
    }

    @Override
    public int centerTolerance() {
        return requireInt("scroll.center.tolerance");
    }

    @Override
    public double minY() {
        return requireDouble("scroll.min.y");
    }

    @Override
    public double maxY() {
        return requireDouble("scroll.max.y");
    }

    @Override
    public int swipeMs() {
        return requireInt("scroll.swipe.ms");
    }

    @Override
    public int maxScrolls() {
        return requireInt("scroll.max.scrolls");
    }


    @Override
    public long defaultTimeoutMs() {
        return requireLong("wait.default.timeout.ms");
    }

    @Override
    public long transitionTimeoutMs() {
        return requireLong("wait.transition.timeout.ms");
    }

    @Override
    public long openTimeoutMs() {
        return requireLong("wait.open.timeout.ms");
    }

    @Override
    public long pollMs() {
        return requireLong("wait.poll.ms");
    }

    @Override
    public String itemPattern() {return require("pattern.item");}

    @Override
    public String boundsPattern() {return require("pattern.bounds");}

    private String resolve(String key) {
        return props.getProperty(key);

    }

    private String require(String key) {
        String value = resolve(key);
        if (!isUsable(value)) {
            throw new IllegalStateException(ReportMessages.CONFIG_KEY_MISSING.format(key));
        }
        return value.trim();
    }
    private String secret(String envKey, String propKey) {
        String fromEnv = System.getenv(envKey);
        return (fromEnv != null && !fromEnv.isBlank()) ? fromEnv : require(propKey);
    }
    private int requireInt(String key) {
        String value = require(key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalStateException(
                    ReportMessages.CONFIG_KEY_NOT_NUMBER.format(key,value + e));
        }
    }
    private Set<String> requireSet(String key) {
        String value = require(key);
        Set<String> result = new LinkedHashSet<>();
        String[] parts = value.split(",");
        for (String part : parts) {
            String trimmed = part.trim();
            if (!trimmed.isEmpty()) {
                result.add(trimmed);
            }
        }
        return result;
    }

    private double requireDouble(String key) {
        String value = require(key);
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new IllegalStateException(
                    ReportMessages.CONFIG_KEY_NOT_NUMBER.format(key, value));
        }
    }

    private long requireLong(String key) {
        String value = require(key);
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new IllegalStateException(
                    ReportMessages.CONFIG_KEY_NOT_NUMBER.format(key, value));
        }
    }
    private static boolean isUsable(String value) {
        return value != null && !value.trim().isEmpty();
    }

    @Override
    public String btnUrl(CategoryNameBtn section) {
        return require(section.getUrlKey());
    }


}

