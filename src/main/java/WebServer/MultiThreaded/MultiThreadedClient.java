package WebServer.MultiThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MultiThreadedClient {

    public Runnable getRunnable(){ // it is a function which doesnt do anything and only implements run
        return new Runnable() {
            @Override
            public void run()   {
                int port = 8010;
                try{
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
            } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    // we will have to spawn threads for the client side and every thread will give a request
    public static void main(String[] args) {
        MultiThreadedClient client = new MultiThreadedClient();
        for (int i = 0; i <100 ; i++){
            try{
                Thread thread = new Thread(client.getRunnable());
                thread.start();

            }catch (Exception ex){
                return;
            }
        }
    }
}
