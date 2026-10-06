package mobile.crawler;

import com.google.inject.Inject;
import core.reporter.stringutils.StringSplitter;
import core.utils.state.IState;
import mobile.crawler.handler.CategoryExistenceReporter;
import core.steps.LeafHandler;
import mobile.category.navigation.ICategoryNamesCollector;
import core.annotations.TestScoped;

import java.util.ArrayList;
import java.util.List;


@TestScoped
public class CategoryCrawler {
    private final ICategoryNamesCollector namesCollector;
    private final CategoryUiCursor cursor;
    private final CategoryExistenceReporter existenceReporter;
    private final StringSplitter stringSplitter;

    @Inject
    public CategoryCrawler(ICategoryNamesCollector namesCollector, CategoryUiCursor cursor,
                           CategoryExistenceReporter existenceReporter, StringSplitter stringSplitter) {
        this.namesCollector = namesCollector;
        this.cursor = cursor;
        this.existenceReporter = existenceReporter;
        this.stringSplitter = stringSplitter;
    }

    public void crawl(LeafHandler leafHandler, IState state) {
        cursor.reset();
        dfs(new ArrayList<>(), leafHandler,state);

    }

    private void dfs(List<String> path, LeafHandler leafHandler, IState state) {
        cursor.ensureAt(path);

        List<String> children = namesCollector.collectAllNames();

        for (String child : children) {
            existenceReporter.report(path, child);
            List<String> childPath = new ArrayList<>(path);
            childPath.add(child);
            if (state.isDone(stringSplitter.key(childPath))) continue;
            if (cursor.clickChild(path, child)) {
                List<String> fullPath = new ArrayList<>(path);
                fullPath.add(child);
                leafHandler.handle(fullPath);
                cursor.reset();
                state.markDone(stringSplitter.key(childPath));
            } else {
                cursor.markAt(childPath);
                dfs(childPath, leafHandler,state);
            }
            state.markDone(stringSplitter.key(childPath));
        }
    }
}
