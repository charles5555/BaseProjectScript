package mx.com.ts4.automation.Runner;

import mx.com.ts4.automation.pages.Account;
import mx.com.ts4.automation.pages.Prospecto;
import org.openqa.selenium.By;

import java.time.format.DateTimeFormatter;


public class Main  {
    public static void main(String[] args) throws InterruptedException {

        Account prospecto = new Account();
        //Ir a una página web
        prospecto.GoWebPage();
        //Loguearse
        prospecto.CompletarLogin();
        //Ir a prospectos y llenar el formulario
        prospecto.Prospecto();

        prospecto.marcar();
    }
}
