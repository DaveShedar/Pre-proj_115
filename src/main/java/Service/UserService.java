package Service;

import User.User;
import UserDAOInterface.UserDAOInterface;
import util.UserDAOFactory;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService userService;

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    private static UserDAOInterface typeDb = UserDAOFactory.getTypeDB();

    public void addUser(User user) throws SQLException {
        typeDb.addUser(user);
    }

    public boolean updateUser(User user) throws SQLException {
        return typeDb.updateUser(user);
    }

    public User getUserById(int id) {
        return typeDb.getUserById(id);
    }

    public List< User > getAllUsers() {
        return typeDb.getAllUsers();
    }

    public boolean deleteUser(int id) throws SQLException {
        return typeDb.deleteUser(id);
    }

    public String getRoleByNamePassword(String name, String password){
        return typeDb.getRoleByNamePassword(name, password);
    }

    public boolean isUserExist(String name, String password){
        return typeDb.isUserExist(name, password);
    }
}
