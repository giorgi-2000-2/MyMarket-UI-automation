package mobile.category.navigation;
import com.google.inject.Inject;
import core.config.IScrollConfig;
import core.reporter.ReportMessages;
import mobile.category.model.Item;
import mobile.category.screen.ScreenReader;
import mobile.category.scroll.ListScroller;

public class CategoryItemFinder {
    private final ScreenReader screenReader;
    private final ListScroller listScroller;
    private final IScrollConfig scrollConfig;

    @Inject
    public CategoryItemFinder(ScreenReader screenReader, ListScroller listScroller, IScrollConfig scrollConfig) {
        this.screenReader = screenReader;
        this.listScroller = listScroller;
        this.scrollConfig = scrollConfig;
    }

    public Item findByName(String name) {
        for (Item it : screenReader.read().items) {
            if (it.name.equals(name)) return it;
        }
        return null;
    }


    public Item findWithScroll(String name) {
        Item target = findByName(name);

        for (int i = 0; target == null && i < scrollConfig.maxScrolls(); i++) {
            if (!listScroller.scrollForward()) break;
            target = findByName(name);
        }

        for (int i = 0; target == null && i < scrollConfig.maxScrolls(); i++) {
            if (!listScroller.scrollBackward()) break;
            target = findByName(name);
        }

        if (target == null) {
            throw new IllegalStateException(ReportMessages.CATEGORY_NOT_FOUND.format(name));
        }
        return target;
    }
}
