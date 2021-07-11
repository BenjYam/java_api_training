
package fr.lernejo.navy_battle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;

import static java.lang.System.exit;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.net.http.HttpResponse;
import java.util.HashMap;


public class HandleServer {
    private final int port;

    private final HashMap<String, String> enemy = new HashMap<>();

    private final HandleRequest req = new HandleRequest();
    private final ObjectMapper object = new ObjectMapper();


    public HandleServer(int port) throws IOException {
        InetSocketAddress add = new InetSocketAddress("localhost", port);
        HttpServer server = HttpServer.create(add, 0);
        server.setExecutor(Executors.newSingleThreadExecutor());
        server.createContext("/ping", new Ping());
        server.start();
        this.port = port;
    }
    public void start(int port, String url) throws IOException, URISyntaxException, InterruptedException {
        ServerInformation serv = new ServerInformation(UUID.randomUUID().toString(), "http://localhost:" + port, "May the force be with you");
        HttpResponse<String> answ = new HandleRequest().mPost(url, object.writeValueAsString(serv));
        if (answ.statusCode() == 202) {
            ServerInformation rServ = object.readValue(answ.body(), ServerInformation.class);
        }
    }

    public int getPort() { return this.port; }


}
