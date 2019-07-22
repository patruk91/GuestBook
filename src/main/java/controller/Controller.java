package controller;

import dao.sql.ConnectionPool;
import server.ServerGuestBook;

import java.io.IOException;
import java.sql.SQLException;

public class Controller {
    private String url;
    private String user;
    private String password;
    private ServerGuestBook serverGuestBook;

    public Controller(String url, String user, String password, ServerGuestBook serverGuestBook) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.serverGuestBook = serverGuestBook;
    }

    public void run() {


        try {
            serverGuestBook.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
