package UserDAOInterface;

import User.User;
import java.sql.SQLException;
import java.util.List;

public interface UserDAOInterface {

    void addUser(User user) throws SQLException;
    boolean updateUser(User user) throws SQLException;
    User getUserById(int id);
    List< User > getAllUsers();
    boolean deleteUser(int id) throws SQLException;
    String getRoleByNamePassword(String name, String password);
    boolean isUserExist(String name, String password);
}
