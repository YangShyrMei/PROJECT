package net.share.upload;
import java.awt.*;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import net.share.client.SocClient;
import net.share.encryption.Decryptor;
import net.share.rsaAlgorithm.AsymDecrypt;


public class userSide extends Frame implements ActionListener{
	
	private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
	JButton b1,b2,b3,b4,b5,b6;
	JFrame frame;
	String project_root = System.getProperty("user.dir");
    String zipped_path = System.getProperty("user.dir")+"/zipped";
	String unzipped_path = System.getProperty("user.dir")+"/unzipped";
	/*JTextField t1 = new JTextField();*/
	
	private static File file;
	public static void receiveFile(String fileName)throws Exception
        {
            int bytes = 0;
			
            FileOutputStream fileOutputStream= new FileOutputStream("/"+fileName+".zip");
     
            long size= dataInputStream.readLong(); // read file size
            byte[] buffer = new byte[4 * 1024];
            while (size > 0&& (bytes = dataInputStream.read(
                           buffer,0,
                           (int)Math.min(buffer.length, size)))
                          != -1) {
                // Here we write the file using write method
                fileOutputStream.write(buffer, 0, bytes);
                size -= bytes; // read upto file size
            }
            // Here we received file
            System.out.println("Zipped Folder Received");
            fileOutputStream.close();
        }

	public userSide()  {
		
		
		JFrame frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,0,900,800);
		
		Container container= frame.getContentPane();
		container.setBackground(Color.ORANGE);
		container.setLayout(null);
		
		
		
		/*t1.setBounds(350,320,200,40);
		container.add(t1);
		//t1.setText("Enter reciver Public Key");
		
		/*JLabel label=new JLabel("Enter the IP adress :");
		container.add(label);
		label.setBounds(220,308,150,60);*/
		
		JLabel label2 = new JLabel();
		label2.setText("A Public Key based Framework to deal with Data Sharing in Clouds");
		label2.setBounds(80,15,800,50);
		label2.setFont(new Font("Times New Roman",Font.BOLD,25));
		container.add(label2);
		//label2.setForeground(new Color("0x00FF00"));
		//label2.printBorder(true);
		label2.setBackground(Color.black);
		
		/*b1 =new JButton("Upload the File");
		b1.setBounds(350,100,200,40);
		b1.setFocusable(false);
		container.add(b1);
		b1.addActionListener(this);
		b1.setActionCommand("upload"); */
		
		/*b2 =new JButton("Generate Key");
	    b2.setBounds(350,170,200,40);
		container.add(b2);
		b2.addActionListener(this);
		b2.setActionCommand("generateKey"); */
		
		
		b3 =new JButton("Receive file");
		b3.setBounds(350,170,200,40);
		b3.setFocusable(false);
		container.add(b3);
		b3.addActionListener(this);
		b3.setActionCommand("upPB");
		
		b4 =new JButton("Decryption");
		b4.setBounds(350,250,200,40);
		b4.setFocusable(false);
		container.add(b4);
		//b4.addActionListener(this);
		b4.setActionCommand("decryption");
		b4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Unzip u1 = new Unzip();
					String zipFilePath =( zipped_path+"/compressed.zip" );  
					String destDir = unzipped_path+"/" ;  
				    
					u1.unzipFile(zipFilePath, destDir) ;
					AsymDecrypt a1 = new AsymDecrypt();
					String fileName = unzipped_path+"/encryptedPublicKey.txt";
					a1.asymDecrypt(fileName);
				    
				}
				catch(Exception ex2) {
					System.out.println(ex2);
				}
			}
			
		});
	
		
		/*b5 =new JButton("cancel");
		b5.setBounds(350,400,200,40);
		b5.setFocusable(false);
		//b5.addActionListener(this);
		b5.setActionCommand("cancel");
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
	        SocClient.sendFile("C:/Users/yangs/eclipse-workspace/PublicBasedEncryptionFileSharingInClouds/src_file/abcd.pdf");
	        dataInputStream.close();
	        dataInputStream.close();
	        
	   }
	        catch (Exception ex) {  
	            System.out.println(ex);  
	        }
			}
			
		});
		
		container.add(b5);*/
		
		frame.setVisible(true);
		
		b6 =new JButton("Cancel");
		b6.setBounds(350,325,200,40);
		b6.setFocusable(false);
		container.add(b6);
		b6.addActionListener(this);
		b6.setActionCommand("cancel");
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String action=e.getActionCommand();
		if(action=="upload") {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(project_root));
			int response= fileChooser.showSaveDialog(null);
			
			if(response == JFileChooser.APPROVE_OPTION) {
				file =new File(fileChooser.getSelectedFile().getAbsolutePath());
				System.out.println(file);
			}
		}
		else if(action=="upPB") {
			try{
			ServerSocket ss = new ServerSocket(5130);
			System.out.println("Waiting for file");
			Socket s = ss.accept();
			System.out.println("Client is connected");
			dataInputStream = new DataInputStream(s.getInputStream());
			dataOutputStream = new DataOutputStream(s.getOutputStream());			
			receiveFile("compressed");
			dataInputStream.close();
			dataOutputStream.close();
			}
			catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
		
		userSide b= new userSide();
		
		
		// TODO Auto-generated method stub

	}




}

