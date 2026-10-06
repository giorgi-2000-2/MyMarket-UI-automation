package mobile.dimodulemobile;
import com.google.inject.AbstractModule;
import mobile.category.driver.ISwiper;
import mobile.category.driver.TouchSwiper;
import mobile.category.navigation.*;
import mobile.category.screen.AutoConfirmIScreenReader;
import mobile.category.screen.PageSourceScreenReaderI;
import mobile.category.screen.IRawScreenReader;
import mobile.category.screen.IScreenReader;
import mobile.category.scroll.IListScroller;
import mobile.category.scroll.NudgeIListScroller;
import mobile.category.scroll.IPageScroller;
import mobile.category.scroll.SwipeIPageScroller;
import mobile.pages.AdvertisementPage;

public class CategoryPickerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ISwiper.class).to(TouchSwiper.class);
        bind(IRawScreenReader.class).to(PageSourceScreenReaderI.class);
        bind(IScreenReader.class).to(AutoConfirmIScreenReader.class);
        bind(IListScroller.class).to(NudgeIListScroller.class);
        bind(IPageScroller.class).to(SwipeIPageScroller.class);
        bind(ICategoryNamesCollector.class).to(ScrollingICategoryNamesCollector.class);
        bind(ICategoryNavigator.class).to(ICategoryPickerNavigator.class);
        bind(ICategoryFieldActions.class).to(AdvertisementPage.class);
    }
}
