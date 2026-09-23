package core.reporter;
import core.reporter.texts.AssertMessages;
import core.reporter.texts.ErrorMessages;
import core.utils.TestAttributes;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener {


    @Override
    public void onStart(ITestContext context) {
        System.out.println(AssertMessages.SUITE_STARTED.format(context.getName()));
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println(AssertMessages.TEST_STARTED.format(testName));
        ((IReportTree) result.getAttribute(TestAttributes.REPORTER.key())).info(AssertMessages.TEST_STARTED.format(testName));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(AssertMessages.TEST_SUCCESS.format(result.getName()));
        ((IReportTree) result.getAttribute(TestAttributes.REPORTER.key())).log(ReportStatus.PASS, AssertMessages.TEST_PASSED.get());    }


    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println(AssertMessages.TEST_FAILED.format(result.getName()));

        boolean skipScreenshot = false;
        for (String group : result.getMethod().getGroups()) {
            if ("no-screenshot".equals(group)) {
                skipScreenshot = true;
                break;
            }
        }

        WebDriver driver = (WebDriver) result.getAttribute(TestAttributes.DRIVER.key());
        String errorMessage = result.getThrowable() != null
                ? result.getThrowable().getMessage()
                : "";

        if (!skipScreenshot && driver != null) {
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                String base64Code = ts.getScreenshotAs(OutputType.BASE64);

                File screenshot = ts.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshot, new File("screenshots/" + result.getName() + ".png"));

                ((IReportTree) result.getAttribute(TestAttributes.REPORTER.key())).logWithScreenshot(
                        ReportStatus.FAIL,
                        ErrorMessages.TEST_FAILED_MSG.format(errorMessage),
                        base64Code
                );

            } catch (Exception e) {
                System.out.println(ErrorMessages.SCREENSHOT_FAILED.format(e.getMessage()));
                ((IReportTree) result.getAttribute(TestAttributes.REPORTER.key())).log(
                        ReportStatus.FAIL,
                        ErrorMessages.SCREENSHOT_FAILED.format(errorMessage)
                );
            }
        } else {
            ((IReportTree) result.getAttribute(TestAttributes.REPORTER.key())).log(
                    ReportStatus.FAIL,
                    ErrorMessages.TEST_FAILED_MSG.format(errorMessage)
            );
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println(AssertMessages.TEST_SKIPPED_LOG.format(result.getName()));
        ((IReportTree) result.getAttribute(TestAttributes.REPORTER.key())).log(ReportStatus.SKIP, AssertMessages.TEST_SKIPPED.get());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println(AssertMessages.SUITE_FINISHED.format(context.getName()));

        try {
            File reportFile = new File(System.getProperty("user.dir") + "/report/extentReport.html");
            if (reportFile.exists()) {
                java.awt.Desktop.getDesktop().browse(reportFile.toURI());
            } else {
                System.out.println(ErrorMessages.REPORT_FILE_NOT_FOUND.get());
            }
        } catch (IOException | UnsupportedOperationException e) {
            System.out.println(ErrorMessages.BROWSER_OPEN_FAILED.format(e.getMessage()));
        }
    }

}