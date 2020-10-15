package networkingClient;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class ClientWrite extends Thread {
	
	PrintWriter out;
	String msg;
	Socket socket;
	JTextField display;
	JTextPane bd;
	JScrollPane jpane;
	
	public ClientWrite(Socket s, JTextField txt, JTextPane big_d, JScrollPane jp) 
	{
		socket = s;
		display = txt;
		bd = big_d;
		jpane = jp;
	}
	
	public void run() 
	{
			PrintWriter out;
			try {
				out = new PrintWriter(socket.getOutputStream(), true);
				
				
				msg = display.getText();
		    	System.out.println(msg);
		    	out.println(msg);
		    	bd.setText(bd.getText() + "\n" + msg);
				//jpane.getVerticalScrollBar().setValue( jpane.getVerticalScrollBar().getMaximum() );
		    	
				if(msg.equals("/exit")) 
				{
					out.close();
					try {
						socket.close();
					} catch (IOException e33) {
						// TODO Auto-generated catch block
						e33.printStackTrace();
					}
				}
		    	display.setText("");
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.exit(0);
				e.printStackTrace();
			}
	}
}
