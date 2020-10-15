package networkingServer;

import java.util.*;

import java.io.*;
import java.net.*;
import java.nio.CharBuffer;

//TODO
// -Kad napravis gui ubaci jos jedan input field za server komande:
// 		{Ispisi sva imena klijenata ili ID-ove ili prikazi memoriju klijenata sada itd.. log on/off}

public class Test {
	//public static void main(String[] args) throws IOException {
				
	//	ServerAccept threadAccept = new ServerAccept();
	//	threadAccept.start();
				
		/*THREADING
		ServerThread thread = new ServerThread("Server1");
		ServerThread thread2 = new ServerThread("Server2");
		thread.start();
		thread2.start();
		*/
		
		/*CHARACTER FILE STREAM
		FileWriter logs_out;
		Scanner inp = new Scanner(System.in);
		logs_out = new FileWriter("msg.log");
		
		String msg;
		int k = 0;
		
		while(k != -1) 
		{
			msg = inp.nextLine();
			if(msg.equals("exit")) 
			{
				k = -1;
			}
			System.out.println();
			logs_out.write(new GregorianCalendar().getTime() + ": " + msg + "\n");
			logs_out.flush();
		}
		
		logs_out.close();
		
		BufferedReader br = new BufferedReader(new FileReader("msg.log"));
				
		String temp;
		while((temp = br.readLine()) != null)
		{
			System.out.println(temp);
		}
		*/
		
		/*BYTE FILE STREAM
		FileOutputStream logs = null;
		Scanner msg = new Scanner(System.in);
		
		logs = new FileOutputStream("msg.log");
		
		int ms;
		int k = 0;
		
		while(k != -1) 
		{
			ms = msg.nextInt();
			if(ms == -1)
				k = -1;
			logs.write(ms);
			System.out.println("message sent!");
		}
		
		FileInputStream log_out = new FileInputStream("msg.log");
		
		int temp;
		while((temp = log_out.read()) != -1) 
		{
			System.out.println(temp);
		}
		
		
		log_out.close();
		logs.close();
		*/
				
		/*SCANNER
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter a message:");
		String msg;
		int number = 0;
		
		while(number != -1) {
			msg = input.next();
			System.out.println("You entered: " + msg);
			if(msg.equals("exit")) 
			{
				number = -1;
			}
		}
		
		input.close();
		*/
	//}
}
