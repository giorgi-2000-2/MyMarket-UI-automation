package org.example.steps;

import org.example.dataprovider.CategoryTestCase;

public interface IPageNavigator {
    void navigationMainCheck(CategoryTestCase testCase);
    void navigateToAdvertisementPage();
    void loginAndNavigateToAdvertisementPage();
}