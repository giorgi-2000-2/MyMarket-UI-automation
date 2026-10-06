package core.jsonmanager;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import core.config.ICatalogConfig;
import core.reporter.texts.ErrorMessages;
import lombok.Getter;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Getter
@Singleton
public class JsonReaders {

    private final JSONObject json;

    @Inject
    public JsonReaders(ICatalogConfig config) {
        this(config.catalogResource());
    }

    public JsonReaders(String resource) {
        this.json = load(resource);
    }

    private static JSONObject load(String resource) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream in = loader.getResourceAsStream(resource)) {
            if (in == null) {
                throw new IllegalStateException(
                        ErrorMessages.JSON_READ_FAILED.format("classpath-ზე ვერ მოიძებნა: " + resource));
            }
            String content = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            return new JSONObject(content);
        } catch (IOException | JSONException e) {
            throw new IllegalStateException(ErrorMessages.JSON_READ_FAILED.format(e), e);
        }
    }
}