package mx.com.ts4.automation.pages;

import org.openqa.selenium.By;

public class Prospecto extends Login {

    String firstName = propertyReader.getProperty("prospecto.firstName");
    String lastName = propertyReader.getProperty("prospecto.lastName");
    String Email = propertyReader.getProperty("prospecto.Email");
    String Phone = propertyReader.getProperty("prospecto.Phone");
    String Company = propertyReader.getProperty("prospecto.Company");
    String TCS_Calle__c = propertyReader.getProperty("prospecto.TCS_Calle__c");
    String TCS_NumExterior__c = propertyReader.getProperty("prospecto.TCS_NumExterior__c");
    String TCS_CodigoPostal__c = propertyReader.getProperty("prospecto.TCS_CodigoPostal__c");
    String TCS_Ciudad__c = propertyReader.getProperty("prospecto.TCS_Ciudad__c");
    String TCS_Estado__c = propertyReader.getProperty("prospecto.TCS_Estado__c");
    String TCS_Pais__c = propertyReader.getProperty("prospecto.TCS_Pais__c");
    String TCS_Municipio__c = propertyReader.getProperty("prospecto.TCS_Municipio__c");

    public void Prospecto() {

        clic(By.cssSelector("div[title='Nuevo']"));
        ClickElementFromList(By.xpath("//span[@class='slds-radio--faux']"),1);
        clic(By.xpath("//span[text()='Siguiente']"));

        IngresoDatos(By.xpath("//input[@name='firstName']"),firstName);
        IngresoDatos(By.xpath("//input[@name='lastName']"),lastName);
        IngresoDatos(By.xpath("//input[@name='Email']"),Email);
        IngresoDatos(By.xpath("//input[@name='Phone']"),Phone);
        IngresoDatos(By.xpath("//input[@name='Company']"),Company);
        IngresoDatos(By.xpath("//input[@name='TCS_Calle__c']"),TCS_Calle__c);
        IngresoDatos(By.xpath("//input[@name='TCS_NumExterior__c']"),TCS_NumExterior__c);
        IngresoDatos(By.xpath("//input[@name='TCS_CodigoPostal__c']"),TCS_CodigoPostal__c);
        IngresoDatos(By.xpath("//input[@name='TCS_Ciudad__c']"),TCS_Ciudad__c);
        IngresoDatos(By.xpath("//input[@name='TCS_Estado__c']"),TCS_Estado__c);
        IngresoDatos(By.xpath("//input[@name='TCS_Pais__c']"),TCS_Pais__c);
        IngresoDatos(By.xpath("//input[@name='TCS_Municipio__c']"),TCS_Municipio__c);

        DropDown(By.xpath("//button[contains(@aria-label,'Tipo de cliente -')]"),By.xpath("//span[@title='Directo']"));
        DropDown(By.xpath("//button[contains(@aria-label,'Sector -')]"),By.xpath("//span[@title='Gobierno Federal']"));
        DropDownList(By.xpath("//button[contains(@aria-label,'Vertical -')]"),By.xpath("//span[@title='Financiero']"),1);

        clic(By.xpath("//button[text()='Guardar']"));
    }

}
