package helpers;

import io.restassured.response.Response;

import static config.ConfigManager.BS_CONFIG;
import static io.restassured.RestAssured.given;

public class Browserstack {

    private Browserstack() {
    }

    public static String videoUrl(String sessionId) {

        try {
            String url = String.format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId
            );
            Response response = given().auth().basic(BS_CONFIG.username(),
                    BS_CONFIG.accessKey()).get(url);

            if (response.statusCode() != 200) {
                System.out.println("BrowserStack API returned " + response.statusCode());
                return null;
            }

            return response.jsonPath().getString("automation_session.video_url");

        } catch (Exception e) {

            System.out.println("Video URL error: " + e.getMessage()
            );

            return null;
        }
    }
}