package org.example.DataProvider;

import org.testng.annotations.DataProvider;

public class CategoryDataProvider {

    @DataProvider(name = "CategoriesAndBrandsDataCheck")
    public static Object[][] getCategoriesAndBrandsData() {
        return new Object[][] {
                {
                        DataDto.builder()
                                .name("გაყიდვა")
                                .url("https://mymarket.ge/ka/pr-form/sell")
                                .check(true)
                                .build()
                },
                {
                        DataDto.builder()
                                .name("გაყიდვა")
                                .url("https://mymarket.ge/ka/pr-form/sell")
                                .check(false)
                                .build()
                },

                {
                        DataDto.builder()
                                .name("ყიდვა")
                                .url("https://mymarket.ge/ka/pr-form?Org=1&PCat=0")
                                .check(true)
                                .build()
                },
                {
                        DataDto.builder()
                                .name("ყიდვა")
                                .url("https://mymarket.ge/ka/pr-form?Org=1&PCat=0")
                                .check(false)
                                .build()
                },
                {
                        DataDto.builder()
                                .name("გაქირავება")
                                .url("https://www.mymarket.ge/ka/pr-form?Org=2&PCat=2")
                                .check(false)
                                .build()
                },
                {
                        DataDto.builder()
                                .name("მომსახურება")
                                .url("https://www.mymarket.ge/ka/pr-form?Org=3&PCat=1")
                                .check(false)
                                .build()
                },
        };
    }



    @DataProvider(name = "categoryData")
    public static Object[][] getCategoryData() {
        return new Object[][] {

                {
                        DataDto2.builder()
                                .name("გაქირავება")
                                .url("https://www.mymarket.ge/ka/pr-form?Org=2&PCat=2")
                                .build()
                },
        };
    }

    @DataProvider(name = "categoryBackclickData")
    public static Object[][] getCategoryDataa() {
        return new Object[][] {
                {
                        DataDto2.builder()
                                .name("გაყიდვა")
                                .url("https://mymarket.ge/ka/pr-form/sell")
                                .build()
                },

                {
                        DataDto2.builder()
                                .name("ყიდვა")
                                .url("https://mymarket.ge/ka/pr-form?Org=1&PCat=0")
                                .build()
                },

                {
                        DataDto2.builder()
                                .name("გაქირავება")
                                .url("https://www.mymarket.ge/ka/pr-form?Org=2&PCat=2")
                                .build()
                },
                {
                        DataDto2.builder()
                                .name("მომსახურება")
                                .url("https://www.mymarket.ge/ka/pr-form?Org=3&PCat=1")
                                .build()
                }
        };
    }

}