package fr.lernejo.navy_battle;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.UUID;

class Sgame implements HttpHandler {
    private final HandleServer server;
    private final HandleRequest req = new HandleRequest();

    private final ObjectMapper object = new ObjectMapper();

    public Sgame(HandleServer handleServer) {
        this.server = handleServer;
    }

    public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("POST"))
            req.notFound(exchange);
        try {
            ServerInformation rServ = object.readValue(req.getJsonString(exchange), ServerInformation.class);
            this.server.setEnemy("id", rServ.getId());
            this.server.setEnemy("url", rServ.getUrl());
            ServerInformation myServ = new ServerInformation(UUID.randomUUID().toString(), "http://localhost:" + server.getPort(), "may the force be with you");
            String answ = object.writeValueAsString(myServ);
            req.sendAnsw(exchange, answ, true);
        } catch (Exception e) {
            req.badRequest(exchange);
        }
    }
}
