package drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;

import static config.ConfigManager.LOCAL_CONFIG;

public class LocalDriver implements WebDriverProvider {

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName(LOCAL_CONFIG.deviceName())
                .setPlatformVersion(LOCAL_CONFIG.platformVersion())
                .setApp(appPath());

        try {
            return new AndroidDriver(URI.create(LOCAL_CONFIG.appiumUrl()).toURL(), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private String appPath() {
        File app = new File(LOCAL_CONFIG.appPath());
        if (!app.exists()) {
            throw new IllegalStateException("APK не найден: " + app.getAbsolutePath()
                    + ". Как его получить, написано в README");
        }
        return app.getAbsolutePath();
    }
}
