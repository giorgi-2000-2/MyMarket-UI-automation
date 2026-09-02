package org.example.utils.reporter;

import org.apache.commons.io.FileUtils;
import org.example.utils.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test suite Started : " + context.getName());
        TestReporterContext.set(new ExtentTestReporter());
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("Test Started : " + testName);
        TestReporterContext.get().createTest(testName);
        TestReporterContext.get().info("Test Started : " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Success : " + result.getName());
        TestReporterContext.get().log(ReportStatus.PASS, "Test Passed");
        TestReporterContext.get().unload();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed : " + result.getName());
        WebDriver driver = DriverManager.getDriver();

        boolean skipScreenshot = false;
        for (String group : result.getMethod().getGroups()) {
            if ("no-screenshot".equals(group)) {
                skipScreenshot = true;
                break;
            }
        }

        if (!skipScreenshot && driver != null) {
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                String base64Code = ts.getScreenshotAs(OutputType.BASE64);

                File screenshot = ts.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshot, new File("screenshots/" + result.getName() + ".png"));

                TestReporterContext.get().logWithScreenshot(
                        ReportStatus.FAIL,
                        "Test failed: " + result.getThrowable().getMessage(),
                        base64Code
                );

            } catch (Exception e) {
                System.out.println("სქრინშოთის გადაღება ვერ მოხერხდა: " + e.getMessage());
                TestReporterContext.get().log(ReportStatus.FAIL, "Test failed without screenshot: " + result.getThrowable().getMessage());
            }
        } else {
            TestReporterContext.get().log(ReportStatus.FAIL, "Test failed: " + result.getThrowable().getMessage());
        }

        TestReporterContext.get().unload();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped : " + result.getName());
        TestReporterContext.get().log(ReportStatus.SKIP, "Test Skipped");
        TestReporterContext.get().unload();
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test suite finished : " + context.getName());
        TestReporterContext.get().flush();

        try {
            File reportFile = new File(System.getProperty("user.dir") + "/report/extentReport.html");
            if (reportFile.exists()) {
                java.awt.Desktop.getDesktop().browse(reportFile.toURI());
            } else {
                System.out.println("რეპორტის ფაილი მითითებულ მისამართზე ვერ მოიძებნა!");
            }
        } catch (Exception e) {
            System.out.println("ბრაუზერის ავტომატურად გახსნა ვერ მოხერხდა: " + e.getMessage());
        }
    }
}