package helpers;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.util.Base64;

public class Screenshot {

    private Screenshot() {
    }

    public static void attachScreenshot() {

        try {
            WebDriver driver = WebDriverRunner.getWebDriver();
            String rawBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            if (rawBase64 == null || rawBase64.isBlank()) {
                return;
            }

            if (rawBase64.contains(",")) {rawBase64 =
                        rawBase64.substring(rawBase64.indexOf(",") + 1);
            }

            rawBase64 = rawBase64.replaceAll("\\s", "");
            byte[] screenshotBytes = Base64.getDecoder().decode(rawBase64);
            Allure.addAttachment("Screenshot", "image/png",
                    new ByteArrayInputStream(screenshotBytes), ".png");

            System.out.println("Screenshot attached! Size: " + screenshotBytes.length + " bytes");
        }

        catch (Exception e) {
            System.out.println("Screenshot error: " + e.getMessage());
        }
    }
}