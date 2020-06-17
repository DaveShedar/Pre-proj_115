package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDAOFactory {

    public static String getTypeDAO() {
        Properties property = new Properties();
        try {
            InputStream is = UserDAOFactory.class.getClassLoader().getResourceAsStream("config.properties");
            property.load(is);
            return property.getProperty("daotype");
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл не найден!");
        }
        return null;
    }
}








