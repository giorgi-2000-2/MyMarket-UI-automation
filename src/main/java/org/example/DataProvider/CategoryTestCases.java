package org.example.DataProvider;

import org.testng.annotations.DataProvider;

public class CategoryTestCases {

    @DataProvider(name = "CategoriesAndBrandsDataCheck")
    public static Object[][] getCategoriesAndBrandsDataCheckTestCase() {
        return new Object[][] {
                {
                        CategoryTestCase.builder()
                                .section("გაყიდვა")
                                .expectedUrl("https://mymarket.ge/ka/pr-form/sell")
                                .checkBrands(true)
                                .build()
                },
                {
                        CategoryTestCase.builder()
                                .section("გაყიდვა")
                                .expectedUrl("https://mymarket.ge/ka/pr-form/sell")
                                .checkBrands(false)
                                .build()
                },

                {
                        CategoryTestCase.builder()
                                .section("ყიდვა")
                                .expectedUrl("https://mymarket.ge/ka/pr-form?Org=1&PCat=0")
                                .checkBrands(true)
                                .build()
                },
                {
                        CategoryTestCase.builder()
                                .section("ყიდვა")
                                .expectedUrl("https://mymarket.ge/ka/pr-form?Org=1&PCat=0")
                                .checkBrands(false)
                                .build()
                },
                {
                        CategoryTestCase.builder()
                                .section("გაქირავება")
                                .expectedUrl("https://www.mymarket.ge/ka/pr-form?Org=2&PCat=2")
                                .checkBrands(false)
                                .build()
                },
                {
                        CategoryTestCase.builder()
                                .section("მომსახურება")
                                .expectedUrl("https://www.mymarket.ge/ka/pr-form?Org=3&PCat=1")
                                .checkBrands(false)
                                .skipTitleCheck(true)
                                .build()
                },
        };
    }



    @DataProvider(name = "categoryTitleMatches")
    public static Object[][] getCategoryTitleMatchesTestCase() {
        return new Object[][] {

                {
                        CategoryTestCase.builder()
                                .section("გაქირავება")
                                .expectedUrl("https://www.mymarket.ge/ka/pr-form?Org=2&PCat=2")
                                .build()
                },
        };
    }

    @DataProvider(name = "categoryBackClick")
    public static Object[][] getCategoryBackClickTestCase() {
        return new Object[][] {
                {
                        CategoryTestCase.builder()
                                .section("გაყიდვა")
                                .expectedUrl("https://mymarket.ge/ka/pr-form/sell")
                                .build()
                },

                {
                        CategoryTestCase.builder()
                                .section("ყიდვა")
                                .expectedUrl("https://mymarket.ge/ka/pr-form?Org=1&PCat=0")
                                .build()
                },

                {
                        CategoryTestCase.builder()
                                .section("გაქირავება")
                                .expectedUrl("https://www.mymarket.ge/ka/pr-form?Org=2&PCat=2")
                                .build()
                },
                {
                        CategoryTestCase.builder()
                                .section("მომსახურება")
                                .expectedUrl("https://www.mymarket.ge/ka/pr-form?Org=3&PCat=1")
                                .skipTitleCheck(true)
                                .build()
                }
        };
    }

}