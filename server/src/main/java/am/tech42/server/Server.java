package am.tech42.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


public class Server {
    public static List <Users> clientList = new ArrayList<>();
    public static void main( String[] args ) {
      try ( ServerSocket server = new ServerSocket(80)){
           while (true){
                try{
                    Socket socket = server.accept();
                    clientList.add(new Users(socket));
        		} catch (IOException e){
        			e.printStackTrace();
        		}
            }
        } catch (IOException e){
               e.printStackTrace();
        }
    }
}
