package core.reporter.texts;

import lombok.Getter;

@Getter
public enum UiText {
    BACK_CLICK("უკან დაბრუნება"),
    CHOOSE_CATEGORY("აირჩიე/ჩაწერე კატეგორია"),
    LOGIN_BUTTON("შესვლა"),
    WITH_BRANDS_SUFFIX(" ბრენდები");

    private final String value;

    UiText(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}