package am.tech42.server;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;


class Users extends Thread{
	private  Socket socket;
	private  BufferedReader in;
	private  BufferedWriter out;

	public Users (Socket socket) throws IOException {
		this.socket = socket;
		in = new BufferedReader(new InputStreamReader (socket.getInputStream()));
		out = new BufferedWriter(new OutputStreamWriter (socket.getOutputStream()));
		start();
	}

	@Override 
	public void run() {
		String message;
		try{
			while (true){
				try{
					message = in.readLine();
					if(message.equals("exit")){
                      Server.clientList.remove(this);
                      break;
                    }
					for (Users user :Server.clientList){
						if(user.equals(this)){
							continue;
						}
						user.sendMessage(message);
						System.out.println("get: " + message);
					}
				} catch(IOException e){
					stopAction();
					e.printStackTrace();
				}
			}
		}finally{
			stopAction();
		}
	}

	private  void stopAction(){
		try{
			in.close();
			out.close();
			socket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	private  void sendMessage(String message)throws IOException {
		out.write (message + "\n");
		out.flush();
	}
}