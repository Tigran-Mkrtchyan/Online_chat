package am.tech42.client;

import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;


public class Client{
    private static volatile boolean isGetOut = false;

    public static void main (String [] args){
        try(Socket socket = new Socket("127.0.0.1",80);
            BufferedReader reader = new  BufferedReader ( new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream()));
            BufferedWriter out = new BufferedWriter ( new OutputStreamWriter( socket.getOutputStream()));
            ){
            while(true){
                new Thread() {
                    public void run () {
                        while(true){
                            try {
                                String message = reader.readLine();
                                out.write(message +"\n");
                                out.flush();
                                if(message.equals("exit")){
                                    out.write(message +"\n");
                                    isGetOut = true;
                                    break;
                                }
                            }catch ( IOException e){
                                    e.printStackTrace();
                            }
                        }
                    }
                }.start();
                if(isGetOut){
                    break;
                }
                System.out.println(in.readLine());
            }

        } catch ( IOException e){
            e.printStackTrace();
        }
    }
}