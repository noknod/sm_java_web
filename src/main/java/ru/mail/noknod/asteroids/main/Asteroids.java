package ru.mail.noknod.asteroids.main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.mail.noknod.asteroids.db.datasets.UserDataSet;
import ru.mail.noknod.asteroids.db.DBException;
import ru.mail.noknod.asteroids.db.DBService;
import ru.mail.noknod.asteroids.servlets.SessionServlet;
import ru.mail.noknod.asteroids.servlets.SignInServlet;
import ru.mail.noknod.asteroids.servlets.SignUpServlet;
import ru.mail.noknod.asteroids.servlets.UserServlet;
import ru.mail.noknod.asteroids.user.AccountService;
import ru.mail.noknod.asteroids.user.UserProfile;


/**
 * Created by kua on 26.06.16.
 */
public class Asteroids {
    public static void main(String[] args) throws Exception {
        DBService dbService = new DBService();
        System.out.println(dbService.getConnectionInfo());
/*
        try {
            long userId = dbService.addUser("admin", "admin");
            System.out.println("Added user id: " + userId);

            UserDataSet dataSet = dbService.getUser(userId);
            System.out.println("User data set: " + dataSet);
        } catch (DBException e) {
            e.printStackTrace();
        }
*/
///*
        AccountService accountService = new AccountService();

        //accountService.addNewUser(new UserProfile("admin"));
        //accountService.addNewUser(new UserProfile("test"));

        ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS
        );
        context.addServlet(
                new ServletHolder(new UserServlet(accountService)),
                "/api/v1/users"
        );
        context.addServlet(
                new ServletHolder(new SessionServlet(accountService)),
                "/api/v1/sessions"
        );

        context.addServlet(
                new ServletHolder(new SignUpServlet(accountService)),
                "/signup"
        );

        context.addServlet(
                new ServletHolder(new SignInServlet(accountService)),
                "/signin"
        );

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("public_html");

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(
                new Handler[] {resourceHandler, context}
        );

        Server server = new Server(8080);
        server.setHandler(handlerList);

        server.start();
        System.out.println("Server started");
        server.join();
//*/
    }
}
