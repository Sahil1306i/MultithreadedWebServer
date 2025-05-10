package WebServer.MultiThreaded;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class MultiThreadedServer  {

    public Consumer<Socket> getConsumer (){
        return (clientSocket)->{
            try {
                System.out.println("Accepted connection from " + clientSocket.getInetAddress());
                PrintWriter toSocket = new PrintWriter(clientSocket.getOutputStream(), true);
                toSocket.println("Hello from server " + clientSocket.getInetAddress());
                clientSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        };
    }

    public static void main(String[] args) throws IOException {
        int port = 8010;
        MultiThreadedServer server = new MultiThreadedServer();

        ServerSocket serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(70000);//sets the timeout for the socket expiry
        System.out.println("Server is listning on the port "+port);

        while(true){
            Socket clientSocket = serverSocket.accept();

            // a new socket is created when the client asks for it

            Thread thread = new Thread(()-> server.getConsumer().accept(clientSocket));
            // a new thread is made which will run a funstion (in this function we will run the socket )
            thread.start();
        }
    }
}

