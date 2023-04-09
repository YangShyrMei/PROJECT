
package net.share.loginSystem;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.io.*;

import net.share.upload.SenderSide;
import net.share.upload.userSide;
import net.share.client.GenerateKeys;


public class getKeys extends Frame implements ActionListener{
	

	JButton b1,b2,b3,b4;
	JFrame frame;
	
	public getKeys()  {
		
		
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
		//label2.setForeground(new Color("0x00FF00"));
		//label2.printBorder(true);
		label2.setBackground(Color.black);	
		
	
		
		b1 =new JButton("Get the Key File");
		b1.setBounds(350,200,200,40);
		b1.setFocusable(false);
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
				GenerateKeys g=new GenerateKeys();
				g.getKeys();
				}
				catch(Exception e1){
					System.out.println(e1);
				}
			
			}
		});
		container.add(b1);
		
		b3 =new JButton("Next");
		b3.setBounds(350,400,200,40);
		b3.setFocusable(false);
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new userSide();
				dispose();
			
			}
		});
		container.add(b3);
		
		b3 =new JButton("Send the PublicKey File");
		b3.setBounds(350,300,200,40); 
		b3.setFocusable(false);
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new userSide();
				dispose();
			
			}
		});
		container.add(b3);

	
		b2 =new JButton("Cancel");
		b2.setBounds(350,500,200,40);
		b2.setFocusable(false);
		container.add(b2);
		b2.addActionListener(this);
		b2.setActionCommand("cancel");		
		frame.setVisible(true);
		
	
		
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
	}
	public static void main(String[] args) {
		
		getKeys g= new getKeys();
		
		
		// TODO Auto-generated method stub

	}
	
	

	

	




}

