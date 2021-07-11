package fr.lernejo.navy_battle;

import java.io.IOException;
import java.net.URISyntaxException;

public class Launcher {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        if (args.length == 2) {
            HandleServer server = new HandleServer(Integer.parseInt(args[0]));
            server.start(Integer.parseInt(args[0]), args[1]);
        }
        else if (args.length == 1) {
            new HandleServer(Integer.parseInt(args[0]));

        }
    }
}
