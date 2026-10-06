package uicommon.utils;

import core.reporter.IReportTree;
import core.reporter.ReportStatus;
import core.reporter.texts.AssertMessages;
import core.reporter.texts.ErrorMessages;
import core.utils.TestAttributes;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class TestListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        IReportTree report = reporter(result);
        if (report != null) report.log(ReportStatus.PASS, AssertMessages.TEST_PASSED.get());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        IReportTree report = reporter(result);
        if (report != null) report.log(ReportStatus.SKIP, AssertMessages.TEST_SKIPPED.get());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        IReportTree report = reporter(result);
        if (report == null) return;

        String msg = ErrorMessages.TEST_FAILED_MSG.format(
                result.getThrowable() == null ? "" : result.getThrowable().getMessage());

        boolean skip = Arrays.asList(result.getMethod().getGroups()).contains("no-screenshot");
        Object driver = result.getAttribute(TestAttributes.DRIVER.key());

        if (!skip && driver instanceof TakesScreenshot ts) {
            try {
                byte[] png = ts.getScreenshotAs(OutputType.BYTES);
                save(result, png);
                report.logWithScreenshot(ReportStatus.FAIL, msg,
                        java.util.Base64.getEncoder().encodeToString(png));
                return;
            } catch (RuntimeException e) {
                msg = msg + " | " + ErrorMessages.SCREENSHOT_FAILED.format(e.getMessage());
            }
        }
        report.log(ReportStatus.FAIL, msg);
    }

    private void save(ITestResult result, byte[] png) {
        String params = Arrays.toString(result.getParameters()).replaceAll("[^\\p{L}\\p{N}]+", "_");
        Path file = Paths.get("screenshots", result.getName() + params + System.currentTimeMillis() + ".png");
        try {
            Files.createDirectories(file.getParent());
            Files.write(file, png);
        } catch (IOException ignored) {
        }
    }

    private IReportTree reporter(ITestResult result) {
        Object value = result.getAttribute(TestAttributes.REPORTER.key());
        if (value instanceof IReportTree report) {
            return report;
        }
        return null;
    }
}