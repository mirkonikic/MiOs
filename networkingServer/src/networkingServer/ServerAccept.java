package networkingServer;

import java.io.*;
import javax.swing.*;
import java.net.*;

public class ServerAccept extends Thread{
	
	FileDescriptors file_desc;
	int port = 9090;
	
	public ServerAccept(FileDescriptors file_desc, int pport) 
	{
		port = pport;
		this.file_desc = file_desc;
	}
	
	public void run() 
	{
		try {
			ServerSocket server = new ServerSocket(port);
			Boolean stop = false;
			file_desc.resetObjects();
			file_desc.log("SERVER: booted up!");
			
			while(!stop) 
			{
				//CEKA NA KLIJENTE DA IH ACCEPTUJE
				System.out.println("waiting for clients...");
				Socket socket = server.accept();
				
				//poziva funkciju kreiraj novog klijenta sa prosledjenim njenim file descriptorom
				file_desc.newClient(socket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
