package web.manager;
import com.google.inject.Inject;

public class CategoryWalker implements ICategoryWalker {

    private final ICategoryNavigator navigator;
    @Inject
    public CategoryWalker(ICategoryNavigator navigator) {
        this.navigator = navigator;
    }

    public void walk(IAction leafAction) {
        navigator.openDropdown();
        walkRecursive(leafAction);
    }

    private void walkRecursive(IAction leafAction) {
        int start = navigator.startIndex();
        int size = navigator.optionsCount();

        for (int i = start; i <size; i++) {
            navigator.clickOption(i);

            if (navigator.isLeaf()) {
                leafAction.execute();
                navigator.openDropdown();
            } else {
                walkRecursive(leafAction);
            }
        }
        navigator.goBack();
    }
}