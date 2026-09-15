package org.example.utils.config;
import com.google.inject.Inject;
import org.example.utils.config.properties.CategoryNameBtn;

import java.util.Properties;


public class PropertiesConfig implements IWait,IUrlConfig,IUserConfig,IPageConfig,IBtnUrl{
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





    private String resolve(String key) {
        return props.getProperty(key);

    }

    private String require(String key) {
        String value = resolve(key);
        if (!isUsable(value)) {
            throw new IllegalStateException("კონფიგის გასაღები აკლია " + key);
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
                    "კონფიგის გასაღები " + key + " არ არის რიცხვი: " + value + e);
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

