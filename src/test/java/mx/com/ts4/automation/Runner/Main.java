package mx.com.ts4.automation.Runner;

import io.github.bonigarcia.wdm.WebDriverManager;
import mx.com.ts4.automation.functions.Functions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {
    public static void main(String[] args){

        Functions funciones = new Functions();
        funciones.GoWebPage("https://totalcybersec--tcsqa.sandbox.lightning.force.com/lightning/o/Lead/list?filterName=Recent");
        funciones.sendTextToField(30, 5, By.id("username"),"cmedel@tcs.mx.qa");
        funciones.sendTextToField(30,5,By.id("password"),"Doctorhouse1");
        funciones.ClickButtom(30, 5, By.id("Login"));

    }
}
