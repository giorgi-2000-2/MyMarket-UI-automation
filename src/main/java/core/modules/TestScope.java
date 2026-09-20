package core.modules;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Scope;
import core.reporter.ErrorMessages;

import java.util.HashMap;
import java.util.Map;

public class TestScope implements Scope {

    private final ThreadLocal<Map<Key<?>, Object>> scopedObjects = new ThreadLocal<>();


    public void enter() {
        if (scopedObjects.get() != null) {
            throw new IllegalStateException(
                    ErrorMessages.TEST_SCOPE_ALREADY_ENTERED.get());
        }
        scopedObjects.set(new HashMap<>());
    }


    public void exit() {
        scopedObjects.remove();
    }

    @Override
    public <T> Provider<T> scope(Key<T> key, Provider<T> unscoped) {
        return new Provider<T>() {

            @Override
            public T get() {
                Map<Key<?>, Object> objects = scopedObjects.get();

                if (objects == null) {
                    throw new IllegalStateException(ErrorMessages.TEST_SCOPE_OUTSIDE.format(key));
                }


                T existing = (T) objects.get(key);
                if (existing == null && !objects.containsKey(key)) {
                    existing = unscoped.get();
                    objects.put(key, existing);
                }
                return existing;
            }
        };
    }

    @Override
    public String toString() {
        return "TestScoped";
    }
}