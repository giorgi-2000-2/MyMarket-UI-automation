package mobile.category.navigation;

import com.google.inject.Inject;
import core.config.IScrollConfig;
import mobile.category.screen.ScreenReader;
import mobile.category.scroll.ListScroller;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class ScrollingCategoryNamesCollector implements CategoryNamesCollector {
    private final ScreenReader screenReader;
    private final ListScroller listScroller;
    private final IScrollConfig scrollConfig;

    @Inject
    public ScrollingCategoryNamesCollector(ScreenReader screenReader, ListScroller listScroller, IScrollConfig scrollConfig) {
        this.screenReader = screenReader;
        this.listScroller = listScroller;
        this.scrollConfig = scrollConfig;
    }

    @Override
    public List<String> collectAllNames() {
        Set<String> seen = new LinkedHashSet<>();
        int stagnant = 0;

        for (int i = 0; i < scrollConfig.maxScrolls() && stagnant < 2; i++) {
            int before = seen.size();
            seen.addAll(screenReader.read().names());
            if (seen.size() == before) stagnant++;
            else stagnant = 0;

            if (!listScroller.scrollForward()) break;
        }
        return new ArrayList<>(seen);
    }
}
