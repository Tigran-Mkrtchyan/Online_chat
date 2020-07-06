package am.tech42.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;


public class Server {

    public static void main( String[] args ) {
         try(
           ServerSocket server = new ServerSocket(80);
            Socket socket =  server.accept();
        	BufferedReader in = new BufferedReader (new InputStreamReader(socket.getInputStream()));
        	BufferedWriter out = new BufferedWriter (new OutputStreamWriter( socket.getOutputStream()));
           ){
            while (true){
            	String message = in.readLine();
                if(message.equals("exit")){
                    break;
                }
                else{
                	System.out.println(message);
                	out.write("hi I get message:  " +"\"" +message +"\"\n" );
                    out.flush();
                }
            }
		} catch (IOException e){
			e.printStackTrace();
		}
    }
}
