package screens;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.xpath;

public class ArticleScreen {

    private SelenideElement toolbarTitle(String title) {
        return $(xpath("//android.widget.TextView[@text='" + title + "']"));
    }

    @Step("Проверить, что открыта статья «{expected}»")
    public ArticleScreen checkTitle(String expected) {
        toolbarTitle(expected).shouldBe(visible);
        return this;
    }
}