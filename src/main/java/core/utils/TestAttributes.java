package core.utils;

public enum TestAttributes {
    DRIVER,
    SOFT_ASSERT,
    REPORTER;

    public String key() {
        return name();
    }
}