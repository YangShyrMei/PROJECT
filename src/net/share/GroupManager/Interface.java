
package net.share.GroupManager;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.share.loginSystem.getKeys;

import java.io.*;


public class Interface extends Frame implements ActionListener{
	

	JButton b1,b2;
	JFrame frame;
	
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
                    new SendPublicKey();
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
		
		Interface b= new Interface();
		
		
		// TODO Auto-generated method stub

	}




}

