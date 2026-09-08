package org.example.dataprovider;

import lombok.Getter;

@Getter
public enum SectionNames {
    CATEGORIES ("categories"),
    RENTAL_CATEGORIES("rental_categories"),
    SERVICE_CATEGORIES("service_categories");





    private final String path;

    SectionNames(String path) {
        this.path = path;
    }
}
