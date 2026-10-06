package core.utils.state;

import com.google.inject.Inject;
import core.annotations.TestScoped;
import core.reporter.IReportTree;
import core.testdata.CategoryTestCase;

import java.nio.file.Paths;

@TestScoped
public class CrawlStateCreator {

    private final IReportTree reporter;

    @Inject
    public CrawlStateCreator(IReportTree reporter) {
        this.reporter = reporter;
    }

    public IState forCase(String stateDir, CategoryTestCase testCase) {
        if (stateDir == null || stateDir.isBlank()) {
            return new InMemoryCrawlState();
        }
        var file = Paths.get(stateDir.trim(), testCase.stateKey() + ".txt");
        return new FileCrawlState(file, reporter);
    }
}