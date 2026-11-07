package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackDriver;
import drivers.LocalDriver;
import helpers.AllureAttachments;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    private static final String DEVICE_HOST = System.getProperty("deviceHost", "local");

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = switch (DEVICE_HOST) {
            case "local" -> LocalDriver.class.getName();
            case "browserstack" -> BrowserstackDriver.class.getName();
            default -> throw new IllegalArgumentException(
                    "Неизвестный deviceHost: " + DEVICE_HOST + ". Допустимо: local, browserstack");
        };
        Configuration.browserSize = null;
        Configuration.timeout = 30_000;
        Configuration.pageLoadTimeout = -1;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void afterEach() {
        if (!Selenide.webdriver().driver().hasWebDriverStarted()) {
            return;
        }
        String sessionId = Selenide.sessionId().toString();
        AllureAttachments.attachScreenshot();
        closeWebDriver();

        if ("browserstack".equals(DEVICE_HOST)) {
            AllureAttachments.attachVideo(sessionId);
        }
    }
}
