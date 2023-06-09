
package net.share.loginSystem;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import net.share.upload.SenderSide;
import net.share.upload.userSide;
import net.share.GroupManager.IPAddressOfClients;
import net.share.loginSystem.ClientIP;





public class getPublicKey extends Frame implements ActionListener{
	JButton b1,b2,b3;
	JFrame frame;
	public static JTextField t1 = new JTextField();
	private static DataInputStream dataInputStream = null;
	private static DataOutputStream dataOutputStream = null;
	static String received_public_key_path = System.getProperty("user.dir")+"/receivedPublicKey";
	static String groupManager="192.168.1.11";
	//static String ClientID="";
	public static String clientID="";
	String IP_address_of_receiver;
	IPAddressOfClients cip = new IPAddressOfClients();

	public String getIPAddressOfReceiver()
	{
		return IP_address_of_receiver;
	}
	
	

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
		public static void sendRequest() throws UnknownHostException, IOException{
			Socket s = new Socket(groupManager,5321);
			System.out.println("Sending client ID:");
            PrintStream ps = new PrintStream(s.getOutputStream());
            clientID = t1.getText();
            ps.println(clientID.toCharArray());
			ps.close();
			s.close();
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


		//request public key of client from group manager
		b3 =new JButton("Request Public Key File");
		b3.setBounds(350,320,200,40);
		b3.setFocusable(false);
	
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("Connecting GM");
					Socket s1 = new Socket(groupManager, 5513);
					System.out.println("Sending client name");
					PrintStream ps1 = new PrintStream(s1.getOutputStream());
					InetAddress iAddress1= InetAddress.getLocalHost();
					String server_IP1=iAddress1.getHostAddress();
					ps1.println(server_IP1.toCharArray());
					ps1.close();
					s1.close();
					ps1.close();
					s1.close();
					
			 		Socket s = new Socket(groupManager, 5498);
             		//send System IP
					 System.out.println("Sending System IP");
					 PrintStream ps = new PrintStream(s.getOutputStream());
					 InetAddress iAddress= InetAddress.getLocalHost();
					 String server_IP=iAddress.getHostAddress();
					 ps.println(server_IP.toCharArray());
					 ps.close();
					 s.close();
					 sendRequest();
					JOptionPane.showMessageDialog(b3, "Request accepted, get public key");

				}catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
				
			}
		});
		container.add(b3);


	
		
	
		//get public key from group manager
		b1 =new JButton("Get Public Key File");
		b1.setBounds(350,420,200,40);
		b1.setFocusable(false);
	
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ServerSocket ss1 = new ServerSocket(5183);
					System.out.println("Sevrer is waiting for client request");
					Socket s1 = ss1.accept();
					System.out.println("Client Connected");
					InputStream is1 = s1.getInputStream();
					InputStreamReader isr1 = new InputStreamReader(is1);
					BufferedReader br1= new BufferedReader(isr1);
					IP_address_of_receiver = br1.readLine();
					IP_address_of_receiver=IP_address_of_receiver.trim();
					cip.addIPAddress(clientID,IP_address_of_receiver);
					br1.close();
					isr1.close();
					is1.close();
					s1.close();
					ss1.close();
					System.out.println("Got requested client IP address");

					
					ServerSocket ss = new ServerSocket(5167);
					System.out.println("Sevrer is waiting for client request");
					Socket s = ss.accept();

					System.out.println("Client Connected");
					
					/*PrintStream ps = new PrintStream(s.getOutputStream());
					
					ps.println(t1.getText());*/

					dataInputStream = new DataInputStream(s.getInputStream());
					dataOutputStream = new DataOutputStream(s.getOutputStream());

					receiveFile("PublicKey.pub");
					System.out.println("Got public key");
                    //clean up
					dataInputStream.close();
					dataOutputStream.close();
					//ps.close();
					s.close();
					JOptionPane.showMessageDialog(b1,"Public key received");
					

				}catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
				
			}
		});
		container.add(b1);
		
		frame.setVisible(true);
		
		b2 =new JButton("Next");
		b2.setBounds(350,500,200,40);
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

