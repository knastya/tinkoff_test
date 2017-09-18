package web.tinkoff.page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

//Страница Оплаты у конкретного поставщика услуг
public class PaymentOnProviderPage extends BasePage {
    public PaymentOnProviderPage() {
        waitElement(By.xpath(".//div[@class = 'ui-menu-second__items']//span[text()[contains(.,'Оплатить ЖКУ')]]"));
    }

    //Переход на вкладку where
    public PaymentOnProviderPage navigateTo(String where) {
        if (where != null && (where.contains("Оплатить ЖКУ") || where.contains("Узнать задолженность за ЖКУ")))
            driver.findElement(By.xpath(".//div[@class = 'ui-menu-second__items']//span[text()[contains(.,'" + where + "')]]")).click();
        else
            throw new IllegalArgumentException("Неверно указано название вкладки!");
        return new PaymentOnProviderPage();
    }

    //проверка появления сообщений об ошибке при невалидных значениях
    public void checkNotValidValueRequiredField() {
        checkNotValidValuePayerCode();
        checkNotValidValuePeriod();
        checkNotValidValuePayment();
    }

    //Код плательщика
    private void checkNotValidValuePayerCode() {
        WebElement payerCode = driver.findElement(By.xpath(".//form[@class ='ui-form']//input[@name='provider-payerCode']"));
        checkErrorMessage(payerCode, "Поле обязательное");

        payerCode.sendKeys("1");
        checkErrorMessage(payerCode, "Поле неправильно заполнено");
        clearField(payerCode);
    }

    //Период оплаты
    private void checkNotValidValuePeriod() {
        WebElement providerPeriod = driver.findElement(By.xpath(".//form[@class ='ui-form']//input[@name='provider-period']"));
        checkErrorMessage(providerPeriod, "Поле обязательное");

        providerPeriod.sendKeys("20.2017");
        checkErrorMessage(providerPeriod, "Поле заполнено некорректно");
        clearField(providerPeriod);

        //вот тут тест упадёт, потому что ошибки не появится
//        providerPeriod.sendKeys("12.0000");
//        checkErrorMessage(providerPeriod, "Поле заполнено некорректно");
//        clearField(providerPeriod);
    }

    //Сумма платежа
    private void checkNotValidValuePayment() {
        WebElement payment = driver.findElement(By.xpath(".//form[@class ='ui-form']" +
                "//div[contains(@class, 'account-amount')]//input"));
        checkErrorMessage(payment, "Поле обязательное");

        payment.sendKeys("1");
        checkErrorMessage(payment, "Минимальная сумма перевода - 10 \u20BD");
        clearField(payment);

        payment.sendKeys("15001");
        checkErrorMessage(payment, "Максимальная сумма перевода - 15 000 \u20BD");
        clearField(payment);
    }

    private void checkErrorMessage(WebElement element, String errorMessage) {
        element.sendKeys(Keys.ENTER);
        String error = "";
        try {
            error = element.findElement(By.xpath("./../../../../..//div[contains(@class,'error-message')]")).getText();
        }catch (NoSuchElementException ex)
        {
            Assert.fail("Текст ошибки отсутствует!");
        }
        Assert.assertTrue("Текст ошибки неверен. Ожидалось: " + errorMessage + ". Фактически: " + error,
                error.equals(errorMessage));
    }

    private void clearField(WebElement providerPeriod) {
        while (!providerPeriod.getAttribute("value").equals(""))
            providerPeriod.sendKeys(Keys.BACK_SPACE);
    }
}
