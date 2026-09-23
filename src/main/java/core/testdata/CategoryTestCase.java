package core.testdata;
import core.reporter.texts.UiText;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import core.config.properties.CategoryNameBtn;

@Getter
@AllArgsConstructor
@Builder
public class CategoryTestCase {
    private final CategoryNameBtn section;

    private final boolean checkBrands;

    private final boolean skipTitleCheck;

    @Override
    public String toString() {
        return section + (checkBrands ? UiText.WITH_BRANDS_SUFFIX.get() : "");
    }


    public String stateKey() {
        String base = section.name();
        if (checkBrands) base += "_brands";
        if (skipTitleCheck) base += "_skipTitle";
        return base;
    }

}