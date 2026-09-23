package core.utils.state;

import com.google.inject.Inject;
import core.annotations.TestScoped;
import core.config.IAppTree;
import core.reporter.IReportTree;
import core.testdata.CategoryTestCase;

import java.nio.file.Paths;

@TestScoped
public class CrawlStateCreator {

    private final IAppTree config;
    private final IReportTree reporter;

    @Inject
    public CrawlStateCreator(IAppTree config, IReportTree reporter) {
        this.config = config;
        this.reporter = reporter;
    }

    public IState forCase(CategoryTestCase testCase) {
        String dir = config.stateFile();
        if (dir == null || dir.trim().isEmpty()) {
            return new InMemoryCrawlState();
        }
        var file = Paths.get(dir.trim(), testCase.stateKey() + ".txt");
        return new FileCrawlState(file, reporter);
    }
}