package web.manager;
import com.google.inject.Inject;
import core.utils.state.IState;
import core.steps.LeafHandler;

import java.util.ArrayList;
import java.util.List;


public class CategoryWalker implements ICategoryWalker {
    private final ICategoryNavigator navigator;
    @Inject
    public CategoryWalker(ICategoryNavigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void walk(LeafHandler leaf, IState state) {
        navigator.openDropdown();
      walk(List.of(), leaf, state);
    }

    private int walk(List<String> path, LeafHandler leaf, IState state) {
        int size = navigator.optionsCount();
        int leafCount = 0;

        for (int i = navigator.startIndex(); i < size; i++) {
            String name = navigator.optionsCategory(i);
            List<String> childPath = new ArrayList<>(path);
            childPath.add(name);
            String key = String.join(" -> ", childPath);
            if (state.isDone(key)) continue;
            navigator.clickOption(i);
            if (navigator.isLeaf()) {
                leaf.handle(childPath);
                leafCount++;
                navigator.openDropdown();
            } else {
                leafCount += walk(childPath, leaf, state);
            }
            state.markDone(key);
        }
        navigator.goBack();
        return leafCount;
    }
}