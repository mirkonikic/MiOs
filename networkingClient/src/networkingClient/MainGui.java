package networkingClient;

import java.awt.BorderLayout;
import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultCaret;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

public class MainGui extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	
	public Boolean on = false;
	public ClientWrite threadWrite;
	public Socket socket;
	private JTextField textField;
	public String ip_addr;
	public int port;
	private JTextField textField_2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MainGui frame = new MainGui();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	}

	/**
	 * Create the frame.
	 */
	public MainGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 497);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 11, 864, 351);
		contentPane.add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Monospac821 BT", Font.PLAIN, 12));
		scrollPane.setViewportView(textPane);
		DefaultCaret caret = (DefaultCaret)textPane.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(664, 417, 136, 41);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.control);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBounds(518, 417, 136, 41);
		contentPane.add(panel_1);
		
		JButton button = new JButton("Start");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textPane.setText("Booting...");
				try {
					Thread.sleep(3000);
					on = true;
					
					try {
						
						ip_addr = textField.getText();
						port = Integer.parseInt(textField_2.getText());
						
						socket = new Socket(ip_addr, port);
						//Thread reads from server
						ClientRead threadRead = new ClientRead(socket, textPane, scrollPane);
						
						threadRead.start();
						
						textField_1.requestFocus();
						
					} catch (UnknownHostException e) {
						textPane.setText("SERVER ON THAT IP ADDRESS IS DOWN!");
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						textPane.setText("SERVER ON THAT IP ADDRESS IS DOWN!");
						e.printStackTrace();
					}				
				} 
				catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button.setFont(new Font("Monospac821 BT", Font.PLAIN, 12));
		panel_1.add(button);
		
		JButton btnKill = new JButton("Kill");
		btnKill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_1.setText("/exit");
				ClientWrite threadWrote = new ClientWrite(socket, textField_1, textPane, scrollPane);
				threadWrote.start();
				on = false;
			}
		});
		btnKill.setFont(new Font("Monospac821 BT", Font.PLAIN, 12));
		panel.add(btnKill);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.menu);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_2.setBounds(10, 417, 498, 41);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblCommands = new JLabel("Message:");
		lblCommands.setBounds(10, 10, 56, 15);
		panel_2.add(lblCommands);
		lblCommands.setFont(new Font("Monospac821 BT", Font.PLAIN, 12));
		
		Action action = new AbstractAction()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	ClientWrite threadWrote = new ClientWrite(socket, textField_1, textPane, scrollPane);
		    	 threadWrote.start();
		    }
		};
		
		textField_1 = new JTextField();
		textField_1.setBounds(76, 7, 324, 21);
		panel_2.add(textField_1);
		textField_1.setFont(new Font("Monospac821 BT", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.addActionListener( action );
		textField_1.requestFocus();
	
		JButton btnSend = new JButton("Send");
		btnSend.setFont(new Font("Monospac821 BT", Font.PLAIN, 12));
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientWrite threadWrote = new ClientWrite(socket, textField_1, textPane, scrollPane);
				threadWrote.start();
			}
		});
		btnSend.setBounds(410, 7, 78, 23);
		panel_2.add(btnSend);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_3.setBounds(516, 373, 358, 33);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblIpaddress = new JLabel("ip_addr:");
		lblIpaddress.setFont(new Font("Monospac821 BT", Font.PLAIN, 12));
		lblIpaddress.setBounds(10, 9, 68, 14);
		panel_3.add(lblIpaddress);
		
		textField = new JTextField();
		textField.setFont(new Font("Monospac821 BT", Font.PLAIN, 12));
		textField.setBounds(88, 6, 148, 20);
		panel_3.add(textField);
		textField.setColumns(10);
		
		JLabel lblPort = new JLabel("port:");
		lblPort.setFont(new Font("Monospac821 BT", Font.PLAIN, 12));
		lblPort.setBounds(246, 9, 46, 14);
		panel_3.add(lblPort);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Monospac821 BT", Font.PLAIN, 12));
		textField_2.setBounds(302, 7, 46, 20);
		panel_3.add(textField_2);
		textField_2.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_4.setBounds(10, 373, 498, 33);
		contentPane.add(panel_4);
		
		JButton btnSaveChat = new JButton("Save Chat");
		btnSaveChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					File file = new File("chat.txt");
					FileWriter f = new FileWriter(file);
					
					f.write(textPane.getText());
					f.flush();
					f.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSaveChat.setFont(new Font("Monospac821 BT", Font.PLAIN, 12));
		panel_4.add(btnSaveChat);
		
		JButton btnClearChat = new JButton("Clear Chat");
		btnClearChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane.setText("");
			}
		});
		btnClearChat.setFont(new Font("Monospac821 BT", Font.PLAIN, 12));
		panel_4.add(btnClearChat);
	}
}
