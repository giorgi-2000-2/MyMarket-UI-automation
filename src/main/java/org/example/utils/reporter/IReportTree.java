package org.example.utils.reporter;


public interface IReportTree {
    void log(ReportStatus status, String message);
    void info(String message);
    void logWithScreenshot(ReportStatus status, String message, String base64Image);

}