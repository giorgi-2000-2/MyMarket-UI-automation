package org.example.di.modules.modules;
import com.google.inject.AbstractModule;
import org.example.di.modules.TestScope;
import org.example.pages.advertisement.BrandDropdownComponent;
import org.example.pages.advertisement.CategoryDropdownComponent;
import org.example.pages.advertisement.TitleComponent;
import org.example.pages.advertisement.UserInfoComponent;

public class ComponentModule extends AbstractModule {
    public final TestScope TEST_SCOPE;

    public ComponentModule(TestScope testScope){
        TEST_SCOPE = testScope;
    }

    @Override
    protected void configure() {


        bind(UserInfoComponent.class).in(TEST_SCOPE);
        bind(CategoryDropdownComponent.class).in(TEST_SCOPE);
        bind(BrandDropdownComponent.class).in(TEST_SCOPE);
        bind(TitleComponent.class).in(TEST_SCOPE);


    }




}
