package core.config;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import core.reporter.texts.ErrorMessages;

import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;
@Singleton
public class TypedPropertiesHelper {
    private final String FILE = "config.properties";
    private final Properties props;

    @Inject
    public TypedPropertiesHelper() {
        Properties fromFile = ConfigSource.fromClasspath(FILE);
        this.props = fromFile;
    }


    public String resolve(String key) {
        return props.getProperty(key);

    }

    public String require(String key) {
        String value = resolve(key);
        if (!isUsable(value)) {
            throw new IllegalStateException(ErrorMessages.CONFIG_KEY_MISSING.format(key));
        }
        return value.trim();
    }
    public String secret(String envKey, String propKey) {
        String fromEnv = System.getenv(envKey);
        return (fromEnv != null && !fromEnv.isBlank()) ? fromEnv : require(propKey);
    }
    public int requireInt(String key) {
        String value = require(key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalStateException(
                    ErrorMessages.CONFIG_KEY_NOT_NUMBER.format(key,value + e));
        }
    }
    public Set<String> requireSet(String key) {
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
    public String optional(String key) {
        String value = resolve(key);
        return isUsable(value) ? value.trim() : "";
    }
    public double requireDouble(String key) {
        String value = require(key);
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new IllegalStateException(
                    ErrorMessages.CONFIG_KEY_NOT_NUMBER.format(key, value));
        }
    }

    public long requireLong(String key) {
        String value = require(key);
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new IllegalStateException(
                    ErrorMessages.CONFIG_KEY_NOT_NUMBER.format(key, value));
        }
    }
    public boolean isUsable(String value) {
        return value != null && !value.trim().isEmpty();
    }



}
