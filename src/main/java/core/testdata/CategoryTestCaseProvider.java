package core.testdata;
import org.testng.annotations.DataProvider;

import static core.testdata.Section.*;

public class CategoryTestCaseProvider {



    @DataProvider(name = "CategoriesAndBrandsDataCheck")
    public Object[][] getCategoriesAndBrandsDataCheckTestCase() {

        return new Object[][] {
//                {
//                        CategoryTestCase.builder()
//                                .section(SELL)
//                                .checkBrands(true)
//                                .build()
//                },
                {
                        CategoryTestCase.builder()
                                .section(SELL)
                                .checkBrands(false)
                                .build()
                },

                {
                        CategoryTestCase.builder()
                                .section(BUY)
                                .checkBrands(true)
                                .build()
                },
                {
                        CategoryTestCase.builder()
                                .section(BUY)
                                .checkBrands(false)
                                .build()
                },
                {
                        CategoryTestCase.builder()
                                .section(RENT)
                                .checkBrands(false)
                                .build()
                },
                {
                        CategoryTestCase.builder()
                                .section(SERVICE)
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
                                .section(RENT)
                                .build()
                },
        };
    }

    @DataProvider(name = "categoryBackClick")
    public Object[][] getCategoryBackClickTestCase() {
        return new Object[][] {
                {
                        CategoryTestCase.builder()
                                .section(SELL)
                                .build()
                },

                {
                        CategoryTestCase.builder()
                                .section(BUY)
                                .build()
                },

                {
                        CategoryTestCase.builder()
                                .section(RENT)
                                .build()
                },
                {
                        CategoryTestCase.builder()
                                .section(SERVICE)
                                .skipTitleCheck(true)
                                .build()
                }
        };
    }

}