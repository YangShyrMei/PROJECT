package net.share.GroupManager;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.net.ServerSocket;
import java.net.Socket;

import net.share.loginSystem.getKeys;

import java.io.*;


public class Interface extends Frame implements ActionListener{
	

	JButton b1,b2,b3;
	JFrame frame;
	String requested_client,IP_address_of;
	IPAddressOfClients cip = new IPAddressOfClients();

	public void receiveClientID() throws Exception
	{
		System.out.println("Waiting for request");
		ServerSocket ss = new ServerSocket(5321);
		Socket s = ss.accept();
		System.out.println("Client Connected");
		InputStream is = s.getInputStream();
	    InputStreamReader isr = new InputStreamReader(is);
	    BufferedReader br= new BufferedReader(isr);
		requested_client=br.readLine();
		requested_client=requested_client.trim();
	    System.out.println("Client ID received");
	       
	       br.close();
	       isr.close();
	       is.close();
	       s.close();
	       ss.close();
	}
	
	public Interface()  {
	
		
		JFrame frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setBounds(0,0,900,800);
		
		Container container= frame.getContentPane();
		container.setBackground(Color.ORANGE);
		container.setLayout(null);
		
		JLabel label2 = new JLabel();
		label2.setText("Do you want to Send or Receive public Key? ");
		label2.setBounds(200,125,800,50);
		label2.setFont(new Font("Times New Roman",Font.BOLD,25));
		container.add(label2);
		//label2.setForeground(new Color("0x00FF00"));
		//label2.printBorder(true);
		label2.setBackground(Color.black);	
		
	
		
		b2 =new JButton("Send Public Key");
		b2.setBounds(350,250,200,40);
		b2.setFocusable(false);
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
                    new SendPublicKey(IP_address_of, requested_client);
                    dispose();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
				
				
			}
		});
		container.add(b2);
	
	
		
		b1 =new JButton("Receive Public Key");
		b1.setBounds(350,400,200,40);
		b1.setFocusable(false);
		//b5.addActionListener(this);
		b1.setActionCommand("send");
		b1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ReceivePublicKey r = new ReceivePublicKey();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(b1,"Received Public key");
				dispose();
	 
			}
			
		});
		
		container.add(b1);
		
		b3 =new JButton("Waiting for Public Key Request");
		b3.setBounds(350,550,200,40);
		b3.setFocusable(false);
		//b5.addActionListener(this);
		b3.setActionCommand("send");
		b3.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("Waiting for request");
					ServerSocket ss = new ServerSocket(5513);
					Socket s = ss.accept();
					System.out.println("Client Connected");
					InputStream is = s.getInputStream();
				    InputStreamReader isr = new InputStreamReader(is);
					   //receive Client name
					   BufferedReader br= new BufferedReader(isr);
				       String client_name = br.readLine();
					   br.close();
					   isr.close();
				       is.close();
				       s.close();
				       ss.close();

					    //receive IP Address
					   System.out.println("Waiting for IP address");
					   ServerSocket ss1 = new ServerSocket(5498);
					   Socket s1 = ss1.accept();
					   InputStream is1 = s1.getInputStream();
				       InputStreamReader isr1 = new InputStreamReader(is1);
					   BufferedReader br1= new BufferedReader(isr1);
					   IP_address_of = br1.readLine();
					   IP_address_of=IP_address_of.trim();
					   cip.addIPAddress(client_name,IP_address_of);
					   br1.close();
					   isr1.close();
				       is1.close();
				       s1.close();
				       ss1.close();
				       receiveClientID();
				       
	
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(b3,"Request received so send requested public key");
				dispose();
	 
			}
			
		});
		
		container.add(b3);
		
		
		frame.setVisible(true);
		
	
		
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	

	public static void main(String[] args) {
		
		Interface b= new Interface();
		
		
		// TODO Auto-generated method stub

	}




}