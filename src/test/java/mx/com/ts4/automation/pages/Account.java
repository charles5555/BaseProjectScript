package mx.com.ts4.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class Account extends Prospecto{

    public void marcar() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        Thread.sleep(10000);
        forceClic(By.xpath("//span[text()='Marcar Estado como completado(a)']"));

        Thread.sleep(5000);

        clic(By.xpath("//button[text()='Convertir']"));

        //Thread.sleep(5000);
        //clic(By.xpath("//a[text()='Compañía de Alejandro-']"));

    }

}
