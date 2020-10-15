package networkingServer;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class FileDescriptors extends MainGui {
	JTextPane pane;
	JScrollPane jpane;
	public Socket[] arr = new Socket[255];
	public Client[] cli = new Client[255];
	public FileWriter log;
	public int max_con = 0; 							//Koji je najveci id do sada konektovanog

	public FileDescriptors(JTextPane pane, JScrollPane jp) 
	{
		this.pane = pane;
		jpane = jp;
	}
	
	public void log(String log_text) throws IOException 
	{
		log = new FileWriter("sys.log", true);
		
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeZone(null);
		log.write("[" + cal.getTime() + "] " + log_text + "\n");
		pane.setText(pane.getText() + "[" + cal.getTime() + "] " + log_text + "\n");
		//jpane.getVerticalScrollBar().setValue( jpane.getVerticalScrollBar().getMaximum() );
		
		log.flush();
		log.close();
	}
	
	public String getClients()
	{
		String clients = "";
		
		for(int i = 0; i<=max_con; i++) 
		{
			if(cli[i] != null && cli[i].getHide() != true) 
			{
				clients = clients + "\r\n" + cli[i].getName();
			}
		}
		
		return clients;
	}
	
	public void broadcast(String message, int i_d) throws IOException 
	{
		log(message);
		for(int i = 0; i<=max_con; i++) 
		{
			if(i != i_d && arr[i] != null) 
			{
				//System.out.println(fd.cli[i].getId() + " " + client.getId());
				PrintWriter pout = new PrintWriter(arr[i].getOutputStream(), true);
				pout.println(message);
			}
		}
	}
	
	public void resetObjects() 
	{
		for(int i= 0; i<255; i++) 
		{
			arr[i] = null;
			cli[i] = null;
		}
	}
	
	public void resetMaxCon() 
	{
		for(int i = 0; i<255; i++) 
		{
			if(cli[i] != null || arr[i] != null) 
			{
				max_con = i;
				//System.out.println(cli[i].getId() + cli[i].getName());
			}
		}
	}
	
	//Prolazi kroz sve elemente array-a ako se neki disconnectovao
	void storeClient(Client clnt, Socket sckt) 
	{
		for(int i= 0; i<255; i++) 
		{
			if(arr[i] == null) 
			{
				arr[i] = sckt;			//Storuje socket u array
				cli[i] = clnt;			//Storuje objekat klijenta u array
				clnt.setId(i);			//Postavlja id klijenta
				break;
			}
		}
	}
	
	public void newClient(Socket socket)
	{
		//Kreira klijenta
		Client clnt = new Client();
		//Kreira Prostor za prosledjeni socket
		Socket sckt = socket;
		
		//Smesta klijenta u array klijenta
		storeClient(clnt, sckt);
		
		//Kreira nov klijent thread koji usluzuje tog klijenta
		ClientThread clientThread = new ClientThread(sckt, clnt, this);
		//Pokrece thread
		clientThread.start();
		
		//Postavi Max_Con na maks
		resetMaxCon();
	}
	
	public void removeClient(int n) 
	{
		arr[n] = null;
		cli[n] = null;
		
		//Postavi Max_Con na maks
		resetMaxCon();
	}
	
	public int getMaxCon() 
	{
		resetMaxCon();
		return max_con;
	}
	
	public Client getClient(int n) 
	{
		return cli[n];
	}
	
	public Socket getSocket(int n) 
	{
		return arr[n];
	}
}
