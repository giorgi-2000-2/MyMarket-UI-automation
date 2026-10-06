package core.jsonmanager;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import core.catalog.ICatalog;
import core.reporter.stringutils.CategoryPath;
import core.reporter.stringutils.StringSplitter;

@Singleton
public class CategoryDataService {
    private final StringSplitter stringSplitter;
    private final ICatalog catalog;

    @Inject
    public CategoryDataService(StringSplitter stringSplitter, ICatalog catalog) {
        this.stringSplitter = stringSplitter;
        this.catalog = catalog;
    }

    public boolean exists(String fullCategoryName) {
        CategoryPath path = stringSplitter.parseString(fullCategoryName);
        return catalog.categoryExists(path);
    }

    public boolean brandExists(String fullCategoryName, String brandName) {
        CategoryPath path = stringSplitter.parseString(fullCategoryName);
        return catalog.brandExists(path, brandName);
    }
}
