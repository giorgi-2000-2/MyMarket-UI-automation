package org.example.jsonmanager;
import com.google.inject.Inject;
import org.example.utils.reporter.stringutils.CategoryPath;
import org.example.utils.reporter.stringutils.StringSplitter;

public class CategoryDataService {
    private final StringSplitter stringSplitter;
    private final JsonFinder jsonFinder;
    @Inject
    public CategoryDataService(StringSplitter stringSplitter, JsonFinder jsonFinder) {
        this.stringSplitter = stringSplitter;
        this.jsonFinder = jsonFinder;
    }

    public boolean exists(String fullCategoryName) {
        CategoryPath path = stringSplitter.parseString(fullCategoryName);
        return jsonFinder.findJsonData(path);
    }

    public boolean brandExists(String fullCategoryName, String brandName) {
        CategoryPath path = stringSplitter.parseString(fullCategoryName);
        return jsonFinder.findJsonBrand(path, brandName);
    }

    public CategoryPath parse(String fullCategoryName) {
        return stringSplitter.parseString(fullCategoryName);
    }
}