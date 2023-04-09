
package net.share.loginSystem;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import net.share.upload.SenderSide;
import net.share.upload.userSide;
import net.share.loginSystem.ClientIP;





public class getPublicKey extends Frame implements ActionListener{
	JButton b1,b2;
	JFrame frame;
	JTextField t1 = new JTextField();
	private static DataInputStream dataInputStream = null;
	private static DataOutputStream dataOutputStream = null;
	static String received_public_key_path = System.getProperty("user.dir")+"/receivedPublicKey";

	public static void receiveFile(String fileName)throws Exception
        {
            int bytes = 0;
            FileOutputStream fileOutputStream= new FileOutputStream(received_public_key_path+"/"+fileName);
     
            long size= dataInputStream.readLong(); // read file size
            byte[] buffer = new byte[4 * 1024];
            while (size > 0&& (bytes = dataInputStream.read(
                           buffer, 0,
                           (int)Math.min(buffer.length, size)))
                          != -1) {
                // Here we write the file using write method
                fileOutputStream.write(buffer, 0, bytes);
                size -= bytes; // read upto file size
            }
            // Here we received file
            fileOutputStream.close();
        }
	
	public getPublicKey()  {

		
		JFrame frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setBounds(0,0,900,800);
		
		Container container= frame.getContentPane();
		container.setBackground(Color.ORANGE);
		container.setLayout(null);
		
		JLabel label2 = new JLabel();
		label2.setText("A Public Key based Framework to deal with Data Sharing in Clouds");
		label2.setBounds(80,75,800,50);
		label2.setFont(new Font("Times New Roman",Font.BOLD,25));
		container.add(label2);
		label2.setBackground(Color.black);	
		
		t1.setBounds(350,250,200,40);
		container.add(t1);
		//t1.setText("Enter reciver Public Key");
		
		JLabel label=new JLabel("Enter the Client name :"); 
		container.add(label);
		label.setBounds(220,245,150,60); 
	
		
	
		//get public key from group manager
		b1 =new JButton("Get Public Key File");
		b1.setBounds(350,320,200,40);
		b1.setFocusable(false);
	
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ServerSocket ss = new ServerSocket(5102);
					System.out.println("Sevrer is waiting for client request");
					Socket s = ss.accept();

					System.out.println("Client Connected");
					
					PrintStream ps = new PrintStream(s.getOutputStream());
					
					ps.println(t1.getText());

					dataInputStream = new DataInputStream(s.getInputStream());
					dataOutputStream = new DataOutputStream(s.getOutputStream());

					receiveFile("PublicKey.pub");
					System.out.println("Got public key");
                    //clean up
					dataInputStream.close();
					dataOutputStream.close();
					ps.close();
					s.close();
					

				}catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
				
			}
		});
		container.add(b1);
		
		frame.setVisible(true);
		
		b2 =new JButton("Next");
		b2.setBounds(350,400,200,40);
		b2.setFocusable(false);
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SenderSide();
				dispose();
			
			}
		});
		container.add(b2);
		
	
		
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	

	public static void main(String[] args) {
		
		getPublicKey b= new getPublicKey();
		
		
		// TODO Auto-generated method stub

	}




}

