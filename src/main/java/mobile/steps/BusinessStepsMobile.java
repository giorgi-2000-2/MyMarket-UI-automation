package mobile.steps;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import core.steps.ICategoryCheckSteps;
import core.utils.state.CrawlStateCreator;
import core.utils.state.IState;
import mobile.crawler.CategoryCrawler;
import mobile.crawler.CategoryPathFormatter;
import core.steps.LeafHandler;
import core.annotations.TestScoped;
import core.testdata.CategoryTestCase;

import java.util.List;

@TestScoped
public class BusinessStepsMobile implements ICategoryCheckSteps {
    private final CategoryCrawler walker;
    private final MobileBrandVerificationSteps brandVerificationSteps;
    private final CategoryPathFormatter pathFormatter;
    private final CrawlStateCreator stateCreator;
    private final String stateDir;
    @Inject
    public BusinessStepsMobile(CategoryCrawler walker , MobileBrandVerificationSteps brandVerificationSteps, CategoryPathFormatter pathFormatter, CrawlStateCreator stateCreator, @Named("crawlStateDir") String stateDir) {
        this.walker = walker;
        this.brandVerificationSteps = brandVerificationSteps;
        this.pathFormatter = pathFormatter;
        this.stateCreator = stateCreator;
        this.stateDir = stateDir;
    }


    public void checkAllCategories(CategoryTestCase testCase) {
        IState state = stateCreator.forCase(stateDir,testCase);
        walker.crawl(new LeafHandler() {
            @Override
            public void handle(List<String> fullPath) {
                brandVerificationSteps.verifyCategoryWithData(pathFormatter.fullName(fullPath), testCase);
            }
        },state);
    }

}
