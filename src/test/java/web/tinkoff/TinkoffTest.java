package web.tinkoff;

import org.junit.*;
import web.tinkoff.page.*;
import java.util.concurrent.TimeUnit;

import static web.tinkoff.page.BasePage.driver;

public class TinkoffTest {

    @Before
    public void setUp() {
        BasePage.setUp();
        driver.get("https://www.tinkoff.ru");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void utilitiesPaymentTest() {
        new MainPage().chooseCategoryInHeader("Платежи");
        new PaymentPage().choosePayment("Коммунальные платежи");

        UtilitiesPaymentPage utilitiesPaymentPage = new UtilitiesPaymentPage().checkRegion("Москв");
        String provider = utilitiesPaymentPage.chooseProvider(1);

        PaymentOnProviderPage paymentOnProviderPage = new PaymentOnProviderPage().navigateTo("Оплатить ЖКУ");
        paymentOnProviderPage.checkNotValidValueRequiredField();

        new MainPage().chooseCategoryInHeader("Платежи");
        new PaymentPage().inputInSearch(provider)
                .checkIndexProvider(1, provider)
                .clickOnProvider(provider);

        new MainPage().chooseCategoryInHeader("Платежи");
        new PaymentPage().choosePayment("Коммунальные платежи");

        new UtilitiesPaymentPage().checkRegion("Санкт-Петербург").checkLackOFProvider(provider);
    }


    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
