package mx.com.ts4.automation.functions;

import mx.com.ts4.automation.functions.Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertyReader {
    private static final PropertyReader propertyReader = new PropertyReader();
    private final Properties properties = new Properties();
    private final Logger logger = Logger.getLogger(PropertyReader.class.toString());

    //Si no se encontro un archivo mostrara el error y encontro las retorna
    private PropertyReader() {
        try {
            loadProperties();
        } catch (IOException e) {
            logger.severe("Unable to load execution configuration file.");
        }
    }

    public static PropertyReader getPropertyReader() {
        return propertyReader;
    }

    //Carga las propiedades de la ruta definida
    private void loadProperties() throws IOException {
        String execEnvironment = Util.getEnvironment();

        FileInputStream inputFile = new FileInputStream("properties/environment.properties");
        properties.load(inputFile);

        inputFile = new FileInputStream("properties/testdata.properties");
        properties.load(inputFile);

        String homeURL = properties.getProperty(execEnvironment);
        logger.info("URL to be used: "+ homeURL);
        properties.setProperty("url", homeURL);
    }

    //Obtiene las propiedades
    public String getProperty(String property) {
        return readProperty(property);
    }

    //Verifica si tiene una clave
    public boolean hasProperty(String key) {
        return properties.containsKey(key);
    }

    //Lee las propiedades
    private String readProperty(String key) {
        String value = properties.getProperty(key);

        if (!hasProperty(key)) {
            throw new RuntimeException("Property '" + key + "' is not defined in properties file");
        }

        return value;
    }
}
