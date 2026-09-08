package org.example.manager;

import org.json.JSONArray;
import org.json.JSONObject;


public class SubcategorySearch {


  public boolean searchInSubcategories(JSONObject nodes, String[] subCats, int index, String itemName ) {
        if (!nodes.has("subcategories")) return false;

        JSONObject subcategories = nodes.getJSONObject("subcategories");
        String targetSub = subCats[index];

        if (!subcategories.has(targetSub)) return false;


        JSONObject subNode = subcategories.getJSONObject(targetSub);

        if (index == subCats.length - 1) {
            if (subNode.has("items")) {
                return subNode.getJSONObject("items").has(itemName);
            }
            return false;
        } else {
            return searchInSubcategories(subNode, subCats, index + 1, itemName);
        }
    }

  public boolean searchBrandInSubcategories(JSONObject node, String[] subCats, int index, String itemName, String brandName) {
        try {
            if (index < subCats.length && node.has("subcategories")) {


                JSONObject subcategories = node.getJSONObject("subcategories");

                     String target = subCats[index];
             if (subcategories.has(target)) {
                    return searchBrandInSubcategories(subcategories.getJSONObject(target), subCats, index + 1, itemName, brandName);
                }
            }
            return findBrandInItem(node, itemName, brandName);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean findBrandInItem(JSONObject cat, String itemName, String brandName) {
        try {
            if (!cat.has("items")) return false;
               JSONObject items = cat.getJSONObject("items");
            if (!items.has(itemName)) return false;
               JSONObject item = items.getJSONObject(itemName);
            if (!item.has("brands")) return false;

            JSONArray brands = item.getJSONArray("brands");
            for (int i = 0; i < brands.length(); i++) {
                if (brands.getString(i).equalsIgnoreCase(brandName.trim())) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }




}
