package core.reporter;

public interface IReportLifecycle {
    void createTest(String testName);
    void unload();
    void flush();
}