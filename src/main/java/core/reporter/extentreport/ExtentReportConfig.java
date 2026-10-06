package core.reporter.extentreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public final class ExtentReportConfig {
    private  volatile ExtentReports extent;
@Inject
    public ExtentReportConfig() {}

    public ExtentReports getInstance() {
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

    public void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}