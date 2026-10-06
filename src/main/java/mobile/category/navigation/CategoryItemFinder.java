package mobile.category.navigation;
import com.google.inject.Inject;
import core.config.IScrollConfig;
import core.exception.CategoryNotFoundException;
import core.reporter.texts.ErrorMessages;
import mobile.category.model.Item;
import mobile.category.screen.IScreenReader;
import mobile.category.scroll.IListScroller;

public class CategoryItemFinder {
    private final IScreenReader IScreenReader;
    private final IListScroller IListScroller;
    private final IScrollConfig scrollConfig;

    @Inject
    public CategoryItemFinder(IScreenReader IScreenReader, IListScroller IListScroller, IScrollConfig scrollConfig) {
        this.IScreenReader = IScreenReader;
        this.IListScroller = IListScroller;
        this.scrollConfig = scrollConfig;
    }

    public Item findByName(String name) {
        for (Item it : IScreenReader.read().items) {
            if (it.name.equals(name)) return it;
        }
        return null;
    }


    public Item findWithScroll(String name) {
        Item target = findByName(name);

        for (int i = 0; target == null && i < scrollConfig.maxScrolls(); i++) {
            if (!IListScroller.scrollForward()) break;
            target = findByName(name);
        }

        for (int i = 0; target == null && i < scrollConfig.maxScrolls(); i++) {
            if (!IListScroller.scrollBackward()) break;
            target = findByName(name);
        }

        if (target == null) {
            throw new CategoryNotFoundException(
                    name, ErrorMessages.CATEGORY_NOT_FOUND.format(name));
        }
        return target;
    }
}
