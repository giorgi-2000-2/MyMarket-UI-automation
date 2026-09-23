package core.reporter.texts;

public enum AssertMessages {
    PASS_MATCH(" %s: '%s'"),
    FAIL_MISMATCH(" %s — მოსალოდნელი: '%s', მიმდინარე: '%s'"),
    CATEGORY_FOUND("%s მოიძებნა"),
    CATEGORY_CONTAINS_BRAND("%s შეიცავს %s"),
    DROPDOWN_NAME_MATCHES_PAGE("Dropdown-ის სახელი ემთხვევა გვერდის სახელს"),
    DIALOG_CLOSED("დიალოგის ფანჯარა წარმატებით დაიხურა."),
    AD_DIALOG_CLOSED("რეკლამის დიალოგის ფანჯარა (dialog) წარმატებით დაიხურა."),
    DIALOG_NOT_SHOWN("დიალოგი არ გამოჩნდა (%s)"),
    FINAL_CATEGORY_NO_BACK("%s — საბოლოო კატეგორიაა, დასაბრუნებელი არაფერია"),
    BACK_RESTORE_CHECK("%s — უკან დაბრუნება"),
    BRAND_DROPDOWN_MISSING(" — ბრენდის dropdown არ არის, გამოტოვება"),
    TEST_STARTED("ტესტი დაიწყო: %s"),
    TEST_PASSED("Test Passed"),
    TEST_FAILED("Test Failed : %s"),
    TEST_SKIPPED("Test Skipped"),
    TEST_SUCCESS("Test Success : %s"),
    TEST_SKIPPED_LOG("Test Skipped : %s"),
    SUITE_STARTED("Test suite Started : %s"),
    SUITE_FINISHED("Test suite finished : %s");

    private final String template;

    AssertMessages(String template) {
        this.template = template;
    }

    public String get() {
        return template;
    }

    public String format(Object... args) {
        return String.format(template, args);
    }
}