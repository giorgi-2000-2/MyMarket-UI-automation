package org.example.utils.reporter;

public interface IReportLifecycle {
    void createTest(String testName);
    void unload();
    void flush();
}