package mx.com.ts4.automation.Runner;

import mx.com.ts4.automation.functions.Functions;
import org.openqa.selenium.By;


public class Main  {
    public static void main(String[] args){
        Functions funciones = new Functions();
        funciones.GoWebPage();
        funciones.sendTextToField(By.id("username"), By.id("password"));
        funciones.ClickButtom(By.id("Login"));

    }
}
