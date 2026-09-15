package org.example.utils.config;

import lombok.Getter;

@Getter
public enum UiText {
    BACK_CLICK("უკან დაბრუნება"),
CHOOSE_CATEGORY("აირჩიე/ჩაწერე კატეგორია");
    private final String path;

    UiText(String path) {
        this.path = path;
    }
}
