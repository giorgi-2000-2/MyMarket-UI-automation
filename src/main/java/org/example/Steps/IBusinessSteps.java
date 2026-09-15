package org.example.steps;
import org.example.dataprovider.CategoryTestCase;

public interface IBusinessSteps {
    void checkAllCategories(CategoryTestCase testCase);
    void checkAllCategoryItems(CategoryTestCase testCase);
    void checkAllCategoryBackClickNavigation();
    void NavigateToAdvertisementPage();
    void navigationChecks(CategoryTestCase testCase);
}