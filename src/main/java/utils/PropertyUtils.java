package utils;

import exception.PropertyLoadException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
    private PropertyUtils(){}
    public static Properties propertyLoader(String filePath) throws FileNotFoundException {
        Properties properties = new Properties();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            throw new FileNotFoundException("Properties file not found at: " + filePath);
        }
        try {
            properties.load(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new PropertyLoadException("Failed to load the Properties file: " + filePath);
        }
        return properties;
    }
}
