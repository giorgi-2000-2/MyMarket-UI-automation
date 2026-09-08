package org.example.steps;
import org.example.dataprovider.CategoryTestCase;
import org.example.manager.*;
import org.example.pages.PageNavigator;

import org.testng.asserts.SoftAssert;


import static org.example.utils.reporter.NodeKey.CATEGORY;


public class BusinessSteps {
    private final CategoryWalker walker;
    private  final DataStepsManager dataManager;
 private PageNavigator navigator;


    public BusinessSteps(CategoryWalker walker, DataStepsManager dataManager, PageNavigator navigator) {
        this.walker = walker;
        this.dataManager = dataManager;
        this.navigator = navigator;
    }

    public void checkAllCategories(SoftAssert softAssert, CategoryTestCase testCase)   {
        walker.walk(new IAction() {
            @Override
            public void execute() {
                dataManager.verifyCategoryWithBrands(softAssert, testCase);
            }
        });
    }


    public void checkAllCategoryItems(SoftAssert softAssert, CategoryTestCase testCase)   {
        walker.walk(new IAction() {
            @Override
            public void execute() {
                dataManager.verifyTitleMatchesPreview(softAssert,CATEGORY,testCase);
            }
        });
    }

    public void checkAllCategoryBackClickNavigation(SoftAssert softAssert) {
        dataManager.verifyBackClickNavigation(softAssert);
    }

    public void NavigateToAdvertisementPage(SoftAssert softAssert) {
        navigator.loginAndNavigateToAdvertisementPage(softAssert);
    }







}