package web.tinkoff.page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Predicate;

// Страница 'Платежи'
public class PaymentPage extends BasePage {

    public PaymentPage() {
        //страница становится открытой, когда цвет вкладки становится серым
        wait.until(driver1 -> {
            WebElement element = driver.findElement(By.xpath(".//ul[@id='mainMenu']//span[text()[contains(.,'Платежи')]]/.."));
            return element.getCssValue("color").equals("rgba(51, 51, 51, 1)");
        });
    }

    //Выбираем Вид платежа payment
    //TODO: проверять параметр payment
    public void choosePayment(String payment) {
        wait.until(driver1 -> {
            driver.findElement(By.xpath(".//ul[@class = 'ui-menu ui-menu_icons']//a[@title='" + payment + "']")).click();
            return driver.findElement(By.xpath(".//div[@class ='_3WKsv iaS10 _2BdTz PmKYk _1Q8Id']")).isDisplayed();
        });
    }

    //Ввод значения provider в строку поиска
    public PaymentPage inputInSearch(String provider) {
        driver.findElement(By.xpath(".//label[@class='_3kceY']/input")).sendKeys(provider);
        return this;
    }

    //Проверка, что provider, находится на i-ой позиции в списке поиска
    public PaymentPage checkIndexProvider(int i, String provider) {
        String providerOnI = driver.findElements(By.xpath(".//div[@class = '_3WKsv _3KH0m HhZgX _2wQkF']"))
                .get(i - 1).getText();
        Assert.assertTrue("Поставщика услуг " + provider + "нет на " + i + "месте", provider.equals(providerOnI));
        return this;
    }

    //Выбор provider из списка поиска
    public PaymentOnProviderPage clickOnProvider(String provider) {
        driver.findElement(By.xpath(".//div[@class = '_3WKsv _3KH0m HhZgX _2wQkF' " +
                "and text()[contains(.,'" + provider + "')]]")).click();
        return new PaymentOnProviderPage();
    }
}
