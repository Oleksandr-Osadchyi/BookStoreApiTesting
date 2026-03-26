package testengine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

public class PropertyHelper {

    private static String propertiesPath = "src/test/testengine/test.properties";

    public static void writeDataToPropertyFile(Map<String, String> map) {
        try {
            Properties properties = new Properties();

            for(String key : map.keySet()) {
                properties.setProperty(key, map.get(key));
            }

            File file = new File("test.properties");
            FileOutputStream fileOut = new FileOutputStream(file);
            properties.store(fileOut, "Manage test properties");
            fileOut.close();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String readFromPropertyFile(String fileName, String key) {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = Files.newInputStream(Paths.get("test.properties"));
            prop.load(input);
            if(prop.containsKey(key)) {
                return prop.getProperty(key);
            } else {
                throw new NoSuchElementException("There is no key ["  + key + "] in property file");
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
