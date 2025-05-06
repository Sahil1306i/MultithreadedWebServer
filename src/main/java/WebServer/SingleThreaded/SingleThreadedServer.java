package WebServer.SingleThreaded;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SingleThreadedServer {
    public void run() throws IOException, UnknownHostException {
        int port = 8010;// define the port where the server listens to the client
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(10000);
        while(true){
            System.out.println("Server is listning on port" + port);
            Socket aacceptedConnection = socket.accept();
            // code will be stopping here until there is no connection from the client

        }
    }
    public static void main(String[] args) {

    }
}
