package mx.com.ts4.automation.Runner;

import mx.com.ts4.automation.functions.Functions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class Main  {
    public static void main(String[] args) throws InterruptedException {
        Functions funciones = new Functions();

        //Ir a una página web
        funciones.GoWebPage();

        //Loguearse
        funciones.sendTextToField(By.id("username"), By.id("password"));
        funciones.ClickElement(By.id("Login"));

        //Ir a prospectos
        funciones.ClickElement(By.cssSelector("div[title='Nuevo']"));
        funciones.ClickElementFromList(By.xpath("//span[@class='slds-radio--faux']"),1);
        funciones.ClickElement(By.xpath("//span[text()='Siguiente']"));

        //Llenar formulario de prospectos
        funciones.SendTextToField(By.xpath("//input[@name='firstName']"),"Alejandro");
        funciones.SendTextToField(By.xpath("//input[@name='lastName']"),"Alejandro");
        funciones.SendTextToField(By.xpath("//input[@name='Email']"),"Ale@gmail.com");
        funciones.SendTextToField(By.xpath("//input[@name='Phone']"),"1111111111");
        funciones.SendTextToField(By.xpath("//input[@name='Company']"),"Compañía de Alejandro");
        funciones.SendTextToField(By.xpath("//input[@name='TCS_Calle__c']"),"calle");
        funciones.SendTextToField(By.xpath("//input[@name='TCS_NumExterior__c']"),"445656");
        funciones.SendTextToField(By.xpath("//input[@name='TCS_CodigoPostal__c']"),"45665");
        funciones.SendTextToField(By.xpath("//input[@name='TCS_Ciudad__c']"),"Ciudad");
        funciones.SendTextToField(By.xpath("//input[@name='TCS_Estado__c']"),"Estado");
        funciones.SendTextToField(By.xpath("//input[@name='TCS_Pais__c']"),"País");
        funciones.SendTextToField(By.xpath("//input[@name='TCS_Municipio__c']"),"Municipio");
        funciones.DropDown(By.xpath("//button[contains(@aria-label,'Tipo de cliente -')]"),By.xpath("//span[@title='Directo']"));
        funciones.DropDown(By.xpath("//button[contains(@aria-label,'Sector -')]"),By.xpath("//span[@title='Gobierno Federal']"));
        funciones.DropDownList(By.xpath("//button[contains(@aria-label,'Vertical -')]"),By.xpath("//span[@title='Financiero']"),1);
    }
}
