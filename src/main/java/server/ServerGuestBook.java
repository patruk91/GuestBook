package server;

import com.sun.net.httpserver.HttpServer;
import dao.IGuestBookDao;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ServerGuestBook {
    private IGuestBookDao guestBookDao;

    public ServerGuestBook(IGuestBookDao guestBookDao) {
        this.guestBookDao = guestBookDao;
    }

    public void startServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new GuestBookHandler(guestBookDao));
        server.setExecutor(null);
        server.start();

    }
}
