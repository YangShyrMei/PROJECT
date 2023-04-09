package net.share.upload;
import java.awt.*;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import net.share.client.SocClient;


public class sample extends Frame implements ActionListener{
	String src_file_path= System.getProperty("user.dir")+"/src_file";
	
	private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
	public sample() {
		
		
		Label p,i;
		TextField pk,ip;
		Button btnFU,btnGK,btnEncrypt,btnSend,btnCancel,btnPublicKeyFU;
		
		btnFU= new Button("Upload the File");
		btnFU.setBounds(100,260,70,40);
		
		btnGK= new Button("Generate the Key");
		btnGK.setBounds(100,260,70,40);
		
		btnEncrypt= new Button("Encryption");
		btnEncrypt.setBounds(100,260,70,40);
		
		
		btnSend = new Button("Send");
		btnSend.setBounds(100,260,70,40);
		
		btnCancel=new Button("Close");
		btnCancel.setBounds(100,260,70,40);
        btnCancel.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				System.exit(0);
				
			}
		}
		);
		
		btnPublicKeyFU=new Button("Upload public key");
		btnPublicKeyFU.setBounds(100,260,70,40);
		
		
		
		
		i= new Label("Enter IP adress:",Label.CENTER);
		i.setBounds(70,130,90,60);
		ip=new  TextField(40);
		ip.setBounds(100,260,70,40);
		btnSend.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
	   try {    
	        String host = ip.getText(); 
	        Socket s= new Socket(host,4080);
	        dataInputStream = new DataInputStream(s.getInputStream());
			dataOutputStream = new DataOutputStream(s.getOutputStream());
	        System.out.println( "Sending the File to the Server");
	        SocClient.sendFile(src_file_path+"/abcdef.txt");
	        dataInputStream.close();
	        dataInputStream.close();
	        
	   }
	        catch (Exception ex) {  
	            System.out.println(ex);  
	        }
			}
			
		});
		
		
		add(btnFU);
		add(btnGK);
		add(btnEncrypt);
		add(btnSend);
		add(btnCancel);
		add(ip);
		  
		
		setLayout(new FlowLayout());
		

		
	}
	

	public static void main(String[] args) {
		SenderSide b= new SenderSide();
		b.setTitle("FileUpload");
		b.setSize(800,800);
		b.setVisible(true);
		
		// TODO Auto-generated method stub

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

