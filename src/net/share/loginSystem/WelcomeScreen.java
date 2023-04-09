package net.share.loginSystem;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

import  static javax.swing.JFrame.EXIT_ON_CLOSE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import net.share.encryption.SymmetricEncryption;


public class WelcomeScreen extends JFrame{
	JButton login;
	
	public WelcomeScreen() {
		displayWelcomeScreen();
		setSize(800,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setForeground(Color.orange);
		JLabel label=new JLabel("Welcome to our  project ");
		label.setFont(new Font("lithograph",Font.BOLD,40));
		//label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(195,75,800,50);
		add(label);	
		JLabel label2 = new JLabel();
		JLabel label3 = new JLabel();
		label2.setText("A Public Key based Framework to deal with Data Sharing");
		label2.setBounds(50,100,800,50);
		label2.setFont(new Font("Times New Roman",Font.BOLD,30));
		label3.setText("in Clouds");
		label3.setBounds(195,90,200,50);
		label3.setFont(new Font("Times New Roman",Font.BOLD,30));
		//label2.setForeground(new Color("0x00FF00"));
		//label2.printBorder(true);
		label2.setBackground(Color.black);
		add(label2);
		
		/* login =new JButton("Login");
		login.setBounds(350,400,200,40);
		login.setFocusable(false);
		add(login);
		//login.addActionListener(this);
	    login.setActionCommand("encryption");
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					new Login();
				}
				catch(Exception ex1) {
					System.out.println(ex1);

				}
			}
		}); */
		

		
	}
	public static void main(String[] args) {
		new WelcomeScreen();
		
	}
	private void displayWelcomeScreen() {
		JWindow w= new JWindow(this);
		w.setSize(750,500);
		setLocationRelativeTo(null);
		w.setVisible(true);
		
		JPanel panel=new JPanel();
		w.add(panel);
		String imagePath="C:\\Users\\Dell\\eclipse-workspace\\PublicBasedEncryptionFileSharingInClouds\\imgonline-com-ua-resize-2By6nTUfbRS.jpg";
		JLabel label=new JLabel(new ImageIcon(imagePath));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label);
		panel.setBackground(Color.white);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		 JLabel label1 = new JLabel("Centered Text");
         label1.setForeground(Color.pink);
         label1.setFont(new Font("SansSerif", Font.BOLD, 40));
    
         label1.setHorizontalAlignment(SwingConstants.CENTER);
         panel.add(label1);
		
		JProgressBar progress= new JProgressBar(0,100);
		progress.setForeground(Color.orange);
		w.add(BorderLayout.PAGE_END,progress);
		w.revalidate();
		//Timer timer;
		
		timer=new Timer(100,new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x=progress.getValue();
				if(x==100) {
					w.dispose();
					WelcomeScreen.this.setVisible(true);
					timer.stop();
				}
				else {
					progress.setValue(x+4);
				}
				// TODO Auto-generated method stub
				
			}
			
			
		});
		timer.start();
		
		
	}
	Timer timer;
}
