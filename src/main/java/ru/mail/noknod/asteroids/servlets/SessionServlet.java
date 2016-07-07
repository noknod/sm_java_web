package ru.mail.noknod.asteroids.servlets;

import com.google.gson.Gson;
import ru.mail.noknod.asteroids.templater.PageGenerator;
import ru.mail.noknod.asteroids.user.AccountService;
import ru.mail.noknod.asteroids.user.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kua on 26.06.16.
 */
public class SessionServlet extends HttpServlet {
    public SessionServlet(AccountService accountService) {
        super();
        _accountService = accountService;
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        String sessionId = request.getSession().getId();
        UserProfile userProfile = _accountService.getUserBySessionId(sessionId);
        if (userProfile == null) {
            response.setContentType("text/html; charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            Gson gson = new Gson();
            String json = gson.toJson(userProfile);
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().println(json);
            response.setStatus(HttpServletResponse.SC_OK);
        }

        /*
        Map<String, Object> pageVariables = createPageVariables(request);
        pageVariables.put("message", "");
        System.out.println(pageVariables.get("parameters"));
        Map<String, String[]> r = request.getParameterMap();
        StringBuilder result = new StringBuilder();
        for (Map.Entry entry: r.entrySet()) {
            String[] value = (String[]) entry.getValue();
            for (String parameter : value) {
                result.append(parameter);
                result.append(" ");
            }
        }
        pageVariables.put("result", result.toString());
        String q = result.toString().trim();
        System.out.println(result.toString() + "*");
        response.getWriter().println(q);

        response.setStatus(HttpServletResponse.SC_OK);
        */
/*
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(("userId"));

        if (userId == null) {
            userId = userIdGenerator.getAndIncrement();
            session.setAttribute("userId", userId);
        }

        String key = session.toString();
*/
    }

    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null || login.isEmpty() ||
            password.isEmpty()
           ) {
            response.setContentType("text/html; charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile userProfile = _accountService.getUserByLogin(login);
        if (userProfile == null || !userProfile.getPass().equals(password)) {
            response.setContentType("text/html; charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        _accountService.addSession(request.getSession().getId(), userProfile);
        Gson gson = new Gson();
        String json = gson.toJson(userProfile);
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().println(json);
        response.setStatus(HttpServletResponse.SC_OK);
        /*
        Map<String, Object> pageVariables = createPageVariables(request);

        String message = request.getParameter("message");

        response.setContentType("text/html; charset=utf-8");

        if (message == null || message.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }

        pageVariables.put("message", message == null ? "" : message);


        response.getWriter().println(
                PageGenerator.instance().getPage(
                        "page.html", pageVariables
                )
        );
        */
    }
    /*
    private Map<String, Object> createPageVariables(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        String pathInfo = request.getPathInfo();
        pageVariables.put("pathInfo", pathInfo == null ? "*null*" : pathInfo);
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("parameters", request.getParameterMap().toString());
        return pageVariables;
    }
    */

    @Override
    public void doDelete(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        String sessionId = request.getSession().getId();
        UserProfile userProfile = _accountService.getUserBySessionId(sessionId);
        if (userProfile == null) {
            response.setContentType("text/html; charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            _accountService.deleteSession(sessionId);
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().println("Goodbye!");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    private final AccountService _accountService;
}
