package core.reporter;

public final class TestReporterContext {

    private static final ThreadLocal<IReportTree> WRITER = new ThreadLocal<>();
    private static final ThreadLocal<IReportLifecycle> LIFECYCLE = new ThreadLocal<>();
    private static final ThreadLocal<IReporter> REPORTER = new ThreadLocal<>();

    private TestReporterContext() {
    }

    public static <R extends IReportTree & IReportLifecycle & IReporter> void set(R reporter) {
        WRITER.set(reporter);
        LIFECYCLE.set(reporter);
        REPORTER.set(reporter);
    }

    public static IReportTree report() {
        IReportTree reporter = WRITER.get();
        if (reporter == null) {
            throw new IllegalStateException(ErrorMessages.REPORTER_NOT_INIT.get());
        }
        return reporter;
    }

    public static IReporter reporter() {
        IReporter reporter = REPORTER.get();
        if (reporter == null) {
            throw new IllegalStateException(ErrorMessages.REPORTER_NOT_INIT.get());
        }
        return reporter;
    }
    public static IReportLifecycle lifecycle() {
        IReportLifecycle reporter = LIFECYCLE.get();
        if (reporter == null) {
            throw new IllegalStateException(ErrorMessages.REPORTER_NOT_INIT.get());
        }
        return reporter;
    }


    public static void remove() {
        WRITER.remove();
        LIFECYCLE.remove();
        REPORTER.remove();
    }


}