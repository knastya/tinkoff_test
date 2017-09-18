package web.tinkoff.form;

import org.openqa.selenium.By;
import web.tinkoff.page.BasePage;

//Форма с выбором регионов
public class RegionForm extends BasePage {

    public RegionForm() {
        waitElement(By.xpath(".//h3[text()[contains(.,'Платежи в')]]"));
    }

    public void chooseRegion(String region) {
        driver.findElement(By.xpath(".//div[@class = 'ui-regions__item']/span[text()[contains(.,'" + region + "')]]")).click();
    }

}
