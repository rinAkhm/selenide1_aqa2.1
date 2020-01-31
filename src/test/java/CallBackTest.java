import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.exactText;

public class CallBackTest {

    @Test
    @DisplayName("should Application Approved")
    void shouldApplicationApproved() {
        open("http://localhost:9999");
        SelenideElement form = $("[method='post']");
        form.$("[type='text']").setValue("Ринатов Ринат");
        form.$("[type='tel']").setValue("+79012345678");
        form.$(".checkbox__text").click();
        form.$(".button__text").click();
        $("[data-test-id]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void invalidNameTest() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Rinat Rinatov");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("span.input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. " +
                "Допустимы только русские буквы, пробелы и дефисы.")).
                getCssValue("color: #ff5c5c;");
    }
}
