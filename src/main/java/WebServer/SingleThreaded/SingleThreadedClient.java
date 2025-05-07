package WebServer.SingleThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.UnknownHostException;

public class SingleThreadedClient {
    public void run() throws UnknownHostException, IOException {
        // here we are checking for the host exception and Io exception
        int port = 8010;
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, port);
        PrintWriter toSocket = new PrintWriter(socket.getOutputStream());
        BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toSocket.println("Hello from the client"); // this is sent on the socket from the client side
        String line = fromSocket.readLine();
        System.out.println("Response from the socket is:"+line);

        // closing the sockets
        toSocket.close();
        fromSocket.close();
        socket.close();
    }

    public static void main(String[] args) {
        try{
            SingleThreadedClient client = new SingleThreadedClient();
            client.run();
        } catch(Exception ex){
        ex.printStackTrace();

        }
    }
}
