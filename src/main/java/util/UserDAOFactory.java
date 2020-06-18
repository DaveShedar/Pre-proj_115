package util;

import Dao.UserHibernateDAO;
import Dao.UserJDBCDAO;
import UserDAOInterface.UserDAOInterface;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDAOFactory {

    private final static UserDAOInterface typeDB = typeDAO();

    public static UserDAOInterface getTypeDB() {
        return typeDB;
    }

    public static UserDAOInterface typeDAO() {
        Properties property = new Properties();
        try {
            InputStream is = UserDAOFactory.class.getClassLoader().getResourceAsStream("config.properties");
            property.load(is);
            if(property.getProperty("daotype").equals(("UserHibernateDAO"))){
                return getConfiguration();
            } else {
                return getConnection();
            }
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл не найден!");
        }
        return null;
    }

    public static UserJDBCDAO getConnection() {
        return UserJDBCDAO.getUserDAO();
    }

    public static UserHibernateDAO getConfiguration() {
        return UserHibernateDAO.getUserHibernateDAO();
    }
}








