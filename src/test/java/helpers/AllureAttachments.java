package helpers;

import io.qameta.allure.Allure;
import io.restassured.response.Response;

import java.io.ByteArrayInputStream;

import static config.ConfigManager.BS_CONFIG;
import static io.restassured.RestAssured.given;

public class AllureAttachments {

    private AllureAttachments() {
    }

    public static void attachVideo(String sessionId) {

        try {
            String videoUrl = Browserstack.videoUrl(sessionId);

            if (videoUrl == null || videoUrl.isBlank()) {
                System.out.println("Video url not found. Skip video attachment.");
                return;
            }

            byte[] video = null;

            int maxAttempts = 10;
            int waitMs = 15000;

            for (int i = 0; i < maxAttempts; i++) {
                try {
                    Response response = given().auth().basic(BS_CONFIG.username(),
                            BS_CONFIG.accessKey()).get(videoUrl);
                    String contentType = response.getContentType();
                    byte[] body = response.asByteArray();

                    if (response.statusCode() == 200
                            && contentType != null
                            && contentType.contains("video")
                            && body.length > 10240) {
                        video = body;
                        System.out.println("Video downloaded successfully! Size: " + video.length + " bytes"
                        );
                        break;
                    }
                }
                catch (Exception e) {
                    System.out.println("Attempt " + (i + 1) + " error: " + e.getMessage());
                }
                Thread.sleep(waitMs);
            }

            if (video != null) {
                Allure.addAttachment("Video", "video/mp4", new ByteArrayInputStream(video), ".mp4"
                );
            }
            else {
                System.out.println("Could not download video.");
            }
        } catch (Exception e) {
            System.out.println("Error in attachVideo: " + e.getMessage());
        }
    }

    public static void attachScreenshot() {
        Screenshot.attachScreenshot();
    }
}