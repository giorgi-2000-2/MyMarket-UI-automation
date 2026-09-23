package mobile.dimodulemobile;
import com.google.inject.AbstractModule;
import mobile.category.driver.Swiper;
import mobile.category.driver.TouchSwiper;
import mobile.category.navigation.CategoryNamesCollector;
import mobile.category.navigation.CategoryNavigator;
import mobile.category.navigation.CategoryPickerNavigator;
import mobile.category.navigation.ScrollingCategoryNamesCollector;
import mobile.category.screen.AutoConfirmScreenReader;
import mobile.category.screen.PageSourceScreenReader;
import mobile.category.screen.RawScreenReader;
import mobile.category.screen.ScreenReader;
import mobile.category.scroll.ListScroller;
import mobile.category.scroll.NudgeListScroller;
import mobile.category.scroll.PageScroller;
import mobile.category.scroll.SwipePageScroller;

public class CategoryPickerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Swiper.class).to(TouchSwiper.class);
        bind(RawScreenReader.class).to(PageSourceScreenReader.class);
        bind(ScreenReader.class).to(AutoConfirmScreenReader.class);
        bind(ListScroller.class).to(NudgeListScroller.class);
        bind(PageScroller.class).to(SwipePageScroller.class);
        bind(CategoryNamesCollector.class).to(ScrollingCategoryNamesCollector.class);
        bind(CategoryNavigator.class).to(CategoryPickerNavigator.class);

    }
}
