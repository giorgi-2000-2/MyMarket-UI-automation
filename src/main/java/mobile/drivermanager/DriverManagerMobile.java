package mobile.drivermanager;

import core.driver.IDriver;
import core.reporter.ReportMessages;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import core.annotations.TestScoped;

import java.net.MalformedURLException;
import java.net.URL;
@TestScoped
public class DriverManagerMobile implements IDriver {
    private static final String APP_PACKAGE = "ge.my.mymarket";
    private final ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();

    public AndroidDriver getDriver() {
        if (driver.get() == null) {
            try {
                DesiredCapabilities cap = new DesiredCapabilities();
                cap.setCapability("platformName", "android");
                cap.setCapability("appium:automationName", "uiautomator2");
                cap.setCapability("appium:skipUnlock", true);
                cap.setCapability("appium:noReset", true);
                cap.setCapability("appium:appPackage", APP_PACKAGE);
                cap.setCapability("appium:appActivity", "ge.my.mymarket.MainActivity");
                cap.setCapability("appium:autoGrantPermissions", true);
                cap.setCapability("appium:avd", "Pixel_6a");
                cap.setCapability("appium:avdLaunchTimeout", "900000");
                cap.setCapability("appium:forceAppLaunch", true);
                cap.setCapability("appium:shouldTerminateApp", true);

                driver.set(new AndroidDriver(new URL("http://127.0.0.1:4723"), cap));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return driver.get();
    }

    public void quit() {
        AndroidDriver current = driver.get();
        if (current == null) return;
        try {
            current.terminateApp(APP_PACKAGE);
        } catch (Exception e) {
            System.out.println(ReportMessages.APP_TERMINATE_FAILED.format( e.getMessage()));
        } finally {
            try {
                current.quit();
            } catch (Exception e) {
                System.out.println(ReportMessages.APPIUM_SESSION_CLOSE_FAILED.format(e.getMessage()));
            } finally {
                driver.remove();
            }
        }
    }
}
