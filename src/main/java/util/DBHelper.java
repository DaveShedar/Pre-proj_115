package util;

import Dao.UserHibernateDAO;
import Dao.UserJDBCDAO;
import UserDAOInterface.UserDAOInterface;

public class DBHelper {

    private static UserDAOInterface typeDB = getDB();

    public static UserDAOInterface getDBHelper() {
        return typeDB;
    }

    public static UserDAOInterface getDB() {
        if (UserDAOFactory.getTypeDAO().equals("UserHibernateDAO")) {
            return getConfiguration();
        } else {
            return getConnection();
        }
    }

    public static UserJDBCDAO getConnection() {
        return UserJDBCDAO.getUserDAO();
    }

    public static UserHibernateDAO getConfiguration() {
        return UserHibernateDAO.getUserHibernateDAO();
    }
}
