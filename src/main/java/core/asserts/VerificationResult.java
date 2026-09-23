package core.asserts;

import core.reporter.texts.AssertMessages;

public final class VerificationResult {
    private final String description;
    private final String expected;
    private final String actual;
    private final boolean passed;

    VerificationResult(String description, String expected, String actual, boolean passed) {
        this.description = description;
        this.expected = expected;
        this.actual = actual;
        this.passed = passed;
    }

    public boolean isPassed() {
        return passed;
    }

    public String message() {
        if (passed) {
            return String.format(AssertMessages.PASS_MATCH.format(description,actual));
        } else {
            return String.format(AssertMessages.FAIL_MISMATCH.format(description,expected,actual));
        }
    }
}