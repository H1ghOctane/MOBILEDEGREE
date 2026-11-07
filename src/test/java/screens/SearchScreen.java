package screens;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.id;

public class SearchScreen {

    private final SelenideElement searchInput = $(id("org.wikipedia.alpha:id/search_src_text"));
    private final SelenideElement clearButton = $(id("org.wikipedia.alpha:id/search_close_btn"));
    private final ElementsCollection resultTitles = $$(id("org.wikipedia.alpha:id/page_list_item_title"));

    @Step("Ввести поисковый запрос «{query}»")
    public SearchScreen search(String query) {
        searchInput.sendKeys(query);
        return this;
    }

    @Step("Проверить, что найдены статьи")
    public SearchScreen checkResultsFound() {
        resultTitles.shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("Проверить, что в результатах есть статья «{title}»")
    public SearchScreen checkResultVisible(String title) {
        resultTitles.findBy(text(title)).shouldBe(visible);
        return this;
    }

    @Step("Открыть статью «{title}»")
    public ArticleScreen openArticle(String title) {
        resultTitles.findBy(text(title)).click();
        return new ArticleScreen();
    }

    @Step("Очистить строку поиска")
    public SearchScreen clearSearch() {
        clearButton.click();
        return this;
    }

    @Step("Проверить, что результатов нет")
    public SearchScreen checkNoResults() {
        resultTitles.shouldHave(size(0));
        return this;
    }
}
