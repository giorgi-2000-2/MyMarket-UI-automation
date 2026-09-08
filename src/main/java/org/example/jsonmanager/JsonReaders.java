package org.example.jsonmanager;

import lombok.Getter;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonReaders {
    @Getter
    private final JSONObject json;

     {
        try {
            String path = "src/test/category.Json";
            String content = new String(Files.readAllBytes(Paths.get(path)));
            json = new JSONObject(content);
        } catch (Exception e) {
            throw new IllegalStateException("კრიტიკული შეცდომა: category.Json ფაილი ვერ ჩაიტვირთა ", e);
        }
    }
}