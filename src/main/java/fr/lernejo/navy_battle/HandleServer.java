package fr.lernejo.navy_battle;


import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import java.util.concurrent.Executors;

public class HandleServer {
    private final int port;


    public HandleServer(int port) throws IOException {
        InetSocketAddress add = new InetSocketAddress("localhost", port);
        HttpServer server = HttpServer.create(add, 0);
        server.setExecutor(Executors.newSingleThreadExecutor());
        server.createContext("/ping", new Ping());
        server.start();
        this.port = port;
    }

}
