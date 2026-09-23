package core.modules;
import core.reporter.IReportTree;
import core.reporter.ReportStatus;
import core.reporter.texts.ErrorMessages;
import core.utils.TestAttributes;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

public class SoftAssertListener implements IInvokedMethodListener {

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        if (!method.isTestMethod()) {
            return;
        }
        if (!(result.getAttribute(TestAttributes.SOFT_ASSERT.key())
                instanceof SoftAssert soft)) {
            return;
        }
        try {
            soft.assertAll();
        }
        catch (AssertionError softError) {
            if (result.getStatus() == ITestResult.SUCCESS) {
                result.setStatus(ITestResult.FAILURE);
                result.setThrowable(softError);
                return;
            }

            Throwable primary = result.getThrowable();
            if (primary != null && primary != softError) {
                primary.addSuppressed(softError);
            }
            else if (primary == null) {
                result.setThrowable(softError);
            }

            ((IReportTree) result.getAttribute(TestAttributes.REPORTER.key())).log(
                    ReportStatus.WARNING, ErrorMessages.EXTRA_SOFT_ERRORS.format(softError.getMessage()));
        }
    }
}