package core.testdata;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import core.config.properties.CategoryNameBtn;
import core.reporter.ReportMessages;

@Getter
@AllArgsConstructor
@Builder
public class CategoryTestCase {
    private final CategoryNameBtn section;

    private final boolean checkBrands;

    private final boolean skipTitleCheck;

    @Override
    public String toString() {
        return section + (checkBrands ? ReportMessages.WITH_BRANDS_SUFFIX.get() : "");
    }
}