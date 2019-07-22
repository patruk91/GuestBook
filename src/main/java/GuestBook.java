import controller.Controller;
import dao.IGuestBookDao;
import dao.sql.ConnectionPool;
import dao.sql.GuestBookSQL;
import server.ServerGuestBook;

import java.sql.SQLException;


public class GuestBook {
    public static void main(String[] args) {
        final String URL = "jdbc:postgresql://localhost:5432/guestbook";
        final String USER = "pl";
        final String PASSWORD = "postgres";

        ConnectionPool connectionPool = null;
        try {
            connectionPool = ConnectionPool.create(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        IGuestBookDao guestBookDao = new GuestBookSQL(connectionPool);
        ServerGuestBook serverGuestBook = new ServerGuestBook(guestBookDao);
        Controller controller = new Controller(URL, USER, PASSWORD, serverGuestBook);
        controller.run();
    }
}
