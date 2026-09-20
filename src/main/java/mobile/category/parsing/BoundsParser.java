package mobile.category.parsing;

import com.google.inject.Inject;
import core.config.IPatternConfig;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BoundsParser {
    private final Pattern boundsPattern;
    @Inject
    public BoundsParser(IPatternConfig patternConfig) {
        this.boundsPattern = Pattern.compile(patternConfig.boundsPattern());
    }

    public int[] parse(String bounds) {
        if (bounds != null) {
            Matcher m = boundsPattern.matcher(bounds);
            if (m.find()) {
                return new int[]{
                        Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)),
                        Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4))};
            }
        }
        return new int[]{0, 0, 0, 0};
    }
}
