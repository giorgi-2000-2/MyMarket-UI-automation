package core.reporter.texts;

public enum ErrorMessages {
    TEST_SCOPE_ALREADY_ENTERED(" TestScope უკვე გახსნილია ამ თრედზე — წინა ტესტს exit() არ გამოუძახებია "),
    TEST_SCOPE_OUTSIDE(" TestScope-ის გარეთ მოთხოვნილია: %s"),
    REPORTER_NOT_INIT(" Reporter არ არის ინიციალიზებული "),
    EMPTY_NAME(" სახელი ცარიელია "),
    NAME_ISEMPTY("მომხმარებლის სახელი ცარიელია"),
    CONFIG_KEY_MISSING("კონფიგის გასაღები აკლია: %s"),
    CONFIG_KEY_NOT_NUMBER(" კონფიგის გასაღები არ არის რიცხვი: %s"),
    JSON_READ_FAILED("JSON ფაილის წაკითხვა ვერ მოხერხდა: %s"),
    CONFIG_FILE_NOT_FOUND("%s ვერ მოიძებნა classpath-ზე"),
    CONFIG_FILE_READ_FAILED("%s-ის წაკითხვა ვერ მოხერხდა"),
    BROWSER_CLOSE_FAILED("ბრაუზერის დახურვა ვერ მოხერხდა: %s"),
    BROWSER_OPEN_FAILED("ბრაუზერის ავტომატურად გახსნა ვერ მოხერხდა: %s"),
    SCREENSHOT_FAILED("სქრინშოთის გადაღება ვერ მოხერხდა: %s"),
    NODE_NOT_FOUND("Node '%s' ვერ მოიძებნა: %s"),
    PARENT_NODE_MISSING("მშობელი ნოუდი სახელით '%s' ვერ მოიძებნა. შვილი ნოუდი შეიქმნება მთავარ ტესტში."),
    BRAND_CHECK_ERROR("%s — ბრენდის შემოწმებისას შეცდომა: %s"),
    EXTRA_SOFT_ERRORS("დამატებითი soft-assert შეცდომები (ტესტი უკვე ჩავარდნილი იყო):\n%s"),
    REPORT_FILE_NOT_FOUND("რეპორტის ფაილი მითითებულ მისამართზე ვერ მოიძებნა!"),
    TEST_FAILED_MSG("Test failed: %s"),
    CATEGORY_NOT_FOUND("ვერ ვიპოვე კატეგორია: '%s' (ვერც ქვემოთ და ვერც ზემოთ სქროლვით)"),
    CATEGORY_PICKER_OPEN_FAILED("კატეგორიის პიკერი ვერ გაიხსნა"),
    BRAND_FIELD_NOT_FOUND_RETURNING(" ბრენდის ველი ვერ მოიძებნა, ვბრუნდებით კატეგორიებში..."),
    CHARACTERISTICS_NOT_FOUND_RETURNING(" მახასიათებლები ვერ მოიძებნა, ვბრუნდებით კატეგორიებში..."),
    BRAND_SEARCH_ERROR("ბრენდი ვერ მოიძებნა: %s"),
    XML_PARSER_CREATION_FAILED("XML parser ვერ შეიქმნა"),
    PAGE_SOURCE_PARSE_FAILED("page source ვერ დაიპარსა"),
    APP_TERMINATE_FAILED("აპლიკაციის გათიშვა ვერ მოხერხდა: %s"),
    APPIUM_SESSION_CLOSE_FAILED("Appium სესიის დახურვა ვერ მოხერხდა: %s"),
    CATEGORY_TESTCASE_MISSING("CategoryTestCase აკლია — ტესტს DataProvider სჭირდება"),
    STRING_PARSE_FAILED("სტრინგის პარსვა ვერ მოხერხდა: %s"),
    UNKNOWN_MOBILE_SECTION("უცნობი სექცია მობილურისთვის: %s"),
    ELEMENTS_NOT_FOUND_TIMEOUT("ელემენტები ვერ მოიძებნა ან მოლოდინის დრო ამოიწურა");

    private final String template;

    ErrorMessages(String template) {
        this.template = template;
    }

    public String get() {
        return template;
    }

    public String format(Object... args) {
        return String.format(template, args);
    }
}