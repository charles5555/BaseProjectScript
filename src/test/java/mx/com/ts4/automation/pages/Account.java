package mx.com.ts4.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class Account extends Prospecto{

    public void marcar() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        Thread.sleep(10000);

        WebElement btn = driver.findElement(By.xpath("//span[text()='Marcar Estado como completado(a)']"));
        jse.executeScript("arguments[0].click()", btn);

        Thread.sleep(10000);

        WebElement btn2 = driver.findElement(By.xpath("//span[text()='Marcar Estado como completado(a)']"));
        jse.executeScript("arguments[0].click()", btn2);

        Thread.sleep(5000);

        clic(By.xpath("//button[text()='Convertir']"));

        Thread.sleep(5000);
        clic(By.xpath("//a[text()='Compañía de Alejandro-']"));

    }

}
