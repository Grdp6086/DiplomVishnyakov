package page;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private SelenideElement heading = $("[class='heading heading_size_l heading_theme_alfa-on-white']");
    private SelenideElement headingPaymentForm = $("[class='heading heading_size_m heading_theme_alfa-on-white']");
    private SelenideElement buyForm = $(byText("Купить"));
    private SelenideElement buyByCreditForm = $(byText("Купить в кредит"));

    public MainPage(){
        heading.should(visible);
    }


    public PaymentFormBuyPage payWithDebitCard(){
        buyForm.click();
        headingPaymentForm.shouldHave(exactText("Оплата по карте"));
        return new PaymentFormBuyPage();
    }
    public PaymentFormBuyByCreditPage payWithCreditCard(){
        buyByCreditForm.click();
        headingPaymentForm.shouldHave(exactText("Кредит по данным карты"));
        return new PaymentFormBuyByCreditPage();
    }
}
