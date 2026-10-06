package core.utils;

import com.google.inject.Inject;
import core.annotations.TestScoped;
import core.config.IRetryConfig;
import core.exception.PermanentUiException;
import core.reporter.RetryReporter;

import java.util.function.Predicate;
import java.util.function.Supplier;

@TestScoped
public class RetryPolicy {
    private final IRetryConfig config;
    private final RetryReporter retryReporter;

    @Inject
    public RetryPolicy(IRetryConfig config, RetryReporter retryReporter) {
        this.config = config;
        this.retryReporter = retryReporter;
    }

    public <T> T run(String action, String target, Supplier<T> body, Runnable beforeRetry) {
        return run(action, target, body, beforeRetry, e -> true);
    }

    public <T> T run(String action, String target, Supplier<T> body,
                     Runnable beforeRetry, Predicate<RuntimeException> retryOn) {
        RuntimeException last = null;
        for (int attempt = 1; attempt <= config.maxAttempts(); attempt++) {
            try {
                return body.get();
            } catch (PermanentUiException e) {
                throw e;
            } catch (RuntimeException e) {
                if (!retryOn.test(e)) throw e;
                last = e;
                retryReporter.logAttempt(action, target, attempt, e);
                beforeRetry.run();
            }
        }
        throw new PermanentUiException(action + " ვერ შესრულდა: " + target, last);
    }
}