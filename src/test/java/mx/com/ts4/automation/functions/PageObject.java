package mx.com.ts4.automation.functions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
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

        //Para Chrome
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        chromeOptions.addArguments("--disable-notifications");
        driver = new ChromeDriver(chromeOptions);

        //Para Firefox
        /*WebDriverManager.firefoxdriver().setup();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("dom.webnotifications.enable",false);
        profile.setPreference("geo.enable",false);
        profile.setPreference("permissions.default.desktop-notification",1);
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(profile);
        driver = new FirefoxDriver(firefoxOptions);*/

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

        //WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        return driver.findElement(locator);
    }

    //Método para seleccionar una opción de un desplegable
    //Requiere 2 parámetros
    //locator1 ---- localizador del desplegable
    //locator2 ---- localizador de la opción a escoger del desplegable

    public void DropDown(By locator1, By locator2){
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement dropDownElement = fluentWaitElement(10, 1, locator1); // Llamada al método fluentWait
            String argument = "arguments[0].scrollIntoView()";
            js.executeScript(argument, dropDownElement);
            dropDownElement.click();
            WebElement dropDownListOption = fluentWaitElement(10, 1, locator2);
            js.executeScript(argument, dropDownListOption);
            dropDownListOption.click();
        }catch (org.openqa.selenium.TimeoutException e1){
            ExceptionTimeOut(e1);
        } catch (org.openqa.selenium.NoSuchElementException e2){
            ExceptionNoElement(e2);
        };
    }

    //Método para seleccionar una opción de un desplegable. Este método se diferencía del anterior porque
    //aquí todas las opciones del desplegable comparten el mismo localizador
    //Requiere 3 parámetros
    //locator1 -------- localizador del desplegable
    //locator2 -------- localizador de las opciones del desplegable
    //elementNumber --- elemento de la lista que corresponde a la opción a seleccionar del desplegable

    public void DropDownList(By locator1, By locator2, int elementNumber){
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement dropDownListElement = fluentWaitElement(10, 1, locator1); // Llamada al método fluentWait
            js.executeScript(argument, dropDownListElement);
            dropDownListElement.click();
            List<WebElement> dropDownList = driver.findElements(locator2);
            dropDownList.get(elementNumber).click();
        }catch (org.openqa.selenium.TimeoutException e1){
            ExceptionTimeOut(e1);
        } catch (org.openqa.selenium.NoSuchElementException e2){
            ExceptionNoElement(e2);
        };
    }

    //Método para dar clic a un elemento web que comparte el mismo localizador con varios elementos web
    //Este método requiere un parámetro
    //locator ----- es el localizador de los elementos web a localizar
    //Nota: requiere arreglar el .get() porque no es muy general

    public void ClickElementFromList(By locator, int elementNumber){
        try {
            WebElement button = fluentWaitElement(10, 1, locator); // Llamada al método fluentWait
            List<WebElement> dropDownList = driver.findElements(locator);
            dropDownList.get(elementNumber).click();
        }catch (org.openqa.selenium.TimeoutException e1){
            ExceptionTimeOut(e1);
        } catch (org.openqa.selenium.NoSuchElementException e2){
            ExceptionNoElement(e2);
        };
    }

    public void IngresoDatos(By input, String dato) {
        String info = dato;
        try {

            WebElement campo = fluentWaitElement(10, 1, input); // Llamada al método fluentWait

            if (campo != null) {
                campo.sendKeys(info);
            } else {
                System.out.println("No se encontró el botón con el localizador proporcionado.");
            }
        }catch (org.openqa.selenium.TimeoutException e1){
            ExceptionTimeOut(e1);
        } catch (org.openqa.selenium.NoSuchElementException e2){
            ExceptionNoElement(e2);
        };
    }

    //Hace un clic a un elemento web. Este método requiere solo un parámetro
    //locator ---- es el localizador del elemento web
    //
    public void clic(By locator){
        try {
            WebElement button = fluentWaitElement(20, 1, locator);// Llamada al método fluentWait
            if (button != null) {
                button.click();
            } else {
                System.out.println("No se encontró el botón con el localizador proporcionado.");
            }
        }catch (org.openqa.selenium.TimeoutException e1){
            ExceptionTimeOut(e1);
        } catch (org.openqa.selenium.NoSuchElementException e2){
            ExceptionNoElement(e2);
        };


    }

    public  void forceClic(By locator){
        try {
            WebElement button = fluentWaitElement(20, 1, locator);// Llamada al método fluentWait
            if (button != null) {
                JavascriptExecutor jse = (JavascriptExecutor)driver;
                jse.executeScript("arguments[0].click()", button);
                button.click();
            } else {
                System.out.println("No se encontró el botón con el localizador proporcionado.");
            }
        }catch (org.openqa.selenium.TimeoutException e1){
            ExceptionTimeOut(e1);
        } catch (org.openqa.selenium.NoSuchElementException e2){
            ExceptionNoElement(e2);
        }catch (org.openqa.selenium.ElementClickInterceptedException e3){

        };
    }

    public void ExceptionTimeOut(Exception e){
        //Capturar screenshot
        driver.quit();
        System.out.println("\\u001B[El elemento que buscabas no se encontró, posibles causas:");
        System.out.println("1.- La ruta de localización del elemento web es incorrecta, verifica en tu navegador esta ruta si es correcta");
        System.out.println("2.- Si tu ruta es correcta y usas una estrategia diferente de Xpath, cambia la estrategia a Xpath para evitar estos problemas");
        System.out.println("3.- Si el elemento no aparece en pantalla o simplemente lo anterior es correcto y persiste el problema, contacta al QA automation más cercano");
        e.printStackTrace();
        System.exit(1);
    }

    public void ExceptionNoElement(Exception e){
        //Capturar screenshot
        driver.quit();
        System.out.println("El elemento que buscabas sí se encontró, pero no se le puede dar clic, esto se puede deber a diferentes causas:");
        System.out.println("1.- No está el objeto directamente en pantalla, un scroll a la pantalla para que el objeto sea visible sería una solución");
        System.out.println("2.- Generar una espera en el proceso para que el elemento pueda interactuar con un clic");
        System.out.println("3.- Forzar a que el elemento se le pueda dar clic");
        System.out.println("Para todos estos casos, contacte al QA automation más cercano");
        e.printStackTrace();
        System.exit(1);
    }
    public void ExceptionInterceptedClic(Exception e){
        driver.quit();
        System.out.println("Al intentar hacer clic en el elemento que seleccionaste, un elemento externo fue el que recibió el clic.");
        System.out.println("Verificar qué elemento recibió el clic. En caso de no hacerlo, contacte al QA automation más cercano");
        e.printStackTrace();
        System.exit(1);
    }

}
