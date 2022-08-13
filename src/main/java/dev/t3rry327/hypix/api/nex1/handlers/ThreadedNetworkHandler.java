package dev.t3rry327.hypix.api.nex1.handlers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

public class ThreadedNetworkHandler extends Thread{
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

            if (headerLine == null) return;

            StringTokenizer tokenizer = new StringTokenizer(headerLine);

            String httpMethod = tokenizer.nextToken();
            String httpQueryString = tokenizer.nextToken();


            if (httpMethod.equals("GET")) {

                if (httpQueryString.startsWith("/player")) {
                    String player = httpQueryString.substring(7);
                    //TODO player controller

                } else if (httpQueryString.startsWith("/Sumar")) {


                } else {
                    sendResponse(400, "Invalid Endpoint");
                }

            } else if (httpMethod.equals("POST")) {
                //TODO post handler
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void sendResponse(int statusCode, String responseString) throws Exception {

        String NEW_LINE = "\r\n";
        String statusLine;

        if (statusCode == 200) statusLine = "HTTP/1.1 200 OK";
        else statusLine = "HTTP/1.1 400 BAD REQUEST";

        statusLine += NEW_LINE;

        dataOutputStream.writeBytes(statusLine);
        dataOutputStream.writeBytes("Connection: close" + NEW_LINE);
        dataOutputStream.writeBytes(NEW_LINE);
        dataOutputStream.writeBytes(responseString);
        dataOutputStream.close();
    }
}
