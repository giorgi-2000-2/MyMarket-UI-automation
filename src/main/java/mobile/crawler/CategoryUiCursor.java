package mobile.crawler;

import com.google.inject.Inject;
import core.utils.RetryPolicy;
import mobile.category.navigation.ICategoryNavigator;

import java.util.ArrayList;
import java.util.List;

public class CategoryUiCursor {
    private final ICategoryNavigator navigator;
    private final RetryPolicy retryPolicy;
    private List<String> uiPath = null;

    @Inject
    public CategoryUiCursor(ICategoryNavigator navigator, RetryPolicy retryPolicy) {
        this.navigator = navigator;
        this.retryPolicy = retryPolicy;
    }

    public void reset() {
        uiPath = null;
    }

    public void markAt(List<String> path) {
        uiPath = path;
    }

    public boolean clickChild(List<String> path, String child) {
        return retryPolicy.run(
                "clickChild",
                child,
                () -> {
                    ensureAt(path);
                    return navigator.clickAndIsLeaf(child);
                },
                () -> uiPath = null
        );
    }

    public void ensureAt(List<String> path) {
        if (path.equals(uiPath)) return;

        retryPolicy.run(
                "ensureAt",
                path.toString(),
                () -> {
                    navigator.openAt(path);
                    uiPath = new ArrayList<>(path);
                    return true;
                },
                () -> uiPath = null
        );
    }
}