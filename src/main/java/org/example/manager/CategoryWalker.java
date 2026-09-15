package org.example.manager;


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

    private void walkRecursive(IAction action) {
        int start = navigator.startIndex();
        int size = navigator.optionsCount();

        for (int i = start; i <size; i++) {
            navigator.clickOption(i);

            if (navigator.isLeaf()) {
                action.execute();
                navigator.openDropdown();
            } else {
                walkRecursive(action);
            }
        }
        navigator.goBack();
    }
}