package core.reporter.extentreport;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import core.reporter.NodeKey;
import core.reporter.ReportMessages;
import core.reporter.ReportStatus;

public class ExtentLogger {
    private final ReportNodeManager nodeManager;

    public ExtentLogger(ReportNodeManager nodeManager) {
        this.nodeManager = nodeManager;
    }

    public void log(ReportStatus status, String message) {
        ExtentTest test = nodeManager.getCurrentTest();
        if (test != null) {
            test.log(toExtentStatus(status), message);
        } else {
            System.out.println(status + "  " + message);
        }
    }

    public void logToNode(NodeKey key, ReportStatus status, String message) {
        ExtentTest node = nodeManager.getNode(key);
        if (node != null) {
            node.log(toExtentStatus(status), message);
        } else {
            log(status, ReportMessages.NODE_NOT_FOUND.format(key,message));
        }
    }

    public void logWithScreenshot(ReportStatus status, String message, String base64Image) {
        ExtentTest test = nodeManager.getCurrentTest();
        if (test != null && base64Image != null && !base64Image.isEmpty()) {
            test.log(toExtentStatus(status), message,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
        } else if (test != null) {
            test.log(toExtentStatus(status), message);
        }
    }

    public void info(String message) {
        log(ReportStatus.INFO, message);
    }

    private Status toExtentStatus(ReportStatus status) {
        return switch (status) {
            case PASS    -> Status.PASS;
            case FAIL    -> Status.FAIL;
            case SKIP    -> Status.SKIP;
            case INFO    -> Status.INFO;
            case WARNING -> Status.WARNING;
        };
    }
}