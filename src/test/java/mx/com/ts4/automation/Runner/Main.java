package mx.com.ts4.automation.Runner;

import mx.com.ts4.automation.functions.Functions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class Main  {
    public static void main(String[] args) throws InterruptedException {
        Functions funciones = new Functions();
        funciones.GoWebPage();
        funciones.sendTextToField(By.id("username"), By.id("password"));
        funciones.ClickElement(By.id("Login"));
        funciones.ClickElement(By.cssSelector("div[title='Nuevo']"));
        funciones.ClickElement(By.xpath("//span[text()='Siguiente']"));
        

    }
}
