package mx.com.ts4.automation.pages;

import mx.com.ts4.automation.functions.PageObject;
import mx.com.ts4.automation.functions.PropertyReader;
import org.openqa.selenium.By;


public class Login extends PageObject {

    protected PropertyReader propertyReader = PropertyReader.getPropertyReader();

    String customerEmail = propertyReader.getProperty("user.email");
    String customerPassword = propertyReader.getProperty("user.password");

    //Métodos para agregar texto a los campos (varía dependiendo de localizador que se ocupe), los parámetros
    //quee usa son los siguientes: duration, cycleTime, id, text.
    //duration es el tiempo de duración total de la espera
    //cycleTime es el tiempo del ciclo que va a ir corrobarando que el objeto sea interactuable o visible
    //id es el id del elemento
    //text es el texto que se va a mandar al campo de texto

    public void CompletarLogin() {
        IngresoDatos(By.id("username"),customerEmail);
        IngresoDatos(By.id("password"),customerPassword);
        clic(By.id("Login"));
    }
}
