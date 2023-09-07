package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PaymentFormBuyByCreditPage {
    private SelenideElement cardNumberForm = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthForm = $("[placeholder='08']");
    private SelenideElement yearForm = $("[placeholder='22']");
    private SelenideElement ownerForm = $(byText("Владелец"));
    private SelenideElement cvcForm = $("[placeholder='999']");
    private SelenideElement continueButton = $(byText("Продолжить"));
    private SelenideElement succsesfullNotification = $(byText("Операция одобрена Банком."));
    private SelenideElement errorNotification = $(byText("Ошибка! Банк отказал в проведении операции."));
    private SelenideElement emptyField = $ (byText("Поле обязательно для заполнения"));
    private SelenideElement wrongFormat = $(byText("Неверный формат"));
    private SelenideElement wrongCardDate = $ (byText("Неверно указан срок действия карты"));
    private SelenideElement cardExpired = $(byText("Истёк срок действия карты"));

    public void filledForm(DataHelper.CardInfo cardInfo, DataHelper.MonthInfo monthInfo, DataHelper.YearInfo yearInfo, DataHelper.OwnerInfo ownerInfo, DataHelper.CvcInfo cvcInfo){
        cardNumberForm.setValue(cardInfo.getCardNumber());
        monthForm.setValue(monthInfo.getMonth());
        yearForm.setValue(yearInfo.getYear());
        ownerForm.setValue(ownerInfo.getOwner());
        cvcForm.setValue(cvcInfo.getCvc());
        continueButton.click();

    }

}
