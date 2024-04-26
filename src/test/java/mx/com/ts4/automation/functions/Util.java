package mx.com.ts4.automation.functions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

public class Util {
    public static String getEnvironment() {
        return System.getProperty("environment", "automated");
    } //Manda a llamar el ambiente de prueba

}
