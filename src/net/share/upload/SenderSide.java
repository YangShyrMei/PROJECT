
package net.share.upload;
import java.awt.*;
import java.awt.Button;
import java.awt.FlowLayout;
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


public class SenderSide extends Frame implements ActionListener{
	
	private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
	JButton b1,b2,b3,b4,b5,b6,add;
	JFrame frame;
	JTextField t1 = new JTextField();
	String project_root= System.getProperty("user.dir");
	String src_file_path= System.getProperty("user.dir")+"/src_file";
	String encrypted_file_path = project_root+"/encrypted_file";
	String zipped_path = System.getProperty("user.dir")+"/zipped";
	
	private static File file;
	public static void sendFile(String path) throws Exception
    {
        int bytes = 0;
        // Open the File where he located in your pc
        File file = new File(path);
        FileInputStream fileInputStream
            = new FileInputStream(file);
 
        // Here we send the File to Server
        dataOutputStream.writeLong(file.length());
        // Here we  break file into chunks
        byte[] buffer = new byte[4 * 1024];
        while ((bytes = fileInputStream.read(buffer))
               != -1) {
          // Send the file to Server Socket 
          dataOutputStream.write(buffer, 0, bytes);
            dataOutputStream.flush();
        }
        // close the file here
        fileInputStream.close();
    }
	public SenderSide()  {
		
		
		JFrame frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,0,900,800);
		
		Container container= frame.getContentPane();
		container.setBackground(Color.ORANGE);
		container.setLayout(null);
		
		
		
		/*t1.setBounds(350,320,200,40);
		container.add(t1);
		//t1.setText("Enter reciver Public Key");
		
         JLabel label=new JLabel("Enter the Client ID :");
		container.add(label);
		label.setBounds(220,308,150,60);
		*/
		
		JLabel label2 = new JLabel();
		label2.setText("A Public Key based Framework to deal with Data Sharing in Clouds");
		label2.setBounds(80,50,800,50);
		label2.setFont(new Font("Times New Roman",Font.BOLD,25));
		container.add(label2);
		//label2.setForeground(new Color("0x00FF00"));
		//label2.printBorder(true);
		label2.setBackground(Color.black);
		
		b1 =new JButton("Upload your File");
		b1.setBounds(350,200,200,40);
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
		b3.setBounds(350,300,200,40);
		b3.setFocusable(false);
		container.add(b3);
		//b3.addActionListener(this);
		b3.setActionCommand("encryption");
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					SymmetricEncryption en = SymmetricEncryption.getEncrypter(true);
					File src = new File(src_file_path);
					File dest = new File(encrypted_file_path);
					en.encrypt(src, dest);

                    samplezip s1 = new samplezip();

				}
				catch(Exception ex1) {
					System.out.println(ex1);

				}
			}
		});
		
		/*b4 =new JButton("Receiver public key");
		b4.setBounds(350,240,200,40);
		b4.setFocusable(false);
		container.add(b4);
		b4.addActionListener(this);
		b4.setActionCommand("upPB"); */
	
		
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
	        //String host = t1.getText(); 
	        Socket s= new Socket("192.168.1.31",5130);
	        dataInputStream = new DataInputStream(s.getInputStream());
			dataOutputStream = new DataOutputStream(s.getOutputStream());
	        System.out.println( "Sending the File to the Server");
	        sendFile(zipped_path+ "/compressed.zip");
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
		b6.setBounds(350,500,200,40);
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
			fileChooser.setCurrentDirectory(new File(src_file_path));
			int response= fileChooser.showSaveDialog(null);
			
			if(response == JFileChooser.APPROVE_OPTION) {
				file =new File(fileChooser.getSelectedFile().getAbsolutePath());
				System.out.println(file);
				
			}
		}
		else if(action=="upPB") {
			JFileChooser fileChooser1 = new JFileChooser();
			fileChooser1.setCurrentDirectory(new File(project_root));
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
		
		SenderSide b= new SenderSide();
		
		
		// TODO Auto-generated method stub

	}




}

