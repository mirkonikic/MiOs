package networkingServer;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientThread extends Thread{
	Socket socket = null;			//Mesto gde cuvamo klijenta
	Client client = null;					//NZM STA JE OVO
	FileDescriptors fd;				//Objekat koji cuva sve klijente
	
	PrintWriter out = null;				//Output do klijenta
	BufferedReader input = null;			//Input od klijenta
	
	String name;
	String msg;
		
	public ClientThread(Socket s, Client c, FileDescriptors f_d) 
	{
		socket = s;
		client = c;
		fd = f_d;
	}
	
	//Initiates Client, takes his name and lets him know he is good to go
	public void introFunction() 
	{
		try 
		{
			//Definisem input i output ka klijentu
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);

			//Sets IP
			client.setIp_addr(socket.getRemoteSocketAddress().toString());
			//Animation
			try {
			out.println("		   .----.    ");
			sleep(300);
			out.println("		   |C>_ |    ");
			sleep(300);
			out.println("		 __|____|__  ");
			sleep(300);
			out.println("		|  ______--| ");
			sleep(300);
			out.println("		`-/.::::.\\-'a");
			sleep(300);
			out.println("		 `--------'  ");
			sleep(300);
			out.println("	   Chat by mirkof. ");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Sets his name
			out.println("Input your name: ");
			name = input.readLine();
			client.setName(name);
			//Prints to server
			System.out.println("User " + name + " has connected.");
			fd.broadcast(name+" has joined the chat.", client.getId());			
			fd.log(name+" has joined the chat." + " | " + client.getIp_addr());
			//Prints to him
			out.println("*****Hello " + client.getName() + ". To exit type in '/exit'*****");
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void run() 
	{
		try {
			introFunction();
			
			while(true)
			{
				msg = input.readLine();
				
				if(msg.equals("/exit")) 
				{
					fd.log(client.getName() + " used '/exit' command");
					System.out.println(name + " has disconnected.");
					fd.broadcast(name+" has disconected.", client.getId());
					break;
				}
				else if(msg.equals("/clients")) 
				{
					fd.log(client.getName() + " used '/clients' command");
					out.println("*****clients: " + fd.getClients());
				}
				else if(msg.equals("/help")) 
				{
					fd.log(client.getName() + " used '/help' command");
					out.println("**********This is encrypted chat using TCP protocol.***************");
					out.println("	To use this chat fully, you can also use commands.");
					out.println("	/clients		 - to see who is online");
					out.println("	/help			 - to ask for help");
					out.println("	/hide 			 - to hide from others using /clients command");
					out.println("	/exit			 - to exit");
					out.println("	/kill 			 - to exit");
					out.println("  ");
				}
				else if(msg.equals("/hide")) 
				{
					fd.log(client.getName() + " used '/hide' command, hide->" + client.getHide());
					client.setHide(!client.getHide());
					out.println("*****hide variable set to: " + client.getHide());
				}
				else if(msg.equals("/kill")) 
				{
					fd.log(client.getName() + " used '/kill' command");
					System.exit(0);
				}
				else 
				{
					//Printuje na serveru poruku usera
					System.out.println(client.getName() + ": " + msg);
					//System.out.println(client.getId());
					
					//Salje svim ostalim userima poruku
					//System.out.println(fd.getMaxCon());
					fd.broadcast(name+": "+msg, client.getId());
				}
			}	
			fd.removeClient(client.getId());
			input.close();
			socket.close();

			
		} catch (IOException e1) {
			System.out.println("Error c" + client.getId() + ": Problem with recieving client " + client.getName() + "'s message");
		}
	}
}
