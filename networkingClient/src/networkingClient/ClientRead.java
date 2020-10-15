package networkingClient;	

import java.io.*;
import java.net.*;
import javax.swing.*;

public class ClientRead extends Thread {
	
	BufferedReader input;
	Socket socket;
	String msg;
	JTextPane display;
	JScrollPane jpane;
	
	public ClientRead(Socket s, JTextPane txt, JScrollPane jp) 
	{
		socket = s;
		display = txt;
		jpane = jp;
	}
	
	public void run() 
	{
		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
			while(true) 
			{
				msg = input.readLine();
				display.setText(display.getText() + "\n" + msg);
				//jpane.getVerticalScrollBar().setValue( jpane.getVerticalScrollBar().getMaximum() );
			}
		
		} catch (IOException e) {
			System.exit(0);
		}
	}
}
