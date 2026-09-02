package org.example.utils.reporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.util.EnumMap;
import java.util.Map;

public class ExtentTestReporter implements ITestReporter {

    private volatile ExtentReports extent;
    private final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private final ThreadLocal<Map<NodeKey, ExtentTest>> namedNodes =
            ThreadLocal.withInitial(() -> new EnumMap<>(NodeKey.class));

    private ExtentReports getExtentReports() {
        if (extent == null) {
            synchronized (ExtentTestReporter.class) {
                if (extent == null) {
                    String reportPath = System.getProperty("user.dir") + "/report/extentReport.html";
                    ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
                    sparkReporter.config().setReportName("Automation Tester: Giorgi Mikeladze - Reports");
                    sparkReporter.config().setDocumentTitle("Test Execution Report");

                    extent = new ExtentReports();
                    extent.attachReporter(sparkReporter);
                    extent.setSystemInfo("Environment", "QA");
                    extent.setSystemInfo("Automation Tester", "Giorgi Mikeladze");
                }
            }
        }
        return extent;
    }

    @Override
    public void createTest(String testName) {
        test.set(getExtentReports().createTest(testName));
        namedNodes.get().clear();
    }

    @Override
    public void createNamedNode(NodeKey key, String nodeName) {
        ExtentTest currentTest = test.get();
        if (currentTest != null) {
            ExtentTest newNode = currentTest.createNode(nodeName);
              namedNodes.get().put(key, newNode);
        }
    }
    @Override
    public void createChildNode(NodeKey parentKey, NodeKey childKey, String childNodeName) {
        ExtentTest parentNode = namedNodes.get().get(parentKey);

        if (parentNode != null) {
            ExtentTest childNode = parentNode.createNode(childNodeName);
            namedNodes.get().put(childKey, childNode);
        } else {
            System.out.println("მშობელი ნოუდი სახელით '" + parentKey + "' ვერ მოიძებნა. შვილი ნოუდი შეიქმნება მთავარ ტესტში.");
            createNamedNode(childKey, childNodeName);
        }
    }
    @Override
    public void logToNode(NodeKey key, ReportStatus status, String message) {
        ExtentTest targetNode = namedNodes.get().get(key);
        if (targetNode != null) {
               targetNode.log(toExtentStatus(status), message);
        } else {
            log(status, " Node '" + key + "' ვერ მოიძებნა: " + message);
        }
    }

    @Override
    public void log(ReportStatus status, String message) {
        ExtentTest currentTest = test.get();
        if (currentTest != null) {
            currentTest.log(toExtentStatus(status), message);
        } else {
            System.out.println(" " + status + "  " + message);
        }
    }

    @Override
    public void logWithScreenshot(ReportStatus status, String message, String base64Image) {
        ExtentTest currentTest = test.get();
        if (currentTest != null && base64Image != null && !base64Image.isEmpty()) {
            currentTest.log(toExtentStatus(status), message,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
        } else if (currentTest != null) {
            currentTest.log(toExtentStatus(status), message);
        }
    }

    @Override
    public void info(String message) {
        log(ReportStatus.INFO, message);
    }

    @Override
    public void unload() {
        namedNodes.remove();
        test.remove();
    }

    @Override
    public void flush() {
        if (extent != null) {
            extent.flush();
        }
    }

    private Status toExtentStatus(ReportStatus status) {
        return switch (status) {
            case PASS -> Status.PASS;
            case FAIL -> Status.FAIL;
            case SKIP -> Status.SKIP;
            case INFO -> Status.INFO;
            case WARNING -> Status.WARNING;
        };
    }
}