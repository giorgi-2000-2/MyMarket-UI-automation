package core.reporter;

public enum ReportMessages {

    TEST_STARTED("ტესტი დაიწყო: %s"),
    BROWSER_CLOSE_FAILED("ბრაუზერის დახურვა ვერ მოხერხდა: %s"),

    FINAL_CATEGORY_NO_BACK("%s — საბოლოო კატეგორიაა, დასაბრუნებელი არაფერია"),
    BACK_RESTORE_CHECK("%s — უკან დაბრუნება"),
    AFTER_SECTION_CLICK("%s ღილაკზე დაჭერის შემდეგ შემოწმება"),

    BRAND_CHECK_ERROR("%s — ბრენდის შემოწმებისას შეცდომა: %s"),
    CATEGORY_FOUND("%s მოიძებნა"),
    CATEGORY_CONTAINS_BRAND("%s შეიცავს %s"),

    SCREENSHOT_FAILED("სქრინშოთის გადაღება ვერ მოხერხდა: %s"),
    BROWSER_OPEN_FAILED("ბრაუზერის ავტომატურად გახსნა ვერ მოხერხდა: %s"),
    NODE_NOT_FOUND("Node '%s' ვერ მოიძებნა: %s"),
    PARENT_NODE_MISSING("მშობელი ნოუდი სახელით '%s' ვერ მოიძებნა. შვილი ნოუდი შეიქმნება მთავარ ტესტში."),

    DIALOG_NOT_SHOWN("დიალოგი არ გამოჩნდა (%s)"),
    EXTRA_SOFT_ERRORS("დამატებითი soft-assert შეცდომები (ტესტი უკვე ჩავარდნილი იყო):\n%s"),

    LOGIN("დალოგინება"),
    NAVIGATE_TO_AD_PAGE("ნავიგაცია განცხადების დამატების გვერდზე"),

    CHECK_CATEGORIES_AND_BRANDS("კატეგორიების და ბრენდების შემოწმება"),
    CHECK_CATEGORIES("კატეგორიების ნახვა"),
    COMPARE_CATEGORIES_WITH_DATA("კატეგორიების შედარება მონაცემებთან"),
    CONFIG_KEY_MISSING("კონფიგის გასაღები აკლია: %s"),
    CONFIG_KEY_NOT_NUMBER("კონფიგის გასაღები %s არ არის რიცხვი: %s"),
    BACK_NAVIGATION("უკან დაბრუნების ნავიგაცია"),
    SECTION_VIEW("%s-ის ნახვა"),
    TITLE_COMPARE("შედარება"),
    CHECK_URL("მისამართის შემოწმება"),
    AFTER_NAV_TO_AD("განცხადების გვერდზე გადასვლის შემდეგ შემოწმება"),
    CHECK_MAIN_TITLE("მთავარი სათაური"),
    CHECK_USER_NAME("მომხმარებლის სახელი"),
    NAME_ISEMPTY("მომხმარებლის სახელი ცარიელია"),
    CHECK_USER_ID("მომხმარებლის ID"),
    PROFILE_CHECK("პროფილში მომხმარებლის შემოწმება"),
    DROPDOWN_NAME_MATCHES_PAGE("Dropdown-ის სახელი ემთხვევა გვერდის სახელს"),
    PASS_MATCH(" %s: '%s'"),
    FAIL_MISMATCH(" %s — მოსალოდნელი: '%s', მიმდინარე: '%s'"),

    BRAND_DROPDOWN_MISSING(" — ბრენდის dropdown არ არის, გამოტოვება"),
    BRANDS_NODE("ბრენდები"),
    CLICK_SECTION_BUTTON("%s ღილაკზე დაკლიკება"),
    CLICK_CATEGORY_DROPDOWN("დროპდაუნ კატეგორიებზე დაკლიკება"),
    DIALOG_CLOSED("დიალოგის ფანჯარა წარმატებით დაიხურა."),
    AD_DIALOG_CLOSED("რეკლამის დიალოგის ფანჯარა (dialog) წარმატებით დაიხურა."),

    REPORT_FILE_NOT_FOUND("რეპორტის ფაილი მითითებულ მისამართზე ვერ მოიძებნა!"),
    WITH_BRANDS_SUFFIX(" ბრენდები"),
    SUITE_STARTED("Test suite Started : %s"),
    SUITE_FINISHED("Test suite finished : %s"),
    TEST_SUCCESS("Test Success : %s"),
    TEST_FAILED("Test Failed : %s"),
    TEST_SKIPPED_LOG("Test Skipped : %s"),

    TEST_PASSED("Test Passed"),
    TEST_FAILED_MSG("Test failed: %s"),
    TEST_SKIPPED("Test Skipped"),

    CATEGORY_NOT_FOUND("ვერ ვიპოვე კატეგორია: '%s' (ვერც ქვემოთ და ვერც ზემოთ სქროლვით)"),
    CATEGORY_PICKER_OPEN_FAILED("კატეგორიის პიკერი ვერ გაიხსნა"),
    BRAND_FIELD_NOT_FOUND_RETURNING("⚠️ ბრენდის ველი ვერ მოიძებნა, ვბრუნდებით კატეგორიებში..."),
    CHARACTERISTICS_NOT_FOUND_RETURNING("⚠️ მახასიათებლები ვერ მოიძებნა, ვბრუნდებით კატეგორიებში..."),
    BRAND_SEARCH_ERROR("ბრენდი ვერ მოიძებნა: %s"),
    XML_PARSER_CREATION_FAILED("XML parser ვერ შეიქმნა"),
    PAGE_SOURCE_PARSE_FAILED("page source ვერ დაიპარსა"),
    APP_TERMINATE_FAILED("⚠️ აპლიკაციის გათიშვა ვერ მოხერხდა: %s"),
    APPIUM_SESSION_CLOSE_FAILED("⚠️ Appium სესიის დახურვა ვერ მოხერხდა: %s"),
    CATEGORY_TESTCASE_MISSING("CategoryTestCase აკლია — ტესტს DataProvider სჭირდება"),

    UNKNOWN_MOBILE_SECTION("უცნობი სექცია მობილურისთვის: %s"),
    ELEMENTS_NOT_FOUND_TIMEOUT("⚠️ ელემენტები ვერ მოიძებნა ან მოლოდინის დრო ამოიწურა");

    private final String template;

    ReportMessages(String template) {
        this.template = template;
    }

    public String get() {
        return template;
    }

    public String format(Object... args) {
        return String.format(template, args);
    }
}