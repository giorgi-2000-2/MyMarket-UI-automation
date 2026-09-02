package org.example.utils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
public class ConfigReader {
    private static final Properties PROPERTIES = new Properties();

    static {
        try(InputStream inputStream = Files.newInputStream(Paths.get("config.properties"))) {

            PROPERTIES.load(inputStream);

        } catch (IOException e) {

            throw new RuntimeException( "Error while reading config.properties", e );

        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
    public static long getLong(String key) {
        return Long.parseLong(PROPERTIES.getProperty(key));

    }
}