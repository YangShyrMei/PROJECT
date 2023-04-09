package net.share.loginSystem;


import java.util.HashMap;
import javax.swing.*;

import net.share.upload.SenderSide;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoginFrame extends JFrame implements ActionListener {
	HashMap<String,String> logininfo= new HashMap<String,String>();

    Container container = getContentPane();
    JLabel title= new JLabel("A Public Key based Framework to deal with Data Sharing in Clouds");
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password"); 


    LoginFrame(HashMap<String,String> logininfoOriginal) {
    	logininfo=logininfoOriginal;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }
  

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
    	title.setBounds(80,15,800,50);
    	title.setFont(new Font("Times New Roman",Font.BOLD,25));
        userLabel.setBounds(320, 150, 100, 30);
        passwordLabel.setBounds(320, 220, 100, 30);
        userTextField.setBounds(420, 150, 150, 30);
        passwordField.setBounds(420, 220, 150, 30);
        showPassword.setBounds(420, 250, 150, 30);
        loginButton.setBounds(320, 300, 100, 30);
        resetButton.setBounds(470, 300, 100, 30);


    }

    public void addComponentsToContainer() {
    	container.add(title);
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
        container.setBackground(Color.ORANGE);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
        loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
	            String pwdText;
                String userText;
                Login l = new Login();
	            userText = userTextField.getText();
                l.setUserText(userText);
	            pwdText = String.valueOf(passwordField.getPassword());
	            if (logininfo.containsKey(userText) && logininfo.get(userText).equals(pwdText)) {
	               // JOptionPane.showMessageDialog(this, "Login Successful");
	                new ReceiverOrSender();
					dispose();
	            } else {
	               // JOptionPane.showMessageDialog(this, "Invalid Username or Password");
	            }
				
				
			}
        	
        });    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) {
            String pwdText;
            String userText;
            userText = userTextField.getText();
            pwdText = String.valueOf(passwordField.getPassword());
            if (logininfo.containsKey(userText) && logininfo.get(userText).equals(pwdText)) {
                JOptionPane.showMessageDialog(this, "Login Successful");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }

        }
        //Coding Part of RESET button
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
       //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }


        }
    }

}

public class Login {
    private static String userText;

    public static void main(String[] a) {
    	IDandPasswords idandPasswords= new IDandPasswords();
        LoginFrame frame = new LoginFrame(idandPasswords.getLoginInfo());
       // IDandPasswords idandPasswords= new IDandPasswords();
		//Login login=new Login(idandPasswords.getLoginInfo());
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 900, 800);
       
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

    }

    public String getUserText() {
        return userText;
    }
    public void setUserText(String ut){
        userText= ut;
    }

}