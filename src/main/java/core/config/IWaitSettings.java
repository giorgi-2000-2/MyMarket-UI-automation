package core.config;

public interface IWaitSettings {
    long defaultTimeoutMs();
    long transitionTimeoutMs();
    long openTimeoutMs();
    long pollMs();
}