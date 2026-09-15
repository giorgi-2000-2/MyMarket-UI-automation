package org.example.di.modules.modules;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.example.di.modules.TestScope;
import org.example.utils.driver.DriverManager;
import org.example.utils.driver.IDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverModule extends AbstractModule {
    private final TestScope testScope;

    public DriverModule(TestScope testScope) {
        this.testScope = testScope;
    }

    @Override
    protected void configure() {
        bind(IDriver.class).to(DriverManager.class).in(testScope);
    }

    @Provides
    public ChromeOptions provideChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        return options;
    }
}