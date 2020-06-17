package Dao;

import User.User;
import UserDAOInterface.UserDAOInterface;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJDBCDAO implements UserDAOInterface {

    private static UserJDBCDAO userDAO;

    public static UserJDBCDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserJDBCDAO();
        }
        return userDAO;
    }
    private final Connection connection = getMysqlConnection();

    public void addUser(User user) throws SQLException {
        try (
             PreparedStatement preparedStatement = connection.
                     prepareStatement("INSERT INTO users" + "(name, password) VALUES" + "(?, ?)")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (
             PreparedStatement statement = connection.prepareStatement("update users set name = ?, password = ?, role = ? where id = ?")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.setInt(4, user.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public User getUserById(int id) {
        User user = null;
        try (
             PreparedStatement preparedStatement = connection.
                     prepareStatement("SELECT name, password, role FROM users WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                user = new User(id, name, password, role);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public List< User > getAllUsers() {
        List< User > list = new ArrayList< User >();
        try (
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users")) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                list.add(new User(id, name, password, role));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean usersDeleted;
        try (
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            usersDeleted = preparedStatement.executeUpdate() > 0;
        }
        return usersDeleted;
    }

    public boolean isUserExist(String name, String password){
        User user = null;
        try (PreparedStatement preparedStatement = connection.
                        prepareStatement("SELECT id, role FROM users WHERE name = ? and password = ?")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String role = resultSet.getString("role");
                user = new User(id, name, password, role);
            }

            if(user != null){
                return true;
            }
        } catch (Exception e) {
            System.out.println("_____________________________________________");
            System.out.println("Поймано исключение в Получение роли:" + user);
            System.out.println("_____________________________________________");
        }
        return false;
    }

    public String getRoleByNamePassword (String name, String password){
        String role = null;
        try (
             PreparedStatement preparedStatement = connection.
                     prepareStatement("SELECT role FROM users WHERE name = ? and password = ?")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                role = resultSet.getString("role");
                return role;
            }
        } catch (Exception e) {
            System.out.println("_____________________________________________");
            System.out.println("Поймано исключение в Получение роли:" + role);
            System.out.println("_____________________________________________");
        }
        return "absent";
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("task115?").          //db name
                    append("user=root&").          //login
                    append("password=309201");       //password

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            System.out.println("db is connected!!!");
            return connection;
        } catch (Exception e) {
            System.out.println("Ошибка в создании Connection (ниже StackTrace");
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}