package dev.t3rry327.hypix.api.nex1.handlers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.UUID;

import com.google.gson.Gson;

import dev.t3rry327.hypix.api.nex1.objects.Profile;
import dev.t3rry327.hypix.api.nex1.objects.ProfilesEnum;

public class ThreadedNetworkHandler extends Thread {
    private Socket socket;

    private boolean isRunning = false;
    private DataOutputStream dataOutputStream = null;

    public ThreadedNetworkHandler(Socket socket) {
        this.socket = socket;
    }

    public ThreadedNetworkHandler() {

    }

    public void startServer(Socket socket) {
        isRunning = true;
        this.socket = socket;
        this.run();
        isRunning = false;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            String headerLine = bufferedReader.readLine();

            if (headerLine == null)
                return;

            StringTokenizer tokenizer = new StringTokenizer(headerLine);

            String httpMethod = tokenizer.nextToken();
            String httpQueryString = tokenizer.nextToken();
            String[] path = httpQueryString.substring(1).split("//");
            Gson gson = new Gson();

            if (httpMethod.equals("GET")) {

                if (path[0].equals("player")) {
                    if (path[1].equals("info")) {
                        String playerNick = path[2];

                    } else if (path[1].equals("cosmetics")) {
                        String playerUUID = path[2];

                    } else if (path[1].equals("friends")) {
                        String playerUUID = path[2];

                    }

                } else if (path[0].equals("guild")) {

                } else if (path[0].equals("skyblock")) {
                    if (path[1].equals("profiles")) { // Consulta de los perfiles de skyblock
                        if (path[2].equals("new")) { // Consulta de los perfiles de skyblock
                            String[] values = path[3].split("&");
                            Profile perfil = new Profile(UUID.randomUUID().toString(), ProfilesEnum.randomProfile(),
                                    values[0], new String[] { values[1] });
                            // TODO: Agregar a la db
                            sendResponse(200, gson.toJson(perfil));

                        } else if (path[2].equals("addcoop")) { // Consulta de los perfiles de skyblock
                            String[] values = httpQueryString.substring(8).split("&");
                            // TODO: Agregar coop teammate a la db

                        } else {
                            String playerUUID = path[2];
                            Profile perfil = new Profile("", ProfilesEnum.randomProfile(), "", new String[] { "" });// TODO:Recuperar el profile completo
                            sendResponse(200, gson.toJson(perfil));
                        }

                    } else if (path[1].equals("profile")) {
                        if (path[2].equals("bank")) {

                        } else if (path[2].equals("id")) {

                        }

                    } else if (path[1].equals("bazaar")) {

                    } else if (path[1].equals("auction")) {

                    } else if (path[1].equals("instance")) {

                    }
                } else {
                    sendResponse(400, "Invalid Endpoint");
                }

            } else if (httpMethod.equals("POST")) {
                // TODO post handler
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendResponse(int statusCode, String responseString) throws Exception {

        String NEW_LINE = "\r\n";
        String statusLine;

        if (statusCode == 200)
            statusLine = "HTTP/1.1 200 OK";
        else
            statusLine = "HTTP/1.1 400 BAD REQUEST";

        statusLine += NEW_LINE;

        dataOutputStream.writeBytes(statusLine);
        dataOutputStream.writeBytes("Connection: close" + NEW_LINE);
        dataOutputStream.writeBytes(NEW_LINE);
        dataOutputStream.writeBytes(responseString);
        dataOutputStream.close();
    }
}
