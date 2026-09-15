package org.example.di.modules;
import lombok.Getter;
import lombok.Setter;
import org.example.dataprovider.CategoryTestCase;

@Getter
@Setter
public class TestContext {
    private CategoryTestCase testCase;
    private String           currentCategoryPath;
}