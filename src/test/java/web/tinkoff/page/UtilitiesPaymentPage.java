package web.tinkoff.page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import web.tinkoff.form.RegionForm;

//Страница 'Коммунальные платежи'
public class UtilitiesPaymentPage extends BasePage {
    public UtilitiesPaymentPage() {
        waitElement(By.xpath(".//div[@class='_2VJN1']//div[text()[contains(.,'Коммунальные платежи')]]"));
    }

    //Проверка региона mustRegion, при несоотвествии выбор mustRegion
    public UtilitiesPaymentPage checkRegion(String mustRegion) {
        By pathToRegion = By.xpath(".//span[@class = 'ui-link payment-page__title_inner']");
        WebElement regionElement = driver.findElement(pathToRegion);
        if (!regionElement.getText().equals(mustRegion)) {
            waitElement(pathToRegion);
            regionElement.click();
            new RegionForm().chooseRegion(mustRegion);
        }
        return new UtilitiesPaymentPage();
    }

    //Выбираем numProvider - го поставщика услуг из списка. Возвращаем его имя.
    public String chooseProvider(int numProvider) {
        WebElement service = driver.findElement(By.xpath(".//section[@class ='_3KqzB']/ul/li[" + numProvider + "]"));
        String nameOfService = service.findElement(By.xpath("./span/a")).getAttribute("title");
        service.findElement(By.xpath("./span/a")).click();
        return nameOfService;
    }

    //Проверка отсутствия provider
    public void checkLackOFProvider(String provider) {
        try {
            driver.findElement(By.xpath(".//section[@class ='_3KqzB']/ul/li//span[text()[contains(.,'" + provider + "')]]"));
            Assert.fail("Поставщик услуг найден на странице!");
        } catch (NoSuchElementException ex) {
            Assert.assertTrue(true);
        }
    }
}
