package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class HandleRequest {

    public void notFound(HttpExchange exchange) throws IOException {
        String answ = "Not Found";
        exchange.sendResponseHeaders(404, answ.length());
        try (OutputStream os = exchange.getResponseBody()) { os.write(answ.getBytes()); }
    }

    public void badRequest(HttpExchange exchange) throws IOException {
        String answ = "Bad Request";
        exchange.sendResponseHeaders(400, answ.length());
        try (OutputStream os = exchange.getResponseBody()) { os.write(answ.getBytes()); }
    }

    public String getJsonString(HttpExchange exchange) throws IOException {
        InputStreamReader isr =  new InputStreamReader(exchange.getRequestBody(),"utf-8");
        BufferedReader br = new BufferedReader(isr);
        String jsonString = br.readLine();
        return jsonString;
    }

    public HttpResponse mPost(String url, String json) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder().uri(new URI(url + "/api/game/start"))
            .setHeader("Content-Type", "application/json")
            .setHeader("Accept", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(String.format(json)))
            .build();
        HttpResponse<String> answ = client.send(req, HttpResponse.BodyHandlers.ofString());
        return answ;
    }

    public void sendResponse(HttpExchange exchange, String response, boolean header) throws IOException {
        if (header)
            exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(202, response.length());
        exchange.getResponseHeaders().set("Accept", "application/json");
        try (OutputStream os = exchange.getResponseBody()) { os.write(response.getBytes()); }
    }
}
