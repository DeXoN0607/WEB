package ru.netology.order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderCardTest extends BaseTest {

    @Test
    void shouldSubmitFormSuccessfully() {
        OrderPage page = new OrderPage(driver);

        page.setName("Иванов Иван");
        page.setPhone("+79991234567");
        page.acceptAgreement();
        page.submit();

        String expected =
                "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";

        Assertions.assertEquals(expected, page.getSuccessText());
    }

    @Test
    void shouldShowErrorIfNameEmpty() {
        OrderPage page = new OrderPage(driver);

        page.setPhone("+79991234567");
        page.acceptAgreement();
        page.submit();

        Assertions.assertEquals(
                "Поле обязательно для заполнения",
                page.getNameErrorText()
        );
    }

    @Test
    void shouldShowErrorIfPhoneInvalid() {
        OrderPage page = new OrderPage(driver);

        page.setName("Иванов Иван");
        page.setPhone("12345");
        page.acceptAgreement();
        page.submit();

        Assertions.assertEquals(
                "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.",
                page.getPhoneErrorText()
        );
    }

    @Test
    void shouldShowErrorIfNameInvalid() {
        OrderPage page = new OrderPage(driver);

        page.setName("Ivanov Ivan");
        page.setPhone("+79991234567");
        page.acceptAgreement();
        page.submit();

        Assertions.assertEquals(
                "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.",
                page.getNameErrorText()
        );
    }

    @Test
    void shouldShowErrorIfAgreementNotChecked() {
        OrderPage page = new OrderPage(driver);

        page.setName("Иванов Иван");
        page.setPhone("+79991234567");
        page.submit();

        Assertions.assertTrue(page.isAgreementInvalid());
    }
}