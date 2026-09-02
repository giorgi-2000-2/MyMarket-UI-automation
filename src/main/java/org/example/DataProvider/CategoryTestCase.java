package org.example.DataProvider;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CategoryTestCase {
    private final String section;

    private final String expectedUrl;

    private final boolean checkBrands;

    private final boolean skipTitleCheck;

    @Override
    public String toString() {
        return section + (checkBrands ? " ბრენდები" : "");
    }
}