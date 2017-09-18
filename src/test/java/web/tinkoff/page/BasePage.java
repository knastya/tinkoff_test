package web.tinkoff.page;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

import static java.util.concurrent.TimeUnit.SECONDS;

public class BasePage {
    public static WebDriver driver;
    public static Wait wait;

    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "src\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new FluentWait<>(driver)
                .withTimeout(10, SECONDS)
                .pollingEvery(2, SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class)
                .ignoring(WebDriverException.class);
    }

    protected void waitElement(By pathToElementWait) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(pathToElementWait)));
    }
}
