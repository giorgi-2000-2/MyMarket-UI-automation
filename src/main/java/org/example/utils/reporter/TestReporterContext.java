package org.example.utils.reporter;

public final class TestReporterContext {

    private static final ThreadLocal<IReportTree> WRITER = new ThreadLocal<>();
    private static final ThreadLocal<IReportLifecycle> LIFECYCLE = new ThreadLocal<>();


    private TestReporterContext() {
    }

    public static <R extends IReportTree & IReportLifecycle> void set(R reporter) {
        WRITER.set(reporter);
        LIFECYCLE.set(reporter);
    }

    public static IReportTree report() {
        IReportTree reporter = WRITER.get();
        if (reporter == null) {
            throw new IllegalStateException(
                    "Reporter არ არის ინიციალიზებული");
        }
        return reporter;
    }

    public static IReportLifecycle lifecycle() {
        IReportLifecycle reporter = LIFECYCLE.get();
        if (reporter == null) {
            throw new IllegalStateException(
                    "Reporter არ არის ინიციალიზებული");
        }
        return reporter;
    }


    public static void remove() {
        WRITER.remove();
        LIFECYCLE.remove();
    }
}