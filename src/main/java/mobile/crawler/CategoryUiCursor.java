package mobile.crawler;

import com.google.inject.Inject;
import mobile.category.navigation.CategoryNavigator;

import java.util.ArrayList;
import java.util.List;

public class CategoryUiCursor {
    private final CategoryNavigator navigator;
    private List<String> uiPath = null;

    @Inject
    public CategoryUiCursor(CategoryNavigator navigator) {
        this.navigator = navigator;
    }

    public void reset() {
        uiPath = null;
    }

    public void markAt(List<String> path) {
        uiPath = path;
    }

    public boolean clickChild(List<String> path, String child) {
        RuntimeException last = null;
        for (int attempt = 1; attempt <= 3; attempt++) {

            ensureAt(path);
            return navigator.clickAndIsLeaf(child);

        }
        throw last;
    }

    public void ensureAt(List<String> path) {
        if (path.equals(uiPath)) return;

        RuntimeException last = null;
        for (int attempt = 1; attempt <= 3; attempt++) {

            navigator.openAt(path);
            uiPath = new ArrayList<>(path);
            return;

        }
        throw last;
    }
}
