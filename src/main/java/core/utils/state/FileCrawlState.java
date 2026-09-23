package core.utils.state;


import core.annotations.TestScoped;
import core.reporter.IReportTree;
import core.reporter.ReportStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
@TestScoped
public class FileCrawlState extends InMemoryCrawlState {

    private final Path stateFile;
    private final IReportTree reporter;

    public FileCrawlState(Path stateFile, IReportTree reporter) {
        this.stateFile = stateFile;
        this.reporter = reporter;
        load();
    }

    @Override
    public void markDone(String key) {
        if (done.add(key)) append(key);
    }

    private void load() {
        if (!Files.exists(stateFile)) return;
        try {
            done.addAll(Files.readAllLines(stateFile, StandardCharsets.UTF_8));
            done.remove("");
            reporter.info("აღდგენილია " + done.size() + " უკვე შემოვლილი კვანძი");
        } catch (IOException e) {
            reporter.log(ReportStatus.WARNING, "state ფაილი ვერ წაიკითხა: " + e.getMessage());
        }
    }

    private void append(String line) {
        try {
            if (stateFile.getParent() != null) Files.createDirectories(stateFile.getParent());
            Files.writeString(stateFile, line + System.lineSeparator(), StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            reporter.log(ReportStatus.WARNING, "state ფაილში ვერ ჩაიწერა: " + e.getMessage());
        }
    }
}
