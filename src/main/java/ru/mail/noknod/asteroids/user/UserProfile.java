package ru.mail.noknod.asteroids.user;

/**
 * Created by kua on 27.06.16.
 */
public class UserProfile {
    public UserProfile(String login) {
        _login = login;
        _password = login;
        _email = login;
    };

    public UserProfile(String login, String password, String email) {
        _login = login;
        _password = password;
        _email = email;
    };

    public String getLogin() { return _login; }

    public String getPass() { return _password; }

    public String getEmail() { return _email; }

    private final String _login;
    private final String _password;
    private final String _email;
}
