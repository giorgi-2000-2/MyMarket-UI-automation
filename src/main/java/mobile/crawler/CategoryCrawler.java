package mobile.crawler;

import com.google.inject.Inject;
import mobile.crawler.handler.CategoryExistenceReporter;
import mobile.crawler.handler.LeafCategoryHandler;
import mobile.category.navigation.CategoryNamesCollector;
import core.annotations.TestScoped;

import java.util.ArrayList;
import java.util.List;


@TestScoped
public class CategoryCrawler {
    private final CategoryNamesCollector namesCollector;
    private final CategoryUiCursor cursor;
    private final CategoryExistenceReporter existenceReporter;

    @Inject
    public CategoryCrawler(CategoryNamesCollector namesCollector, CategoryUiCursor cursor,
                           CategoryExistenceReporter existenceReporter) {
        this.namesCollector = namesCollector;
        this.cursor = cursor;
        this.existenceReporter = existenceReporter;
    }

    public void crawl(LeafCategoryHandler leafHandler) {
        cursor.reset();
        dfs(new ArrayList<>(), leafHandler);

    }

    private void dfs(List<String> path, LeafCategoryHandler leafHandler) {
        cursor.ensureAt(path);

        List<String> children = namesCollector.collectAllNames();

        for (String child : children) {
            existenceReporter.report(path, child);
            List<String> childPath = new ArrayList<>(path);
            childPath.add(child);
            if (cursor.clickChild(path, child)) {
                List<String> fullPath = new ArrayList<>(path);
                fullPath.add(child);
                leafHandler.handle(fullPath);
                cursor.reset();
            } else {
                cursor.markAt(childPath);
                dfs(childPath, leafHandler);

            }
        }
    }
}
