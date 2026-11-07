package screens;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.accessibilityId;

public class MainScreen {

    private final SelenideElement searchBar = $(accessibilityId("Search Wikipedia"));

    @Step("Открыть поиск")
    public SearchScreen openSearch() {
        searchBar.click();
        return new SearchScreen();
    }
}
