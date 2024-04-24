package mx.com.ts4.automation.functions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class Functions {
    private WebDriver driver;
    public Functions() {

        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        chromeOptions.addArguments("--disable-notifications");
        driver = new ChromeDriver(chromeOptions);



    }


    //Ir a una página web
    public void GoWebPage(String url){
        driver.get(url);
    }

    private WebElement fluentWait(int duration, int cycleTime, By locator) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(duration))
                .pollingEvery(Duration.ofSeconds(cycleTime))
                .ignoring(NoSuchElementException.class);

        return wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
    }

    //Métodos para agregar texto a los campos (varía dependiendo de localizador que se ocupe), los parámetros
    //quee usa son los siguientes: duration, cycleTime, id, text.
    //duration es el tiempo de duración total de la espera
    //cycleTime es el tiempo del ciclo que va a ir corrobarando que el objeto sea interactuable o visible
    //id es el id del elemento
    //text es el texto que se va a mandar al campo de texto
    public void sendTextToField(int duration, int cycleTime, By locator, String text) {
        WebElement element = fluentWait(duration,cycleTime,locator); // Llamada al método fluentWait
        if (element != null) {
            element.sendKeys(text);
        } else {
            System.out.println("No se encontró el campo de texto con el localizador proporcionado.");
        }
    }

    public void ClickButtom(int duration, int cycleTime, By locator){
        WebElement element = fluentWait(duration,cycleTime,locator); // Llamada al método fluentWait
        if (element != null) {
            element.click();
        } else {
            System.out.println("No se encontró el botón con el localizador proporcionado.");
        }
    }

}
