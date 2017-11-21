package com.knockKnockGame.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class KnockKnockClient {

	public static void main(String[] args) {
		if (args.length != 2){
			System.err.println("Usage: java KnockKnockClient <host name> <port number>");
			System.exit(1);
		}
		String hostName = args[0];
		int portNumber = Integer.parseInt(args[1]);
		
		try(
			Socket socket = new Socket(hostName,portNumber);
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		){
		  String fromServer;
		  String fromUser;
	      while ((fromServer = in.readLine())!= null){
	    	  if (fromServer.equalsIgnoreCase("Bye")){
	    		  System.out.println("You're a spoilt sport! Bye!");
	    		  break;
	    	  }
	    	  
	    	  System.out.println("Server:" + fromServer);
	    	  
	    	  fromUser = stdIn.readLine();
	    	  if (fromUser != null){
	    		  out.println(fromUser);
	    	  }
	      	}
		}
		catch(UnknownHostException e){
			e.printStackTrace();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	
	}
}
