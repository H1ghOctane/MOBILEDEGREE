package drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static config.ConfigManager.BS_CONFIG;

public class BrowserstackDriver implements WebDriverProvider {

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        Map<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("userName", BS_CONFIG.username());
        bstackOptions.put("accessKey", BS_CONFIG.accessKey());
        bstackOptions.put("projectName", "Wikipedia Mobile Tests");
        bstackOptions.put("buildName", "wikipedia-android");
        bstackOptions.put("sessionName", "Android tests");

        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("bstack:options", bstackOptions);
        caps.setCapability("platformName", "android");
        caps.setCapability("appium:deviceName", BS_CONFIG.deviceName());
        caps.setCapability("appium:platformVersion", BS_CONFIG.osVersion());
        caps.setCapability("appium:app", BS_CONFIG.appUrl());

        try {
            return new AndroidDriver(URI.create(BS_CONFIG.remoteUrl()).toURL(), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
