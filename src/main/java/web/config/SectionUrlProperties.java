package web.config;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import core.config.TypedPropertiesHelper;
import core.testdata.Section;

@Singleton
public class SectionUrlProperties implements ISectionUrls {
    private final TypedPropertiesHelper helper;

    @Inject
    public SectionUrlProperties(TypedPropertiesHelper helper) {
        this.helper = helper;
    }

    @Override
    public String urlOf(Section section) {
        return helper.require(section.urlKey());
    }
}
