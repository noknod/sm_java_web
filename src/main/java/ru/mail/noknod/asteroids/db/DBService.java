package ru.mail.noknod.asteroids.db;


import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import ru.mail.noknod.asteroids.db.dao.UserDAO;
import ru.mail.noknod.asteroids.db.datasets.UserDataSet;


/**
 * Created by kua on 29.06.16.
 */
public class DBService {
    public DBService() {
        Configuration conf = _getConfiguration();
        _sessionFactory = _createSessionFactory(conf);
    }

    public UserDataSet getUser(long id) throws DBException {
        try (Session session = _sessionFactory.openSession()) {
            UserDAO dao = new UserDAO(session);
            return dao.get(id);
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public long addUser(String name, String password) throws DBException {
        try (Session session = _sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            UserDAO dao = new UserDAO(session);
            long id = dao.insertUser(name, password);
            transaction.commit();
            return id;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public String getConnectionInfo() {
        // http://stackoverflow.com/questions/10607196/how-to-get-database-metadata-from-entity-manager
        String result = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Map<String, Object> properties = _sessionFactory.getProperties();
            //System.out.println("Driver: " + connection.getMetaData().getDriverName());
            stringBuilder.append("\n");
            stringBuilder.append("Driver: ");
            stringBuilder.append(properties.get("hibernate.connection.driver_class"));
            //System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            stringBuilder.append("\n");
            stringBuilder.append("DB name: ");
            stringBuilder.append(properties.get("hibernate.connection.url"));
            //System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            stringBuilder.append("\n");
            stringBuilder.append("DB version: ");
            //stringBuilder.append(properties.get(""));
            //System.out.println("Autocommit: " + connection.getAutoCommit());
            stringBuilder.append("\n");
            stringBuilder.append("Autocommit: ");
            stringBuilder.append(properties.get("hibernate.connection.autocommit"));
            result = stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private Configuration _getConfiguration() {
        Configuration conf = new Configuration();

        conf.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        conf.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        conf.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/sm_web_java");
        conf.setProperty("hibernate.connection.username", "sm_web_user");
        conf.setProperty("hibernate.connection.password", "mailru");
        conf.setProperty("hibernate_show_sql", _hibernate_show_sql);
        conf.setProperty("hibernate.hbm2ddl.auto", _hibernate_hbm3ddl_auto);
        conf.setProperty("hibernate.connection.autocommit", "false");

        conf.addAnnotatedClass(UserDataSet.class);

        return conf;
    }

    private SessionFactory _createSessionFactory(Configuration conf) {
        StandardServiceRegistryBuilder builder =
                new StandardServiceRegistryBuilder();
        builder.applySettings(conf.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return conf.buildSessionFactory(serviceRegistry);
    }

    private static final String _hibernate_show_sql = "true";
    private static final String _hibernate_hbm3ddl_auto = "validate";
    private final SessionFactory _sessionFactory;
}
