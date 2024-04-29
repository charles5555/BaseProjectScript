package mx.com.ts4.automation.Runner;

import mx.com.ts4.automation.pages.Prospecto;
import org.openqa.selenium.By;


public class Main  {
    public static void main(String[] args) {

        Prospecto prospecto = new Prospecto();
        //Ir a una página web
        prospecto.GoWebPage();
        //Loguearse
        prospecto.CompletarLogin();
        //Ir a prospectos y llenar el formulario
        prospecto.Prospecto();
    }
}
