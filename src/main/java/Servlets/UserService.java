package Servlets;

import User.User;
import util.DBHelper;

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

    public void addUser(User user) throws SQLException {
        DBHelper.getDBHelper().addUser(user);
    }

    public boolean updateUser(User user) throws SQLException {
        return DBHelper.getDBHelper().updateUser(user);
    }

    public User getUserById(int id) {
        return DBHelper.getDBHelper().getUserById(id);
    }

    public List< User > getAllUsers() {
        return DBHelper.getDBHelper().getAllUsers();
    }

    public boolean deleteUser(int id) throws SQLException {
        return DBHelper.getDBHelper().deleteUser(id);
    }

    public String getRoleByNamePassword(String name, String password){
        return DBHelper.getDBHelper().getRoleByNamePassword(name, password);
    }

//    protected User getUserByNamePassword(String name, String password){
//        return DBHelper.getDBHelper().getUserByNamePassword(name, password);
//    }

    public boolean isUserExist(String name, String password){
        return DBHelper.getDBHelper().isUserExist(name, password);
    }
}
