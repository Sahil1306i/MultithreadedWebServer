package WebServer.SingleThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadedServer {
    public void run() throws IOException {
        int port = 8010;// define the port where the server listens to the client
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(20000);
        while(true){
            System.out.println("Server is listning on port" + port);
            Socket acceptedConnection = socket.accept();
            // code will be stopping here until there is no connection from the client
            System.out.println("Connection accepted"+ acceptedConnection.getRemoteSocketAddress());
            PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);
            //printwriter needs an output stream and getoutput steam does that
            // printwriter helps us to get the data from the output stream from the server it is a class

            BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
            toClient.println("Hello from the server");

            // closing all the connections
            toClient.close();
            fromClient.close();
            acceptedConnection.close();


        }
    }
    public static void main(String[] args) {
        SingleThreadedServer server = new SingleThreadedServer();
        // the run method is not a static method so it is not in the memory so will have to make a instace for that
        try{
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
