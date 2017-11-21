package com.knockKnockGame.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class KnockKnockServer {

	public static void main(String[] args) {
		if (args.length != 1){
			System.err.println("Usage: java KnockKnockServer <port number>");
			System.exit(1);
			}
			
			int portNumber = Integer.parseInt(args[0]);
			
			try(
				ServerSocket serverSocket = new ServerSocket(portNumber);
				Socket clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			){
				String fromClient;
				String toClient;
				
				//Server is a bigger person, initiating a conversation with the client. 
				KnockKnockProtocol kkp = new KnockKnockProtocol();
				toClient = kkp.processInput(null);
				out.println(toClient);
				
				while((fromClient = in.readLine()) != null){
					toClient = kkp.processInput(fromClient);
					out.println(toClient);
					if(toClient.equals("Bye")){
						break;
					}
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}
	}

}
