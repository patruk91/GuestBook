import controller.Controller;
import server.ServerGuestBook;


public class GuestBook {
    public static void main(String[] args) {
        final String URL = "jdbc:postgresql://localhost:5432/guestbook";
        final String USER = "pl";
        final String PASSWORD = "postgres";
        ServerGuestBook serverGuestBook = new ServerGuestBook();


        Controller controller = new Controller(URL, USER, PASSWORD, serverGuestBook);
        controller.run();
    }
}
