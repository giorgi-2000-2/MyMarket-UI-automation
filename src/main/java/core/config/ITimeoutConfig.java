package core.config;

public interface ITimeoutConfig {
    long transitionTimeoutMs();
    long openTimeoutMs();
    long pollMs();
}
