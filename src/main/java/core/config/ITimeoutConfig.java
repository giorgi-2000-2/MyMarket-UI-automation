package core.config;

public interface ISettingsWait {
    long transitionTimeoutMs();
    long openTimeoutMs();
    long pollMs();
    public long defaultTimeoutMs();
}
