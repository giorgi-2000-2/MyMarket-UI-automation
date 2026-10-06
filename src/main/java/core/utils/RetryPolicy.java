package core.utils;

import com.google.inject.Inject;
import core.config.IRetryConfig;
import core.exception.PermanentUiException;

import java.util.function.Supplier;
public class RetryPolicy{
    private final IRetryConfig config;
    private final
@Inject
    public RetryPolicy(IRetryConfig config) {
        this.config = config;
    }


    public <T> T run(String action, String target, Supplier<T> body, Runnable beforeRetry) {
    RuntimeException last = null;
    for (int attempt = 1; attempt <= config.maxAttempts(); attempt++) {
        try {
            return body.get();
        } catch (PermanentUiException e) {
            throw e;
        } catch (RuntimeException e) {
            last = e;
            reporter.logAttempt(action, target, attempt, e);
            beforeRetry.run();
        }
    }
    throw new PermanentUiException(action + " ვერ შესრულდა: " + target, last);
}

}