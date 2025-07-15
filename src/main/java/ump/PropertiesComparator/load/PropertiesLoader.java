package ump.PropertiesComparator.load;

import java.io.FileInputStream;
import java.util.*;
import java.util.Properties;

public class PropertiesLoader {
    public Map<String, String> loadProperties(String filePath) {
        if (!filePath.endsWith(".properties")) {
            throw new IllegalArgumentException("Verifier le type de fichier :" + filePath);
        }
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            props.load(fis);
        } catch (Exception e) {
            throw new IllegalArgumentException("Impossible de charger le fichier: " + filePath);
        }
        Map<String, String> result = new HashMap<>();
        for (String key : props.stringPropertyNames()) {
            result.put(key, props.getProperty(key));
        }
        return result;
    }
}
