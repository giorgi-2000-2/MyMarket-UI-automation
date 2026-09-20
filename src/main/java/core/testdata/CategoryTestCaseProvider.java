package core.testdata;
import org.testng.annotations.DataProvider;

import static core.config.properties.CategoryNameBtn.*;

public class CategoryTestCaseProvider {



    @DataProvider(name = "CategoriesAndBrandsDataCheck")
    public Object[][] getCategoriesAndBrandsDataCheckTestCase() {

        return new Object[][] {
                {
                        CategoryTestCase.builder()
                                .section(SELLBTN)
                                .checkBrands(true)
                                .build()
                },
                {
                        CategoryTestCase.builder()
                                .section(SELLBTN)
                                .checkBrands(false)
                                .build()
                },

                {
                        CategoryTestCase.builder()
                                .section(BUYBTN)
                                .checkBrands(true)
                                .build()
                },
                {
                        CategoryTestCase.builder()
                                .section(BUYBTN)
                                .checkBrands(false)
                                .build()
                },
                {
                        CategoryTestCase.builder()
                                .section(RENTBTN)
                                .checkBrands(false)
                                .build()
                },
                {
                        CategoryTestCase.builder()
                                .section(SERVICEBTN)
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
                                .build()
                },

                {
                        CategoryTestCase.builder()
                                .section(BUYBTN)
                                .build()
                },

                {
                        CategoryTestCase.builder()
                                .section(RENTBTN)
                                .build()
                },
                {
                        CategoryTestCase.builder()
                                .section(SERVICEBTN)
                                .skipTitleCheck(true)
                                .build()
                }
        };
    }

}