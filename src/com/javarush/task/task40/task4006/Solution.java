package com.javarush.task.task40.task4006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {
        int port = url.getDefaultPort();
        String host = url.getHost();

        try (Socket socket = new Socket(host, port);
             PrintStream printStream = new PrintStream(socket.getOutputStream());
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

//            sending a request GET
            printStream.println("GET " + url.getPath() + "HTTP/1.1");
            printStream.println("Host: " + url.getHost());
            printStream.println("User-Agent: " + "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 YaBrowser/23.1.0.2947 Yowser/2.5 Safari/537.36");
            printStream.println("Connection: close");

//            getting the response
            String responseLine;
            while ((responseLine = bufferedReader.readLine()) != null) {
                System.out.println(responseLine);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
