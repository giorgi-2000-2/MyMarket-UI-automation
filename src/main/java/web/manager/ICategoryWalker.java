package web.manager;

import core.utils.state.IState;
import core.steps.LeafHandler;

public interface ICategoryWalker {
    void walk(LeafHandler leaf, IState state);
}