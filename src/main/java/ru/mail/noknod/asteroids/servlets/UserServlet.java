package ru.mail.noknod.asteroids.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ru.mail.noknod.asteroids.templater.PageGenerator;
import ru.mail.noknod.asteroids.user.AccountService;

/**
 * Created by kua on 26.06.16.
 */
public class UserServlet extends HttpServlet {
    public UserServlet(AccountService accountService) {
        super();
        _accountService = accountService;
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        // todo: get public user profile
    }

    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        // todo: sign up
    }

    @Override
    public void doPut(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        // todo: change profile
    }

    @Override
    public void doDelete(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        // todo: unregister
    }

    private final AccountService _accountService;
}
