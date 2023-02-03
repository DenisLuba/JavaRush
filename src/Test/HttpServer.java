package Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public static void main(String[] args) throws Throwable {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket socket = serverSocket.accept();
            System.err.println("Client accepted");
            new Thread(new SocketProcessor(socket)).start();
        }
    }

    private static class SocketProcessor implements Runnable {
        private Socket socket;
        private InputStream inputStream;
        private OutputStream outputStream;

        private SocketProcessor(Socket socket) throws Throwable {
            this.socket = socket;
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();
        }

        public void run() {
            try {
                readInputHeaders();
                writeResponse("<html><body><h1>Hello from my computer</h1></body></html>");
            } catch (Throwable t) {
                /*do nothing*/
            } finally {
                try {
                    socket.close();
                } catch (Throwable t) {
                    /*do nothing*/
                }
            }
            System.err.println("Client processing finished");
        }

        private void writeResponse(String content) throws Throwable {
            String response = "HTTP/1.1 200 OK\r\n" +
                    "Server: FuckServer/2023-02-03\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: " + content.length() + "\r\n" +
                    "Connection: close\r\n\r\n";
            String result = response + content;
            outputStream.write(result.getBytes());
            outputStream.flush();
        }

        private void readInputHeaders() throws Throwable {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while(true) {
                String content = bufferedReader.readLine();
                if (content == null || content.trim().length() == 0) break;
            }
        }
    }
}
