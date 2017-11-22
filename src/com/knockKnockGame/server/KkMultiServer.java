package com.knockKnockGame.server;

import java.io.IOException;
import java.net.ServerSocket;

public class KkMultiServer {
	public static void main(String...ar){
		if (ar.length != 1){
			System.err.println("Usage: java MultiServer <port number>");
			System.exit(1);
		}
		int portNumber = Integer.parseInt(ar[0]);
		boolean listening = true;
		try(ServerSocket serverSocket = new ServerSocket(portNumber)){
			while(listening){
				new KkMultiServerThread(serverSocket.accept()).start();
			}
		} catch (IOException e){
			System.err.println("Could not listen on port number" + portNumber);
		}
	}
}