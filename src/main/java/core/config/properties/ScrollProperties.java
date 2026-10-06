package core.config.properties;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import core.config.IScrollConfig;
import core.config.TypedPropertiesHelper;
@Singleton
public class ScrollProperties implements IScrollConfig {
    private final TypedPropertiesHelper helper;

@Inject
    public ScrollProperties(TypedPropertiesHelper helper) {
        this.helper = helper;
    }


    @Override
    public int swipeMs() {
        return helper.requireInt("scroll.swipe.ms");
    }


    @Override
    public double nudgeFactor() {
        return helper.requireDouble("scroll.nudge.factor");
    }

    @Override
    public int centerTolerance() {
        return helper.requireInt("scroll.center.tolerance");
    }

    @Override
    public double minY() {
        return helper.requireDouble("scroll.min.y");
    }

    @Override
    public double maxY() {
        return helper.requireDouble("scroll.max.y");
    }

    @Override
    public int maxScrolls() {
        return helper.requireInt("scroll.max.scrolls");
    }





}
