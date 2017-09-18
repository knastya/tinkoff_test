package web.tinkoff.page;

import org.openqa.selenium.By;

//Главная страница
public class MainPage extends BasePage {

    public MainPage() {
        By pathToPictureTinkoff = By.xpath(".//span[@data-reactid = '76']");
        waitElement(pathToPictureTinkoff);
    }

    // Карты, Вклады, Платежи, Инвестиции, Ипотека, Кредит, Бонусы, Ещё -> Страхование, Путешествия
    public void chooseCategoryInHeader(String category) {
        switch (category) {
            case "Карты":
            case "Вклады":
            case "Платежи":
            case "Инвестиции":
            case "Ипотека":
            case "Кредит":
            case "Бонусы":
                String pathToCategory = ".//ul[@id='mainMenu']//span[text()[contains(.,'" + category + "')]]";
                driver.findElement(By.xpath(pathToCategory)).click();
                break;
            case "Страхование":
            case "Путешествия":
                throw new UnsupportedOperationException("Выбор данной категории пока не реализован");
            default:
                throw new IllegalArgumentException("Указанное значение '" + category + "' недопустимо");
        }
    }
}
