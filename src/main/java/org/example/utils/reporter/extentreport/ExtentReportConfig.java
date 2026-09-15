package org.example.utils.reporter.extentreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public final class ExtentReportConfig {
    private static volatile ExtentReports extent;

    private ExtentReportConfig() {}

    public static ExtentReports getInstance() {
        if (extent == null) {
            synchronized (ExtentReportConfig.class) {
                if (extent == null) {
                    String reportPath = System.getProperty("user.dir") + "/report/extentReport.html";
                    ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
                    spark.config().setReportName("Automation Tester: Giorgi Mikeladze - Reports");
                    spark.config().setDocumentTitle("Test Execution Report");

                    extent = new ExtentReports();
                    extent.attachReporter(spark);
                    extent.setSystemInfo("Environment", "QA");
                    extent.setSystemInfo("Automation Tester", "Giorgi Mikeladze");
                }
            }
        }
        return extent;
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}