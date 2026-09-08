package org.example.jsonmanager;

import org.example.dataprovider.SectionNames;
import org.example.manager.SubcategorySearch;
import org.example.utils.reporter.stringutils.CategoryPath;
import org.json.JSONObject;
public class JsonFinder {
    private final SubcategorySearch helper;
private final JsonReaders jsonReader;
    public JsonFinder(SubcategorySearch helper, JsonReaders jsonReader ) {
        this.helper = helper;
        this.jsonReader = jsonReader;
    }

    public boolean findJsonData(CategoryPath parts){
        JSONObject json = jsonReader.getJson();
        boolean found=false;
        for (SectionNames sectionEnum : SectionNames.values()) {
            String section = sectionEnum.getPath();

            if (!json.getJSONObject(section).has(parts.getMainCategory())) {
                continue;
            }

            JSONObject mainCat = json.getJSONObject(section).getJSONObject(parts.getMainCategory());

            if (parts.getSubCategories().length == 0) {
                if (mainCat.has("items")) {
                    found = mainCat.getJSONObject("items").has(parts.getItemName());
                }
            } else {
                found = helper.searchInSubcategories(mainCat, parts.getSubCategories(), 0, parts.getItemName());
            }

            if (found) break;
        }
        return found;
    }



    public boolean findJsonBrand(CategoryPath parts, String brandName){
        JSONObject json = jsonReader.getJson();
        boolean found = false;
        for (SectionNames sectionEnum : SectionNames.values()) {
            String section = sectionEnum.getPath();

            if (!json.getJSONObject(section).has(parts.getMainCategory())) {
                continue;
            }
            JSONObject mainCat = json.getJSONObject(section).getJSONObject(parts.getMainCategory());

            if (parts.getSubCategories().length == 0) {
                found = helper.findBrandInItem(mainCat, parts.getItemName(), brandName);
            } else {
                found = helper.searchBrandInSubcategories(mainCat, parts.getSubCategories(), 0, parts.getItemName(), brandName);
            }
            if (found) break;
        }
        return found;
    }







}
