package server;

import com.sun.net.httpserver.HttpServer;
import model.GuestForm;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ServerGuestBook {

    public void startServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new ServerHandler());
        server.setExecutor(null);
        server.start();

    }
}
