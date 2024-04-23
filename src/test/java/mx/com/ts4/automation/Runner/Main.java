package mx.com.ts4.automation.Runner;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {
    public static void main(String[] args){
        System.out.println("HellowWorld");
        WebDriver driver;
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        chromeOptions.addArguments("--disable-notifications");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://cablevisionfbestel--sbqa.sandbox.lightning.force.com/lightning/page/home");
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("cmedel@ts4.mx.qa");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("Pasaporte2019");
        WebElement botonLogin = driver.findElement(By.id("Login"));
        botonLogin.click();
    }
}
