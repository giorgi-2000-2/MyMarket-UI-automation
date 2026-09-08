package org.example.dataprovider;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.pages.CategoryNameBtn;

@Getter
@AllArgsConstructor
@Builder
public class CategoryTestCase {
    private final CategoryNameBtn section;

    private final String expectedUrl;

    private final boolean checkBrands;

    private final boolean skipTitleCheck;

    @Override
    public String toString() {
        return section + (checkBrands ? " ბრენდები" : "");
    }
}