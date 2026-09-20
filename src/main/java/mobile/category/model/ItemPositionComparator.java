package mobile.category.model;

import com.google.inject.Singleton;

import java.util.Comparator;
@Singleton
public final class ItemPositionComparator implements Comparator<Item> {

    @Override
    public int compare(Item a, Item b) {
        if (a.top != b.top) {
            return Integer.compare(a.top, b.top);
        } else {
            return Integer.compare(a.left, b.left);
        }
    }
}
