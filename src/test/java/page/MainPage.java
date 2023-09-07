package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private SelenideElement heading = $("[class='App_appContainer__3jRx1']");
    private SelenideElement buyForm = $(byText("Купить"));
    private SelenideElement buyByCreditForm = $(byText("Купить в кредит"));

    public MainPage(){
        heading.should(visible);
    }

    public PaymentFormBuyPage payWithDebitCard(){
        buyForm.click();
        heading.shouldHave(exactText("Оплата по карте"));
        return new PaymentFormBuyPage();
    }
    public PaymentFormBuyByCreditPage payWithCreditCard(){
        buyByCreditForm.click();
        heading.shouldHave(exactText("Кредит по данным карты"));
        return new PaymentFormBuyByCreditPage();
    }
}
