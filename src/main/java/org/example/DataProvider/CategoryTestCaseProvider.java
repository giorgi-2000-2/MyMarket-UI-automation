package org.example.dataprovider;

import org.example.di.SuiteContext;
import org.example.utils.config.IBtnUrl;
import org.testng.annotations.DataProvider;

import static org.example.pages.CategoryNameBtn.*;

public class CategoryTestCaseProvider {
private final IBtnUrl configuration;


    public CategoryTestCaseProvider() {
        this(SuiteContext.btnUrl());
    }

    public CategoryTestCaseProvider(IBtnUrl configuration) {
        this.configuration = configuration;
    }

    @DataProvider(name = "CategoriesAndBrandsDataCheck")
    public Object[][] getCategoriesAndBrandsDataCheckTestCase() {

        return new Object[][] {
                {
                        CategoryTestCase.builder()
                                .section(SELLBTN)
                                .expectedUrl(configuration.sellUrl())
                                .checkBrands(true)
                                .build()
                },
                {
                        CategoryTestCase.builder()
                                .section(SELLBTN)
                                .expectedUrl(configuration.sellUrl())
                                .checkBrands(false)
                                .build()
                },

                {
                        CategoryTestCase.builder()
                                .section(BUYBTN)
                                .expectedUrl(configuration.buyUrl())
                                .checkBrands(true)
                                .build()
                },
                {
                        CategoryTestCase.builder()
                                .section(BUYBTN)
                                .expectedUrl(configuration.buyUrl())
                                .checkBrands(false)
                                .build()
                },
                {
                        CategoryTestCase.builder()
                                .section(RENTBTN)
                                .expectedUrl(configuration.rentUrl())
                                .checkBrands(false)
                                .build()
                },
                {
                        CategoryTestCase.builder()
                                .section(SERVICEBTN)
                                .expectedUrl(configuration.serviceUrl())
                                .checkBrands(false)
                                .skipTitleCheck(true)
                                .build()
                },
       };
    }



    @DataProvider(name = "categoryTitleMatches")
    public Object[][] getCategoryTitleMatchesTestCase() {
        return new Object[][] {

                {
                        CategoryTestCase.builder()
                                .section(RENTBTN)
                                .expectedUrl(configuration.rentUrl())
                                .build()
                },
        };
    }

    @DataProvider(name = "categoryBackClick")
    public Object[][] getCategoryBackClickTestCase() {
        return new Object[][] {
                {
                        CategoryTestCase.builder()
                                .section(SELLBTN)
                                .expectedUrl(configuration.sellUrl())
                                .build()
                },

                {
                        CategoryTestCase.builder()
                                .section(BUYBTN)
                                .expectedUrl(configuration.buyUrl())
                                .build()
                },

                {
                        CategoryTestCase.builder()
                                .section(RENTBTN)
                                .expectedUrl(configuration.rentUrl())
                                .build()
                },
                {
                        CategoryTestCase.builder()
                                .section(SERVICEBTN)
                                .expectedUrl(configuration.serviceUrl())
                                .skipTitleCheck(true)
                                .build()
                }
        };
    }

}