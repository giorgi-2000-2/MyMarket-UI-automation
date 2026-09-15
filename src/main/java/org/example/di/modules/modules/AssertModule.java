package org.example.di.modules.modules;
import com.google.inject.AbstractModule;
import org.example.asserts.*;
import org.example.di.modules.TestScope;


public class AssertModule extends AbstractModule {
    public final TestScope TEST_SCOPE;

    public AssertModule(TestScope testScope){
        TEST_SCOPE = testScope;
    }

    @Override
    protected void configure() {
        bind(ISoftVerifier.class).to(SoftVerifier.class).in(TEST_SCOPE);
        bind(CategoryAsserts.class).in(TEST_SCOPE);
        bind(NavigationAsserts.class).in(TEST_SCOPE);
        bind(TitleAsserts.class).in(TEST_SCOPE);
        bind(UserInfoAsserts.class).in(TEST_SCOPE);


    }




}
