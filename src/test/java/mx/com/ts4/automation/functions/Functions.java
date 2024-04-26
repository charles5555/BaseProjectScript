package mx.com.ts4.automation.functions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.function.Function;


public class Functions {
    protected PropertyReader propertyReader = PropertyReader.getPropertyReader();
    private final PropertyReader reader = PropertyReader.getPropertyReader();

    @FindBy(xpath = "//*[@id=\"registered-customer\"]")
    private  WebElement loginContainer;

    @FindBy (id = "username")
    private  WebElement email;

    @FindBy (id = "password")
    private  WebElement password;

    @FindBy (xpath = "//*[@id=\"Login\"]")
    private  WebElement button;


    String customerEmail = propertyReader.getProperty("user.email");
    String customerPassword = propertyReader.getProperty("user.password");

    private static WebDriver driver;
    public Functions() {

        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        chromeOptions.addArguments("--disable-notifications");
        driver = new ChromeDriver(chromeOptions);
    }
    //Ir a una página web
    public void GoWebPage(){
        driver.get(reader.getProperty("url"));
    }

    //Métodos para agregar texto a los campos (varía dependiendo de localizador que se ocupe), los parámetros
    //quee usa son los siguientes: duration, cycleTime, id, text.
    //duration es el tiempo de duración total de la espera
    //cycleTime es el tiempo del ciclo que va a ir corrobarando que el objeto sea interactuable o visible
    //id es el id del elemento
    //text es el texto que se va a mandar al campo de texto

    public void sendTextToField(By email, By password) {
        System.out.println("aqui");

        WebElement correo = fluentWait(60,5, email); // Llamada al método fluentWait
        WebElement contrasena = fluentWait(60,5, password); // Llamada al método fluentWait

        if (correo != null) {
            System.out.println("entro if 1");
            correo.sendKeys(customerEmail);
            contrasena.sendKeys(customerPassword);
            System.out.println("entro1");
        } else {
            System.out.println("No se encontró el botón con el localizador proporcionado.");
        }

    }

    public void ClickButtom(By boton){
        WebElement button = fluentWait(60,5, boton); // Llamada al método fluentWait
        if (button != null) {
            button.click();
        } else {
            System.out.println("No se encontró el botón con el localizador proporcionado.");
        }
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
}
