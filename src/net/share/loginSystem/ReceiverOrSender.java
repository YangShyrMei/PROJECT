
package net.share.loginSystem;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.io.*;

import net.share.upload.SenderSide;
import net.share.upload.userSide;


public class ReceiverOrSender extends Frame implements ActionListener{
	

	JButton b1,b2;
	JFrame frame;
	
	public ReceiverOrSender()  {
	
		
		JFrame frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,0,900,800);
		
		Container container= frame.getContentPane();
		container.setBackground(Color.ORANGE);
		container.setLayout(null);
		
		JLabel label2 = new JLabel();
		label2.setText("Are you a Sender or a Reciver? Please select the button given below.");
		label2.setBounds(80,75,800,50);
		label2.setFont(new Font("Times New Roman",Font.BOLD,25));
		container.add(label2);
		//label2.setForeground(new Color("0x00FF00"));
		//label2.printBorder(true);
		label2.setBackground(Color.black);	
		
	
		
		b2 =new JButton("Sender");
		b2.setBounds(350,250,200,40);
		b2.setFocusable(false);
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new getPublicKey();
				dispose();
				
			}
		});
		container.add(b2);
	
	
		
		b1 =new JButton("Receiver");
		b1.setBounds(350,400,200,40);
		b1.setFocusable(false);
		//b5.addActionListener(this);
		b1.setActionCommand("send");
		b1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				new getKeys();
				dispose();
	 
			}
			
		});
		
		container.add(b1);
		
		frame.setVisible(true);
		
	
		
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	

	public static void main(String[] args) {
		
		ReceiverOrSender b= new ReceiverOrSender();
		
		
		// TODO Auto-generated method stub

	}




}

