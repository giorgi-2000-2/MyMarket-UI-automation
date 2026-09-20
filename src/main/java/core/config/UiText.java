package core.config;

import lombok.Getter;

@Getter
public enum UiText {
    BACK_CLICK("უკან დაბრუნება"),
    CHOOSE_CATEGORY("აირჩიე/ჩაწერე კატეგორია"),
    LOGIN_BUTTON("შესვლა");

    private final String value;

    UiText(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}