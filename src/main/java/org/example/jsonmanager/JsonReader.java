package org.example.jsonmanager;
import lombok.Getter;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
public class JsonReader {
    @Getter
    private static JSONObject json;

    static {
        try
        { String path = "src/test/category.Json";
            String content = new String(Files.readAllBytes(Paths.get(path)));
            json = new JSONObject(content);
        } catch (Exception e) {
            System.out.println("შეცდომა ფაილის წაკითხვისას: " + e.getMessage()); }
    }

}