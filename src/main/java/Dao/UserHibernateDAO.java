package Dao;

import User.User;
import UserDAOInterface.UserDAOInterface;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDAOInterface {
    private static UserHibernateDAO userHibernateDAO;

    public static UserHibernateDAO getUserHibernateDAO() {
        if (userHibernateDAO == null) {
            userHibernateDAO = new UserHibernateDAO();
        }
        return userHibernateDAO;
    }

    private static Session session = DBHelperHibernateDAO.getSessionFactory().openSession();

    public UserHibernateDAO() {
    }

    @Override
    public void addUser(User user) throws SQLException {
        session.save(user);
        session.flush();
        session.clear();
        session.close();
    }

    @Override
    public User getUserById(int id) {
        User user = (User) session.get(User.class, id);
        session.flush();
        session.clear();
        session.close();
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List< User > getAllUsers() {
        session.getTransaction().begin();
        List< User > list = session.createQuery(" FROM User").list();
        session.getTransaction().commit();
        session.flush();
        session.clear();
        session.close();
        return list;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        session.delete(session.get(User.class, id));
        session.flush();
        session.clear();
        session.close();
        return true;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        session.update(user);
        session.flush();
        session.clear();
        session.close();
        return true;
    }

    @Override
    public String getRoleByNamePassword(String name, String password){
        User user = (User) session.byNaturalId(User.class)
                .using("name", name)
                .using("password", password)
                .load();
        session.flush();
        session.clear();
        session.close();
        return user.getRole();
    }

    @Override
    public boolean isUserExist(String name, String password){
        User user = (User) session.byNaturalId(User.class)
                .using("name", name)
                .using("password", password)
                .load();
        session.flush();
        session.clear();
        session.close();
        return user != null;
    }
}
