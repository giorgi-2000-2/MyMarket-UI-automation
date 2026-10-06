package core.jsonmanager;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import core.catalog.ICatalog;
import core.exception.CatalogException;
import core.reporter.stringutils.CategoryPath;
import org.json.JSONException;
import org.json.JSONObject;

@Singleton
public class JsonFinder implements ICatalog {
    private final SubcategorySearch helper;
    private final JsonReaders jsonReader;

    @Inject
    public JsonFinder(SubcategorySearch helper, JsonReaders jsonReader) {
        this.helper = helper;
        this.jsonReader = jsonReader;
    }

    @Override
    public boolean categoryExists(CategoryPath parts) {
        try {
            JSONObject json = jsonReader.getJson();
            for (SectionNames sectionEnum : SectionNames.values()) {
                JSONObject section = json.getJSONObject(sectionEnum.getPath());
                if (!section.has(parts.getMainCategory())) {
                    continue;
                }
                JSONObject mainCat = section.getJSONObject(parts.getMainCategory());

                boolean found;
                if (parts.getSubCategories().length == 0) {
                    found = mainCat.has("items")
                            && mainCat.getJSONObject("items").has(parts.getItemName());
                } else {
                    found = helper.searchInSubcategories(mainCat, parts.getSubCategories(), 0, parts.getItemName());
                }
                if (found) {
                    return true;
                }
            }
            return false;
        } catch (JSONException e) {
            throw new CatalogException("კატალოგის წაკითხვა ვერ მოხერხდა: " + parts.getMainCategory() + " / " + parts.getItemName(), e);
        }
    }

    @Override
    public boolean brandExists(CategoryPath parts, String brandName) {
        try {
            JSONObject json = jsonReader.getJson();
            for (SectionNames sectionEnum : SectionNames.values()) {
                JSONObject section = json.getJSONObject(sectionEnum.getPath());
                if (!section.has(parts.getMainCategory())) {
                    continue;
                }
                JSONObject mainCat = section.getJSONObject(parts.getMainCategory());

                boolean found;
                if (parts.getSubCategories().length == 0) {
                    found = helper.findBrandInItem(mainCat, parts.getItemName(), brandName);
                } else {
                    found = helper.searchBrandInSubcategories(mainCat, parts.getSubCategories(), 0, parts.getItemName(), brandName);
                }
                if (found) {
                    return true;
                }
            }
            return false;
        } catch (JSONException e) {
            throw new CatalogException("ბრენდის წაკითხვა ვერ მოხერხდა: " + parts.getItemName() + " / " + brandName, e);
        }
    }
}
