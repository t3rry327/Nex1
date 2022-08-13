package dev.t3rry327.hypix.api.nex1;

import dev.t3rry327.hypix.api.nex1.database.MongoDBConnection;
import dev.t3rry327.hypix.api.nex1.handlers.ThreadedNetworkHandler;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Nex1 {

    public static int poolThreadSize = 200;


    public static void main(String[] args) throws Exception {
        MongoDBConnection.setupConnection();
        startAPIServer();
    }


    public static void startAPIServer() throws Exception {
        List<ThreadedNetworkHandler> networkHandlers = new ArrayList<>(poolThreadSize);
        for (int i = 0; i < poolThreadSize; i++) {
            networkHandlers.add(new ThreadedNetworkHandler());
        }
        int ciclo = 0;
        try (ServerSocket server = new ServerSocket(5000, 2, InetAddress.getByName("0.0.0.0"))) {
            while (true) {

                Socket connected = server.accept();

                if (networkHandlers.get(ciclo).isRunning()) {
                    new ThreadedNetworkHandler(connected).start();
                } else {
                    networkHandlers.get(ciclo).startServer(connected);
                }

                if (ciclo < poolThreadSize - 1) {
                    ciclo++;
                } else {
                    ciclo = 0;
                }

            }
        }
    }
}
