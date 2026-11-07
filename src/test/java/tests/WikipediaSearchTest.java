package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import screens.MainScreen;

@Epic("Wikipedia Android")
@Feature("Поиск статей")
@Owner("H1ghOctane")
@Tag("android")
public class WikipediaSearchTest extends TestBase {

    private final MainScreen mainScreen = new MainScreen();

    @ParameterizedTest(name = "Поиск по запросу «{0}» находит статьи")
    @ValueSource(strings = {"Appium", "Selenium", "Java"})
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Поиск находит статьи")
    void searchReturnsResultsTest(String query) {
        mainScreen.openSearch()
                .search(query)
                .checkResultsFound();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("В результатах поиска есть статья с точным названием")
    void searchResultContainsArticleTest() {
        mainScreen.openSearch()
                .search("Appium")
                .checkResultVisible("Appium");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Статья открывается из результатов поиска")
    void openArticleTest() {
        mainScreen.openSearch()
                .search("Linux")
                .openArticle("Linux")
                .checkTitle("Linux");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Очистка строки поиска убирает результаты")
    void clearSearchTest() {
        mainScreen.openSearch()
                .search("Appium")
                .checkResultsFound()
                .clearSearch()
                .checkNoResults();
    }
}
