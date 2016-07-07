package ru.mail.noknod.asteroids.user;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kua on 27.06.16.
 */
public class AccountService {
    public AccountService() {
        _loginToProfile = new HashMap<>();
        _sessionIdToProfile = new HashMap<>();
    }

    public void addNewUser(UserProfile userProfile) {
        _loginToProfile.put(userProfile.getLogin(), userProfile);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        _sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        _sessionIdToProfile.remove(sessionId);
    }

    public UserProfile getUserByLogin(String login) {
        return _loginToProfile.get(login);
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return _sessionIdToProfile.get(sessionId);
    }

    private final Map<String, UserProfile> _loginToProfile;
    private final Map<String, UserProfile> _sessionIdToProfile;
}
