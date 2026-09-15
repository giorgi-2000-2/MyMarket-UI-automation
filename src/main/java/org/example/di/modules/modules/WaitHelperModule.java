package org.example.di.modules.modules;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import org.example.di.modules.TestScope;
import org.example.pages.basepage.JavaScriptHelper;
import org.example.pages.basepage.WaitHelper;
import org.example.utils.Waits;

public class WaitHelperModule extends AbstractModule {
    public final TestScope TEST_SCOPE;

    public WaitHelperModule(TestScope testScope) {
        TEST_SCOPE = testScope;
    }

    @Override
    protected void configure() {
        bind(JavaScriptHelper.class).in(TEST_SCOPE);
        bind(WaitHelper.class).in(Singleton.class);
        bind(Waits.class).in(TEST_SCOPE);
    }



}
