import dao.sql.ConnectionPool;

import java.sql.SQLException;

public class GuestBook {
    public static void main(String[] args) {
        final String URL = "jdbc:postgresql://192.168.10.171:5432/guestbook";
        final String USER = "pl";
        final String PASSWORD = "postgres";

        ConnectionPool connectionPool = null;
        try {
            connectionPool = ConnectionPool.create(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
