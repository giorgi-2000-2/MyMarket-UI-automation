package org.example.utils.config;

import java.util.Properties;



public class PropertiesConfig implements IWait,IBtnUrl,IUrlConfig,IUserConfig{
    private final String FILE = "config.properties";
    private final Properties props;


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
    public String sellUrl() {
        return require("sell.url");
    }

    @Override
    public String buyUrl() {
        return require("buy.url");
    }

    @Override
    public String rentUrl() {
        return require("rent.url");
    }

    @Override
    public String serviceUrl() {
        return require("service.url");
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
    public String loginMail() {
        return require("login.mail");
    }

    @Override
    public String loginPassword() {
        return require("login.password");
    }

    @Override
    public String userId() {
        return require("login.id");
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



}
