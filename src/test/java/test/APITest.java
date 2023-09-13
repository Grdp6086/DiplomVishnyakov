package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.MainPage;
import page.PaymentFormBuyByCreditPage;
import page.PaymentFormBuyPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class APITest {
    private MainPage mainPage;
    private PaymentFormBuyPage paymentFormBuyPage;
    private PaymentFormBuyByCreditPage paymentFormBuyByCreditPage;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    @BeforeEach
    public void setup(){
        mainPage = open("http://localhost:8080/", MainPage.class);
    }


    @Test
    public void shouldAddPaymentIDInOrderEntry() {
        paymentFormBuyPage = mainPage.payWithDebitCard();
        var cardNumber = DataHelper.getFirstCardInfo();
        var month = DataHelper.getGenerateMonth(1);
        var year = DataHelper.generateYear(1);
        var owner = DataHelper.generateOwner("EN");
        var cvc = DataHelper.generateCVCCode(3);
        paymentFormBuyPage.filledForm(cardNumber, month, year, owner, cvc);
        sleep(3000);
        var expected = SQLHelper.getDebitPaymentID();
        var actual = SQLHelper.getDebitOrderEntryId();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldDonTAddPaymentIDInOrderEntryStatusDeclined(){
        paymentFormBuyPage = mainPage.payWithDebitCard();
        var cardNumber = DataHelper.getSecondCardInfo();
        var month = DataHelper.getGenerateMonth(1);
        var year = DataHelper.generateYear(1);
        var owner = DataHelper.generateOwner("EN");
        var cvc = DataHelper.generateCVCCode(3);
        paymentFormBuyPage.filledForm(cardNumber, month, year, owner, cvc);
        sleep(3000);
        var expected = SQLHelper.getDebitPaymentID();
        var actual = SQLHelper.getDebitOrderEntryId();
        assertNotEquals(expected, actual);
    }

    @Test
    public void shouldAddCreditInOrderEntry(){
        paymentFormBuyByCreditPage = mainPage.payWithCreditCard();
        var cardNumber = DataHelper.getFirstCardInfo();
        var month = DataHelper.getGenerateMonth(1);
        var year = DataHelper.generateYear(1);
        var owner = DataHelper.generateOwner("EN");
        var cvc = DataHelper.generateCVCCode(3);
        paymentFormBuyByCreditPage.filledForm(cardNumber, month, year, owner, cvc);
        sleep(3000);
        var expected = SQLHelper.getCreditRequestReEntryId();
        var actual = SQLHelper.getCreditOrderEntryId();
        assertEquals(expected, actual);
    }


    @Test
    public void shouldDonTAddCreditInOrderEntryStatusDeclined(){
        paymentFormBuyByCreditPage = mainPage.payWithCreditCard();
        var cardNumber = DataHelper.getSecondCardInfo();
        var month = DataHelper.getGenerateMonth(1);
        var year = DataHelper.generateYear(1);
        var owner = DataHelper.generateOwner("EN");
        var cvc = DataHelper.generateCVCCode(3);
        paymentFormBuyByCreditPage.filledForm(cardNumber, month, year, owner, cvc);
        sleep(3000);
        var expected = SQLHelper.getCreditRequestReEntryId();
        var actual = SQLHelper.getCreditOrderEntryId();
        assertNotEquals(expected, actual);
    }

}
