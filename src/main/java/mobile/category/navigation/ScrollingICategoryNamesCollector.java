package mobile.category.navigation;

import com.google.inject.Inject;
import core.config.IScrollConfig;
import mobile.category.screen.IScreenReader;
import mobile.category.scroll.IListScroller;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class ScrollingICategoryNamesCollector implements ICategoryNamesCollector {
    private final IScreenReader IScreenReader;
    private final IListScroller IListScroller;
    private final IScrollConfig scrollConfig;

    @Inject
    public ScrollingICategoryNamesCollector(IScreenReader IScreenReader, IListScroller IListScroller, IScrollConfig scrollConfig) {
        this.IScreenReader = IScreenReader;
        this.IListScroller = IListScroller;
        this.scrollConfig = scrollConfig;
    }

    @Override
    public List<String> collectAllNames() {
        Set<String> seen = new LinkedHashSet<>();
        int stagnant = 0;

        for (int i = 0; i < scrollConfig.maxScrolls() && stagnant < 2; i++) {
            int before = seen.size();
            seen.addAll(IScreenReader.read().names());
            if (seen.size() == before) stagnant++;
            else stagnant = 0;

            if (!IListScroller.scrollForward()) break;
        }
        return new ArrayList<>(seen);
    }
}
