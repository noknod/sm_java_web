package ru.mail.noknod.asteroids.servlets;

import ru.mail.noknod.asteroids.user.AccountService;
import ru.mail.noknod.asteroids.user.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kua on 26.06.16.
 */
public class SignUpServlet extends HttpServlet {
    public SignUpServlet(AccountService accountService) {
        super();
        _accountService = accountService;
    }

    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        // todo: sign up
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null || login.isEmpty() ||
                password.isEmpty()
                ) {
            response.setContentType("text/html; charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        _accountService.addNewUser(new UserProfile(login, password, "email"));
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private final AccountService _accountService;
}
