package core.jsonmanager;

import com.google.inject.Singleton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
@Singleton
public class SubcategorySearch {

    public boolean searchInSubcategories(JSONObject nodes, String[] subCats, int index, String itemName) {
        if (nodes == null || subCats == null || index >= subCats.length || !nodes.has("subcategories")) {
            return false;
        }

        try {
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
        } catch (JSONException e) {
            return false;
        }
    }

    public boolean searchBrandInSubcategories(JSONObject node, String[] subCats, int index, String itemName, String brandName) {
        if (node == null || subCats == null) return false;

        try {
            if (index < subCats.length && node.has("subcategories")) {
                JSONObject subcategories = node.getJSONObject("subcategories");
                String target = subCats[index];
                if (subcategories.has(target)) {
                    return searchBrandInSubcategories(subcategories.getJSONObject(target), subCats, index + 1, itemName, brandName);
                }
            }
            return findBrandInItem(node, itemName, brandName);
        } catch (JSONException e) {
            return false;
        }
    }

    public boolean findBrandInItem(JSONObject cat, String itemName, String brandName) {
        if (cat == null || itemName == null || brandName == null) return false;

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
        } catch (JSONException e) {
            return false;
        }
        return false;
    }
}