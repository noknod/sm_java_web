package ru.mail.noknod.asteroids.db.dao;


import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ru.mail.noknod.asteroids.db.datasets.UserDataSet;


/**
 * Created by kua on 29.06.16.
 */
public class UserDAO {
    public UserDAO(Session session) {
        _session = session;
    }

    public UserDataSet get(long id) throws HibernateException {
        return (UserDataSet) _session.get(UserDataSet.class, id);
    }

    public long getUserId(String name) throws HibernateException {
        Criteria criteria = _session.createCriteria(UserDataSet.class);
        return (
                (UserDataSet) criteria
                        .add(Restrictions.eq("name", name))
                        .uniqueResult())
                .getId();
    }

    public long insertUser(String name, String password) throws HibernateException {
        return (Long) _session.save(new UserDataSet(name, password));
    }

    private Session _session;
}
