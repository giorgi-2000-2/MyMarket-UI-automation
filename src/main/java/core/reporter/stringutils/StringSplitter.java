package core.reporter.stringutils;

import com.google.inject.Singleton;
import core.reporter.texts.ErrorMessages;

import java.util.Arrays;
import java.util.List;


@Singleton
public class StringSplitter {

    public CategoryPath parseString(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(ErrorMessages.NAME_ISEMPTY.get());
        }

        String[] parts = name.split(" -> ");

        String[] subCategories = parts.length > 2
                ? Arrays.copyOfRange(parts, 1, parts.length - 1)
                : new String[0];

        return new CategoryPath(
                parts[0].trim(),
                parts[parts.length - 1].trim(),
                subCategories
        );
    }



    public String getSplitString(String str){
        String[] arr = str.split(" -> ");
        return arr[arr.length-1];
    }


    public String key(List<String> path) {
        return join(path);
    }


    private String join(List<String> parts) {
        return parts == null || parts.isEmpty() ? "" : String.join(" -> ", parts);
    }
}
