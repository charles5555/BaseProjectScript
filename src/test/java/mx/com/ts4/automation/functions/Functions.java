package mx.com.ts4.automation.functions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;


public class Functions {
    protected PropertyReader propertyReader = PropertyReader.getPropertyReader();
    private final PropertyReader reader = PropertyReader.getPropertyReader();


    private String argument = "arguments[0].scrollIntoView()";


    String customerEmail = propertyReader.getProperty("user.email");
    String customerPassword = propertyReader.getProperty("user.password");

    private static WebDriver driver;
    JavascriptExecutor js = (JavascriptExecutor) driver;
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

        WebElement correo = fluentWaitElement(60,5, email); // Llamada al método fluentWait
        WebElement contrasena = fluentWaitElement(60,5, password); // Llamada al método fluentWait

        if (correo != null) {
            System.out.println("entro if 1");
            correo.sendKeys(customerEmail);
            contrasena.sendKeys(customerPassword);
            System.out.println("entro1");
        } else {
            System.out.println("No se encontró el botón con el localizador proporcionado.");
        }

    }

    public void ClickElement(By boton){
        WebElement button = fluentWaitElement(60,1, boton); // Llamada al método fluentWait
        if (button != null) {
            button.click();
        } else {
            System.out.println("No se encontró el botón con el localizador proporcionado.");
        }
    }

    private WebElement fluentWaitElement(int duration, int cycleTime, By locator) {
        System.out.println("Iniciando FluentWait para el elemento: " + locator);
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(duration))
                .pollingEvery(Duration.ofSeconds(cycleTime))
                .ignoring(NoSuchElementException.class);

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }


    public void DropDown(By locator1, By locator2){
        WebElement dropDownElement = fluentWaitElement(60,5, locator1); // Llamada al método fluentWait
        js.executeScript(argument,dropDownElement);
        dropDownElement.click();
        WebElement dropDownListOption = fluentWaitElement(60,5,locator2);
        js.executeScript(argument,dropDownListOption);
        dropDownListOption.click();
    }

    public void DropDownList(By locator1, By locator2, int elementNumber){
        WebElement dropDownListElement = fluentWaitElement(60,5, locator1); // Llamada al método fluentWait
        js.executeScript(argument,dropDownListElement);
        dropDownListElement.click();
        List<WebElement> dropDownList = driver.findElements(locator2);
        dropDownList.get(elementNumber).click();
    }

    public String ObtainTextFromElement(By locator){
        WebElement dropDownListElement = fluentWaitElement(60,5, locator);
        return dropDownListElement.getText();
    }



}
