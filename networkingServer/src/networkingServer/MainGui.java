package networkingServer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.border.CompoundBorder;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.io.*;
import java.net.*;
import java.awt.Component;
import java.awt.Frame;

//TODO
// +Loguj ip adrese
// -Dovrsi frontend
// -Napravi funkciju koja proverava za komande
// -ServerKomande: -log(otvori novi pane sa logs) -kick(user) -refresh(user_data) -save(as a text file?)
// -ClientKomande: -help -hide -exit -kill -clients

public class MainGui extends JFrame {

	private JPanel contentPane;
	private JTextField commandField;
	private JTextField idField;
	private JTextField nameField;
	private JTextField ipField;
	
	public int k_id = 0;
	public String k_name = "null";
	public String k_ip_addr = "0.0.0.0";
	public FileDescriptors file_desc;
	public Boolean on = false;
	int port = 9090;
	private JTextField textField;
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
		setResizable(false);
		setForeground(SystemColor.desktop);
		setFont(new Font("Monospac821 BT", Font.PLAIN, 12));
		setBackground(SystemColor.desktop);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 965, 557);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBackground(new Color(240, 248, 255));
		panel.setLocation(10, 479);
		panel.setPreferredSize(new Dimension(10, 200));
		panel.setSize(new Dimension(296, 39));
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Commands:");
		lblNewLabel.setFont(new Font("Monospac821 BT", Font.PLAIN, 12));
		panel.add(lblNewLabel);
		
		commandField = new JTextField();
		commandField.setFont(new Font("Monospac821 BT", Font.PLAIN, 12));
		panel.add(commandField);
		commandField.setColumns(10);
		
		JButton commandBtn = new JButton("Submit");
		commandBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(on == true) 
				{
					String a = commandField.getText();
					switch(a) 
					{
						case "/exit":
							System.exit(0);
							break;
						case "":
							break;
						case "/clients":
							break;
						default:
							commandField.setText("");
							break;
					}
				}
			}
		});
		commandBtn.setFont(new Font("Monospac821 BT", Font.PLAIN, 12));
		panel.add(commandBtn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBounds(316, 479, 227, 39);
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_2.setBounds(553, 479, 119, 39);
		contentPane.add(panel_2);
		
		JButton killBtn = new JButton("Kill");
		killBtn.setFont(new Font("Monospac821 BT", Font.PLAIN, 12));
		killBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		panel_2.add(killBtn);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_3.setBounds(682, 267, 267, 251);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_4.setBounds(10, 11, 247, 189);
		panel_3.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(24, 14, 21, 15);
		panel_4.add(lblId);
		lblId.setFont(new Font("Monospac821 BT", Font.PLAIN, 12));
		
		idField = new JTextField();
		idField.setBounds(55, 11, 165, 21);
		panel_4.add(idField);
		idField.setBackground(SystemColor.control);
		idField.setFont(new Font("Monospac821 BT", Font.BOLD, 12));
		idField.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 46, 35, 15);
		panel_4.add(lblName);
		lblName.setFont(new Font("Monospac821 BT", Font.PLAIN, 12));
		
		nameField = new JTextField();
		nameField.setBounds(55, 43, 165, 21);
		panel_4.add(nameField);
		nameField.setBackground(SystemColor.control);
		nameField.setFont(new Font("Monospac821 BT", Font.BOLD, 12));
		nameField.setColumns(10);
		
		JLabel lblIpv = new JLabel("IPv4:");
		lblIpv.setBounds(10, 78, 35, 15);
		panel_4.add(lblIpv);
		lblIpv.setFont(new Font("Monospac821 BT", Font.PLAIN, 12));
		
		ipField = new JTextField();
		ipField.setBounds(55, 75, 165, 21);
		panel_4.add(ipField);
		ipField.setBackground(SystemColor.control);
		ipField.setFont(new Font("Monospac821 BT", Font.BOLD, 12));
		ipField.setColumns(10);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setFont(new Font("Monospac821 BT", Font.PLAIN, 12));
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(on == true) 
				{
					if(file_desc.cli[k_id] == null) 
					{
						k_name = "null";
						k_ip_addr = "0.0.0.0";
					}
					else 
					{
						k_name = file_desc.cli[k_id].getName();
						k_ip_addr = file_desc.cli[k_id].getIp_addr();
					}
						
					idField.setText(k_id + "");
					nameField.setText(k_name + "");
					ipField.setText(k_ip_addr + "");
				}
			}
		});
		btnRefresh.setBounds(91, 211, 83, 25);
		panel_3.add(btnRefresh);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 216, 71, 23);
		panel_3.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(k_id > 0 && on == true) 
				{
					k_id--;
					if(file_desc.cli[k_id] == null) 
					{
						k_name = "null";
						k_ip_addr = "0.0.0.0";
					}
					else 
					{
						k_name = file_desc.cli[k_id].getName();
						k_ip_addr = file_desc.cli[k_id].getIp_addr();
					}
					
					idField.setText(k_id + "");
					nameField.setText(k_name + "");
					ipField.setText(k_ip_addr + "");
				}
			}
		});
		btnBack.setFont(new Font("Monospac821 BT", Font.PLAIN, 12));
		
		JButton btnNext = new JButton("Next");
		btnNext.setBounds(184, 216, 73, 23);
		panel_3.add(btnNext);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(k_id < 255 && on == true) 
				{
					k_id++;
					if(file_desc.cli[k_id] == null) 
					{
						k_name = "null";
						k_ip_addr = "0.0.0.0";
					}
					else 
					{
						k_name = file_desc.cli[k_id].getName();
						k_ip_addr = file_desc.cli[k_id].getIp_addr();
					}
					
					idField.setText(k_id + "");
					nameField.setText(k_name + "");
					ipField.setText(k_ip_addr + "");
				}
			}
		});
		btnNext.setFont(new Font("Monospac821 BT", Font.PLAIN, 12));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 662, 457);
		contentPane.add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		textPane.setFont(new Font("Monospac821 BT", Font.PLAIN, 12));
		textPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		DefaultCaret caret = (DefaultCaret)textPane.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		JButton startBtn = new JButton("Start");
		startBtn.setBounds(14, 6, 92, 22);
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(on == false) {
					textPane.setText("Booting server..");
					textPane.setText("Booting server...");
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					
					textPane.setText("");
					on = true;
					idField.setText(k_id + "");
					nameField.setText(k_name + "");
					ipField.setText(k_ip_addr + "");
					
					file_desc = new FileDescriptors(textPane, scrollPane);
					if(!textField.equals("")) 
					{
						port = Integer.parseInt(textField.getText());
					}
					ServerAccept threadAccept = new ServerAccept(file_desc, port);
					threadAccept.start();
				}
			}
		});
		panel_1.setLayout(null);
		startBtn.setFont(new Font("Monospac821 BT", Font.PLAIN, 12));
		panel_1.add(startBtn);
		
		JLabel lblPort = new JLabel("port:");
		lblPort.setBounds(116, 11, 35, 14);
		lblPort.setFont(new Font("Monospac821 BT", Font.PLAIN, 11));
		panel_1.add(lblPort);
		
		textField = new JTextField();
		textField.setFont(new Font("Monospac821 BT", Font.PLAIN, 12));
		textField.setBounds(161, 7, 51, 20);
		panel_1.add(textField);
		textField.setColumns(10);
	}
}
