package ru.mail.noknod.asteroids.db.datasets;


import javax.persistence.*;
import java.io.Serializable;


/**
 * Created by kua on 29.06.16.
 */
@Entity
@Table(name = "user")
public class UserDataSet implements Serializable {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_name", unique = true, updatable = false)
    private String name;

    @Column(name = "password", updatable = false)
    private String password;

    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public UserDataSet() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public UserDataSet(long id, String name, String password) {
        _setuser(id, name, password);
    }

    @SuppressWarnings("UnusedDeclaration")
    public UserDataSet(String name, String password) {
        _setuser(-1, name, password);
    }

    @SuppressWarnings("UnusedDeclaration")
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    private void _setuser(long id, String name, String password) {
        this.setId(id);
        this.setName(name);
        this.setPassword(password);
    }
    private static final long serialVersionUID = -8706689714326132798L;
}
