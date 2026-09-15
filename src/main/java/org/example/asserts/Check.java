package org.example.asserts;

import org.example.utils.reporter.NodeKey;

import java.util.Objects;

public final class Check {
    private final SoftVerifier verifier;
    private final NodeKey node;
    private final String description;
    private String expected;

    Check(SoftVerifier verifier, NodeKey node, String description) {
        this.verifier = verifier;
        this.node = node;
        this.description = description;
    }

    public Check expected(String value) {
        this.expected = value;
        return this;
    }

    public void actual(String value) {
        boolean isPassed = Objects.equals(expected, value);
        VerificationResult result = new VerificationResult(description, expected, value, isPassed);

        verifier.record(node, result);
    }
}