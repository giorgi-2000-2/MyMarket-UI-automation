package core.reporter.texts;

public enum StepNames {
    LOGIN("დალოგინება"),
    NAVIGATE_TO_AD_PAGE("ნავიგაცია განცხადების დამატების გვერდზე"),
    CHECK_CATEGORIES_AND_BRANDS("კატეგორიების და ბრენდების შემოწმება"),
    CHECK_CATEGORIES("კატეგორიების ნახვა"),
    COMPARE_CATEGORIES_WITH_DATA("კატეგორიების შედარება მონაცემებთან"),
    BACK_NAVIGATION("უკან დაბრუნების ნავიგაცია"),
    SECTION_VIEW("%s-ის ნახვა"),
    TITLE_COMPARE("შედარება"),
    CHECK_URL("მისამართის შემოწმება"),
    AFTER_NAV_TO_AD("განცხადების გვერდზე გადასვლის შემდეგ შემოწმება"),
    CHECK_MAIN_TITLE("მთავარი სათაური"),
    CHECK_USER_NAME("მომხმარებლის სახელი"),
    CHECK_USER_ID("მომხმარებლის ID"),
    PROFILE_CHECK("პროფილში მომხმარებლის შემოწმება"),
    BRANDS_NODE("ბრენდები"),
    CLICK_SECTION_BUTTON("%s ღილაკზე დაკლიკება"),
    CLICK_CATEGORY_DROPDOWN("დროპდაუნ კატეგორიებზე დაკლიკება"),
    AFTER_SECTION_CLICK("%s ღილაკზე დაჭერის შემდეგ შემოწმება");

    private final String template;

    StepNames(String template) {
        this.template = template;
    }

    public String get() {
        return template;
    }

    public String format(Object... args) {
        return String.format(template, args);
    }
}