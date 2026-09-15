package org.example.utils.reporter.stringutils;

import java.util.Arrays;

public class StringSplitter {


    public CategoryPath parseString(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("სახელი ცარიელია");
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




}
