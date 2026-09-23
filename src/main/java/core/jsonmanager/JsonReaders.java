package core.jsonmanager;

import com.google.inject.Singleton;
import lombok.Getter;
import core.reporter.texts.ErrorMessages;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
@Singleton
public class JsonReaders {
    @Getter
    private final JSONObject json;

     {
        try {
            String path = "src/test/category.Json";
            String content = new String(Files.readAllBytes(Paths.get(path)));
            json = new JSONObject(content);
        } catch (IOException | JSONException e) {
            throw new IllegalStateException(ErrorMessages.JSON_READ_FAILED.format(e));
        }
    }




}