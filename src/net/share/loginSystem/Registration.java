package net.share.loginSystem;

import javax.swing.*;

import net.share.GroupManager.IPAddressOfClients;
import net.share.GroupManager.ImplementationToID;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.HashMap;


public class Registration extends JFrame implements ActionListener {
	JLabel i1, i2, i3, i4, i5, i6, i7, i8, i9;
	JTextField tf1, tf2, tf5, tf6, tf7, tf8;
	JButton btn1, btn2;
	JPasswordField p1, p2;
	HashMap <String,String> user_details = new HashMap<>();
	IDandPasswords idp = new IDandPasswords();
	ImplementationToID existing_dir = new ImplementationToID();
	IPAddressOfClients cip = new IPAddressOfClients();

	Registration() {
		setVisible(true);
		setSize(700, 700);
		setLayout(null);
		getContentPane().setBackground(Color.orange);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Registration Form ");
		i1 = new JLabel("A Public Key Based framework to deal with data Sharing in clouds");
		i1.setForeground(Color.black);
		i1.setFont(new Font("Serif", Font.BOLD, 20));
		i2 = new JLabel("Name:");
		i3 = new JLabel("Email-ID");
		i4 = new JLabel("Create Password:");
		i5 = new JLabel("Confirm Pasword:");
		i6 = new JLabel("Country:");
		i7 = new JLabel("state:");
		i8 = new JLabel("Phone No:");
		i9 = new JLabel("IP Address");
		tf1 = new JTextField();
		tf2 = new JTextField();
		p1 = new JPasswordField();
		p2 = new JPasswordField();
		tf5 = new JTextField();
		tf6 = new JTextField();
		tf7 = new JTextField();
		tf8 = new JTextField();
		btn1 = new JButton("Submit");
		btn2 = new JButton("clear");
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		i1.setBounds(80, 30, 500, 30);
		i2.setBounds(80, 70, 200, 30);
		i3.setBounds(80, 110, 200, 30);
		i4.setBounds(80, 150, 200, 30);
		i5.setBounds(80, 190, 200, 30);
		i6.setBounds(80, 230, 200, 30);
		i7.setBounds(80, 270, 200, 30);
		i8.setBounds(80, 310, 200, 30);
		i9.setBounds(80,350,200,30);
		tf1.setBounds(300, 70, 200, 30);
		tf2.setBounds(300, 110, 200, 30);
		p1.setBounds(300, 150, 200, 30);
		p2.setBounds(300, 190, 200, 30);
		tf5.setBounds(300, 230, 200, 30);
		tf6.setBounds(300, 270, 200, 30);
		tf7.setBounds(300, 310, 200, 30);
		tf8.setBounds(300,350, 200, 30);
		btn1.setBounds(50, 350, 100, 30);
		btn2.setBounds(170, 350, 100, 30);
		add(i1);
		add(i2);
		add(tf1);
		add(i3);
		add(tf2);
		add(i4);
		add(p1);
		add(i5);
		add(p2);
		add(i6);
		add(tf5);
		add(i7);
		add(tf6);
		add(i8);
		add(tf7);
		add(btn1);
		add(btn2);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) {
			int x = 0;
			String s1 = tf1.getText();
			String s2 = tf2.getText();
			char[] s3 = p1.getPassword();
			char[] s4 = p2.getPassword();
			String s8 = new String(s3);
			String s9 = new String(s4);
			String s5 = tf5.getText();
			String s6 = tf6.getText();
			String s7 = tf7.getText();
			String s10 = tf8.getText();
			if(existing_dir.isPresent(s1)){
				JOptionPane.showMessageDialog(btn1,
								"Please change user name. User name already taken");
			} else{
			if (s8.equals(s9)) {
					/*Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
							"system");
					PreparedStatement ps = con.prepareStatement("insert into reg values(???????)");
					ps.setString(1, s1);
					ps.setString(2, s2);
					ps.setString(3, s8);
					ps.setString(4, s9);
					ps.setString(5, s5);
					ps.setString(6, s6);
					ps.setString(7, s7);
					ResultSet rs = ps.executeQuery();
					x++;
					if (x > 0) {
						JOptionPane.showMessageDialog(btn1,
								"Registration Successfully and your passcode is This is cloud pr");
						// new Welcome(); */
						existing_dir.addIDPassword(s1,s8);
						cip.addIPAddress(s1,s10);

						idp.showMap();
						JOptionPane.showMessageDialog(btn1,
								"Registration Successful");
						Login.main(null);
						dispose();
					}
			 else {
				JOptionPane.showMessageDialog(btn1, "Password Does Not Match");
			}
		}} /*  else {
			tf1.setText("");
			tf2.setText("");
			p1.setText("");
			p2.setText("");
			tf5.setText("");
			tf6.setText("");
			tf7.setText("");
		} */
	}

	public HashMap getUserDetails(){
		return user_details;
	}
	public static void main(String args[]) {

		new Registration();
	}
}