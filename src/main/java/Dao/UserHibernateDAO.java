package Dao;

import User.User;
import UserDAOInterface.UserDAOInterface;
import org.hibernate.Session;
import util.DBHelperHibernateDAO;
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

    private static Session session;

    public static Session getSession() {
        if (session == null) {
            session = DBHelperHibernateDAO.getSessionFactory().openSession();
        }
        return session;
    }

    public UserHibernateDAO() {
    }

    @Override
    public void addUser(User user) throws SQLException {
        getSession().save(user);
        getSession().flush();
        getSession().clear();
    }

    @Override
    public User getUserById(int id) {
        User user = (User) getSession().get(User.class, id);
        getSession().flush();
        getSession().clear();
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List< User > getAllUsers() {
        getSession().getTransaction().begin();
        List< User > list = getSession().createQuery(" FROM User").list();
        getSession().getTransaction().commit();
        getSession().flush();
        getSession().clear();
        return list;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        getSession().delete(getSession().get(User.class, id));
        getSession().flush();
        getSession().clear();
        return true;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        getSession().update(user);
        getSession().flush();
        getSession().clear();
        return true;
    }

    @Override
    public String getRoleByNamePassword(String name, String password){
        User user = (User) session.byNaturalId(User.class)
                .using("name", name)
                .using("password", password)
                .load();
        getSession().flush();
        getSession().clear();
        return user.getRole();
    }

    @Override
    public boolean isUserExist(String name, String password){
        User user = (User) session.byNaturalId(User.class)
                .using("name", name)
                .using("password", password)
                .load();
        getSession().flush();
        getSession().clear();
        return user != null;
    }
}
