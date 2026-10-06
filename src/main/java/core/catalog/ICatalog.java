package core.catalog;

import core.reporter.stringutils.CategoryPath;

public interface ICatalog {
    boolean categoryExists(CategoryPath path);
    boolean brandExists(CategoryPath path, String brandName);
}
