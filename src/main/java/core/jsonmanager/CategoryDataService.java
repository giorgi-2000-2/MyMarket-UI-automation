package core.jsonmanager;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import core.reporter.stringutils.CategoryPath;
import core.reporter.stringutils.StringSplitter;
@Singleton
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