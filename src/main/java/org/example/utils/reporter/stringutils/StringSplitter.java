package org.example.utils.reporter.stringutils;

import java.util.Arrays;

public class StringSplitter {


public CategoryPath parseString(String name){
    String[] parts = name.split(" -> ");
    String mainCategory = parts[0];
    String itemName = parts[parts.length - 1];
    String[] subCategories = Arrays.copyOfRange(parts, 1, parts.length - 1);
    return new CategoryPath(mainCategory, itemName, subCategories);
}



    public String getSplitString(String str){
        String[] arr = str.split(" -> ");
        return arr[arr.length-1];
    }




}
