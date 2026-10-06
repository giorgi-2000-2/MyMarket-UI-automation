package mobile.datamanager;

import com.google.inject.Inject;
import core.annotations.TestScoped;
import core.catalog.ICatalog;
import core.reporter.stringutils.CategoryPath;
import mobile.category.parsing.StringParser;

import java.util.List;

@TestScoped
public class MobileCategoryDataService {
    private final ICatalog catalog;
    private final StringParser stringParser;

    @Inject
    public MobileCategoryDataService(ICatalog catalog, StringParser stringParser) {
        this.catalog = catalog;
        this.stringParser = stringParser;
    }

    public boolean exists(List<String> path, String child) {
        CategoryPath categoryPath = stringParser.parseString(path, child);
        return catalog.categoryExists(categoryPath);
    }
}
