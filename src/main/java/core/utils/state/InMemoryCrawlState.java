package core.utils.state;

import core.annotations.TestScoped;

import java.util.LinkedHashSet;
import java.util.Set;
@TestScoped
public class InMemoryCrawlState implements IState {

    protected final Set<String> done = new LinkedHashSet<>();

    @Override
    public boolean isDone(String key) {
        return done.contains(key);
    }

    @Override
    public void markDone(String key) {
        done.add(key);
    }

    @Override
    public int size() {
        return done.size();
    }
}
