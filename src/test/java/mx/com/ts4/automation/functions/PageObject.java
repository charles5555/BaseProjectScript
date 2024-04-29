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

public class PageObject {

    public static WebDriver driver;
    public final PropertyReader reader = PropertyReader.getPropertyReader();
    public String argument = "arguments[0].scrollIntoView()";

    public PageObject() {

        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        chromeOptions.addArguments("--disable-notifications");
        driver = new ChromeDriver(chromeOptions);

    }

    //Ir a una página web
    public void GoWebPage(){
        driver.get(reader.getProperty("url"));
        driver.manage().window().maximize();
    }

    //Método para las esperas fluidas, requiere 3 parámetros
    //duration ----- duración del ciclo
    //cycleTime ---  duración del ciclo para probar
    //locator -----  es el localizador del elemento web
    protected WebElement fluentWaitElement(int duration, int cycleTime, By locator) {
        System.out.println("Iniciando FluentWait para el elemento: " + locator);
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(duration))
                .pollingEvery(Duration.ofSeconds(cycleTime))
                .ignoring(NoSuchElementException.class);

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    //Método para seleccionar una opción de un desplegable
    //Requiere 2 parámetros
    //locator1 ---- localizador del desplegable
    //locator2 ---- localizador de la opción a escoger del desplegable

    public void DropDown(By locator1, By locator2){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement dropDownElement = fluentWaitElement(10,1, locator1); // Llamada al método fluentWait
        String argument = "arguments[0].scrollIntoView()";
        js.executeScript(argument,dropDownElement);
        dropDownElement.click();
        WebElement dropDownListOption = fluentWaitElement(10,1,locator2);
        js.executeScript(argument,dropDownListOption);
        dropDownListOption.click();
    }

    //Método para seleccionar una opción de un desplegable. Este método se diferencía del anterior porque
    //aquí todas las opciones del desplegable comparten el mismo localizador
    //Requiere 3 parámetros
    //locator1 -------- localizador del desplegable
    //locator2 -------- localizador de las opciones del desplegable
    //elementNumber --- elemento de la lista que corresponde a la opción a seleccionar del desplegable

    public void DropDownList(By locator1, By locator2, int elementNumber){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement dropDownListElement = fluentWaitElement(10,1, locator1); // Llamada al método fluentWait
        js.executeScript(argument,dropDownListElement);
        dropDownListElement.click();
        List<WebElement> dropDownList = driver.findElements(locator2);
        dropDownList.get(elementNumber).click();
    }

    //Método para dar clic a un elemento web que comparte el mismo localizador con varios elementos web
    //Este método requiere un parámetro
    //locator ----- es el localizador de los elementos web a localizar
    //Nota: requiere arreglar el .get() porque no es muy general

    public void ClickElementFromList(By locator, int elementNumber){
        WebElement button = fluentWaitElement(10,1, locator); // Llamada al método fluentWait
        List<WebElement> dropDownList = driver.findElements(locator);
        dropDownList.get(elementNumber).click();;
    }

    public void IngresoDatos(By input, String dato) {
        String info = dato;
        WebElement campo = fluentWaitElement(60,5, input); // Llamada al método fluentWait

        if (campo != null) {
            campo.sendKeys(info);
        } else {
            System.out.println("No se encontró el botón con el localizador proporcionado.");
        }
    }

    //Hace un clic a un elemento web. Este método requiere solo un parámetro
    //locator ---- es el localizador del elemento web
    //
    public void clic(By locator){
        WebElement button = fluentWaitElement(13,1, locator); // Llamada al método fluentWait
        if (button != null) {
            button.click();
        } else {
            System.out.println("No se encontró el botón con el localizador proporcionado.");
        }
    }

}
