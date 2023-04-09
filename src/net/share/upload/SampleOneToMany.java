package net.share.upload;
import java.awt.*;
import java.awt.event.*;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import net.share.client.SocClient;
import net.share.encryption.SymmetricEncryption;


public class SampleOneToMany extends Frame implements ActionListener{
	
	private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
	JButton b1,b2,b3,b4,b5,b6,add;
	JFrame frame;
	JTextField t1 = new JTextField();
	
	JTextField inputLine ;
	JTextField desc=new JTextField();
	Container container;
	
	private static File file;
	public SampleOneToMany()  {
		
		
		JFrame frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,0,900,800);
		
		Container container= frame.getContentPane();
		container.setBackground(Color.ORANGE);
		container.setLayout(null);
		
		
		
		t1.setBounds(350,320,200,40);
		container.add(t1);
		//t1.setText("Enter reciver Public Key");
		
		JLabel label=new JLabel("Enter the Client ID :");
		container.add(label);
		label.setBounds(220,308,150,60);
		
		JLabel label2 = new JLabel();
		label2.setText("A Public Key based Framework to deal with Data Sharing in Clouds");
		label2.setBounds(80,15,800,50);
		label2.setFont(new Font("Times New Roman",Font.BOLD,25));
		container.add(label2);
		//label2.setForeground(new Color("0x00FF00"));
		//label2.printBorder(true);
		label2.setBackground(Color.black);
		
		b1 =new JButton("Upload the File");
		b1.setBounds(350,100,200,40);
		b1.setFocusable(false);
		container.add(b1);
		b1.addActionListener(this);
		b1.setActionCommand("upload");
		
		/*b2 =new JButton("Generate Key");
	    b2.setBounds(350,170,200,40);
		container.add(b2);
		b2.addActionListener(this);
		b2.setActionCommand("generateKey"); */
		
		b3 =new JButton("Encryption");
		b3.setBounds(350,170,200,40);
		b3.setFocusable(false);
		container.add(b3);
		//b3.addActionListener(this);
		b3.setActionCommand("encryption");
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					SymmetricEncryption en = SymmetricEncryption.getEncrypter(true);
					File src = new File("C:/Users/Dell/eclipse-workspace/PublicBasedEncryptionFileSharingInClouds/src_file");
					File dest = new File("C:/Users/Dell/eclipse-workspace/PublicBasedEncryptionFileSharingInClouds/encrypted_file");
					en.encrypt(src, dest);
				}
				catch(Exception ex1) {
					System.out.println(ex1);

				}
			}
		});
		
		b4 =new JButton("Receiver public key");
		b4.setBounds(350,240,200,40);
		b4.setFocusable(false);
		container.add(b4);
		b4.addActionListener(this);
		b4.setActionCommand("upPB");
	
		
		b5 =new JButton("Send");
		b5.setBounds(350,400,200,40);
		b5.setFocusable(false);
		//b5.addActionListener(this);
		b5.setActionCommand("send");
		b5.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
	   try {    
	        String host = t1.getText(); 
	        Socket s= new Socket(host,4080);
	        dataInputStream = new DataInputStream(s.getInputStream());
			dataOutputStream = new DataOutputStream(s.getOutputStream());
	        System.out.println( "Sending the File to the Server");
	        SocClient.sendFile("C:/Users/Dell/eclipse-workspace/PublicBasedEncryptionFileSharingInClouds/adcc.pdf");
	        dataInputStream.close();
	        dataInputStream.close();
	        
	   }
	        catch (Exception ex) {  
	            System.out.println(ex);  
	        }
			}
			
		});
		
		container.add(b5);
		
		frame.setVisible(true);
		
		b6 =new JButton("Cancel");
		b6.setBounds(350,470,200,40);
		b6.setFocusable(false);
		container.add(b6);
		b6.addActionListener(this);
		b6.setActionCommand("cancel");
		
		add =new JButton("add");
		add.setBounds(650,320,100,40);
		b6.setFocusable(false);
		container.add(add);
		b5.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
	   try {  
		   inputLine = new JTextField();
		   inputLine.setBounds(650,380,100,40);
			container.add(inputLine);
	        
	        
	   }
	        catch (Exception ex) {  
	            System.out.println(ex);  
	        }
			}
			
		});
		add.setActionCommand("add");
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String action=e.getActionCommand();
		if(action=="upload") {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("C:/Users/Dell/eclipse-workspace/PublicBasedEncryptionFileSharingInClouds/src_file"));
			int response= fileChooser.showSaveDialog(null);
			
			if(response == JFileChooser.APPROVE_OPTION) {
				file =new File(fileChooser.getSelectedFile().getAbsolutePath());
				System.out.println(file);
			}
		}
		else if(action=="upPB") {
			JFileChooser fileChooser1 = new JFileChooser();
			fileChooser1.setCurrentDirectory(new File("C://Users//Dell/Documents"));
			int response= fileChooser1.showSaveDialog(null);
			
			if(response == JFileChooser.APPROVE_OPTION) {
				File file =new File(fileChooser1.getSelectedFile().getAbsolutePath());
				System.out.println(file);
			}
		}
		/*else if(action=="send")
		{

			 String host = t1.getText(); 
			 System.out.println(host);
			 try {
				send(host,file);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}*/
		else if(action=="cancel") {
				System.exit(0);
		}
		else
		{
			System.out.println("!");
			
		}
	}
	

	public static void main(String[] args) {
		
		SampleOneToMany  b= new SampleOneToMany ();
		
		
		// TODO Auto-generated method stub

	}




}

