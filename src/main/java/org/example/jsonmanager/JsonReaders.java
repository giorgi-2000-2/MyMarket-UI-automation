package org.example.jsonmanager;

import lombok.Getter;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
        } catch (IOException | JSONException e) {
            throw new IllegalStateException("JSON ფაილის წაკითხვა ვერ მოხერხდა", e);
        }
    }




}